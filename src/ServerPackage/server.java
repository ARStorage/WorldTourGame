package ServerPackage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

class login extends Thread{
	private Socket socket;
	memberSystem m = new memberSystem();
	PrintWriter out;
	BufferedReader in;
	boolean flag = true;
	public login(Socket socket)
	{
		this.socket = socket;
	}
	
	
	public void run(){
		
		try {
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		member user = null;
		while(true)
		{
			try {
			if(in.readLine().equals("START"))
			{
				System.out.println("호출됨");
				String id = in.readLine();
				String password = in.readLine();
				
				System.out.println(in.readLine());
				user = m.login(id,password);
				if(user!=null)
				{
					out.println("START");
					out.println(user.getName());
					out.println(user.getscore());
					out.println("END");
					this.flag = false;
					break;
				}
				
				else
				{
					System.out.println("로그인 실패");
					out.println(-1);
				}
			}
			} catch (SQLException | IOException e1) {e1.printStackTrace();}
			
			stop();
		}
	}
}



class remove extends Thread{
	private Socket socket;
	memberSystem m = new memberSystem();
	PrintWriter out;
	BufferedReader in;

	public remove(Socket socket)
	{
		this.socket = socket;
	}
	
	
	public void run(){
		
		try {
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		member user = null;
		while(true)
		{
			try {
			if(in.readLine().equals("START"))
			{
				System.out.println("호출됨");
				String id = in.readLine();
				String password = in.readLine();
				
				System.out.println(in.readLine());
				m.remove(id,password);
				if(m.remove(id,password)==1)
				{
					out.println("START");
					out.println("OK");
					break;
				}
				
				else
				{
					System.out.println("탈퇴 실패");
					out.println("START");
					out.println(-1);
				}
			}
			} catch (SQLException | IOException e1) {e1.printStackTrace();}
			
			stop();
		}
	}
}
class rank extends Thread{
	private Socket socket;
	memberSystem m = new memberSystem();
	PrintWriter out;
	BufferedReader in;
	boolean flag = true;
	
	public rank(Socket socket)
	{
		this.socket = socket;
	}
	
	
	public void run(){
		try {
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			String data[][] = m.getTable();
			
			out.println("START");
			for(int i = 0; i<data.length; i++)
			{
				if(data[i][0]==null)
					break;
				out.println(data[i][0]);
				out.println(data[i][1]);
			}
			out.println("END");
			this.flag = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}


class join extends Thread{
	private Socket socket;
	memberSystem m = new memberSystem();
	PrintWriter out;
	BufferedReader in;
	boolean flag = true;
	
	public join(Socket socket)
	{
		this.socket = socket;
	}
	
	
	public void run(){
		try {
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
		try {
			if(in.readLine().equals("START"))
			{
				String id = in.readLine();
				String password = in.readLine();
				String name = in.readLine();
				
				System.out.println(in.readLine()+"멤버정보 받음");
				try {
					if(m.join(id, password, name)!=0)
					{
						this.flag = false;
						break;
					}
					
				} catch (SQLException e1) {e1.printStackTrace();}
			}

		}catch(Exception e) {}}
	}
}

class result extends Thread{
	private Socket socket;
	memberSystem m = new memberSystem();
	PrintWriter out;
	BufferedReader in;
	boolean flag = true;
	
	public result(Socket socket)
	{
		this.socket = socket;
	}
	
	
	public void run(){
		try {
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
		try {
			if(in.readLine().equals("START"))
			{
				String id = in.readLine();
				String score = in.readLine();
				m.modifyScore(id,Integer.parseInt(score));
				System.out.println("결과 정보 받음");
			}
			else
			{
				this.flag = false;
				break;
			
			}

		}catch(Exception e) {}}
	}
	
}

public class server {
	
	public static void main(String[] args) throws IOException {
		PrintWriter out;
		BufferedReader in;
		ServerSocket ss = new ServerSocket(9999);
		System.out.println("서버 시작");
		
		while(true)
		{
			Socket socket = ss.accept();
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String menu = in.readLine();
			if(menu.equals("LOGIN"))
			{
				System.out.println("로그인");
				login log = new login(socket);
				log.start();
				if(!log.flag)
					log.stop();
			}
			
			else if(menu.equals("RANK"))
			{
				System.out.println("랭킹");
				rank r = new rank(socket);
				r.start();
				if(!r.flag)
					r.stop();
			}
			
			else if(menu.equals("JOIN"))
			{
				System.out.println("회원가입");
				join j = new join(socket);
				j.start();
				if(!j.flag)
					j.stop();
			}
			
			else if(menu.equals("REMOVE"))
			{
				System.out.println("회원 탈퇴");
				remove r = new remove(socket);
				r.start();
			}
			
			else if(menu.equals("RESULT"))
			{
				System.out.println("점수 저장");
				result rs = new result(socket);
				rs.start();
				if(!rs.flag)
					rs.stop();
			}
			
			else if(menu.equals("GAME"))
			{
				System.out.println("게임");
			}

		}

	}

}
