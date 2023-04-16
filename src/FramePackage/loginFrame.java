package FramePackage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import ServerPackage.member;
import ServerPackage.memberSystem;

//로그인 프레임
public class loginFrame extends JFrame implements ActionListener{
	JButton okButton, exitButton;
	JTextField idText;
	JPasswordField passwordText;
	JLabel idLabel, passwordLabel, infoLabel;
	JPanel loginPanel;
	member user=null;
	int flag;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public loginFrame() {
		
		try {
			socket = new Socket("localhost",9999);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(),true);
			output.println("LOGIN");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//버튼
		getContentPane().setLayout(null);
		okButton = new JButton("확인");
		okButton.setBackground(new Color(221, 160, 221));
		okButton.setBounds(320, 208, 88, 23);
		okButton.addActionListener(this);
		
		exitButton = new JButton("종료");
		exitButton.setBackground(new Color(221, 160, 221));
		exitButton.setBounds(448, 301, 65, 30);
		exitButton.addActionListener(this);
		
		//텍스트필드
		idText = new JTextField();
		idText.setBounds(216, 123, 192, 21);
		passwordText = new JPasswordField();
		passwordText.setBounds(216, 165, 192, 21);
		
		//라벨
		idLabel = new JLabel("아이디");
		idLabel.setBounds(139, 126, 37, 15);
		passwordLabel = new JLabel("비밀번호");
		passwordLabel.setBounds(139, 168, 65, 18);
		infoLabel = new JLabel("아이디와 비밀번호를 입력하세요.");
		infoLabel.setBounds(163, 46, 184, 15);
		
		//패널
		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(255, 255, 255));
		loginPanel.setBounds(0, 0, 525, 358);
		loginPanel.setLayout(null);
		
		loginPanel.add(okButton);
		loginPanel.add(exitButton);
		loginPanel.add(idText);
		loginPanel.add(passwordText);
		loginPanel.add(idLabel);
		loginPanel.add(passwordLabel);
		loginPanel.add(infoLabel);
		
		getContentPane().add(loginPanel);
		setSize(535,389);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("로그인");
	}
	

	public loginFrame(member[] users,int index) {
		try {
			socket = new Socket("localhost",9999);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(),true);
			output.println("LOGIN");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//버튼
		getContentPane().setLayout(null);
		memberSystem m = new memberSystem();
		okButton = new JButton("확인");
		okButton.setBackground(new Color(221, 160, 221));
		okButton.setBounds(320, 208, 88, 23);
		okButton.addActionListener((e)->{
			String id = new String(idText.getText());
			String password = new String(passwordText.getPassword());
			
			if(!id.equals(""))//빈 텍스트필드가 없으면
			{
				if(!password.equals(""))
				{
					try {
						output.println("START");
						output.println(id);//서버에 보내서
						output.println(password);
						output.println("END");
						
						if(input.readLine().equals("START"))//서버가 결과를 보내면
						{
							String name = input.readLine();
							int score = Integer.parseInt(input.readLine());
							users[index] = new member(name,id,password,score);//멤버 정보를 저장한다.
							setVisible(false);
							
						}
						
						else {//아니면 로그인 실패
							infoLabel.setText("로그인 정보가 틀립니다.");
						}
						
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
				
				else {infoLabel.setText("비밀번호를 입력하세요.");}
			}
			
			else
			{infoLabel.setText("아이디를 입력하세요.");}
		});
		
		exitButton = new JButton("종료");
		exitButton.setBackground(new Color(221, 160, 221));
		exitButton.setBounds(448, 301, 65, 30);
		exitButton.addActionListener(this);
		
		//텍스트필드
		idText = new JTextField();
		idText.setBounds(216, 123, 192, 21);
		passwordText = new JPasswordField();
		passwordText.setBounds(216, 165, 192, 21);
		
		//라벨
		idLabel = new JLabel("아이디");
		idLabel.setBounds(139, 126, 37, 15);
		passwordLabel = new JLabel("비밀번호");
		passwordLabel.setBounds(139, 168, 65, 18);
		infoLabel = new JLabel("아이디와 비밀번호를 입력하세요.");
		infoLabel.setBounds(163, 46, 184, 15);
		
		//패널
		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(255, 255, 255));
		loginPanel.setBounds(0, 0, 525, 358);
		loginPanel.setLayout(null);
		
		loginPanel.add(okButton);
		loginPanel.add(exitButton);
		loginPanel.add(idText);
		loginPanel.add(passwordText);
		loginPanel.add(idLabel);
		loginPanel.add(passwordLabel);
		loginPanel.add(infoLabel);
		
		getContentPane().add(loginPanel);
		setSize(535,389);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("로그인");
	}
	
	public void setUser(member user)
	{
		this.user = user;
	}
	
	public member getUser()
	{
		return this.user;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==okButton)
		{
			String id = new String(idText.getText());
			String password = new String(passwordText.getPassword());
			
			if(!id.equals(""))
			{
				if(!password.equals(""))
				{
					try {
					
					output.println("START");
					output.println(id);
					output.println(password);
					output.println("END");
					
					if(input.readLine().equals("START"))
					{
						String name = input.readLine();
						int score = Integer.parseInt(input.readLine());
						user = new member(name,id,password,score);
						setVisible(false);
						waitFrame w = new waitFrame(user);
						
					}
					
					else {
						infoLabel.setText("로그인 정보가 틀립니다.");
					}}catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				else {infoLabel.setText("비밀번호를 입력하세요.");}
			}
			
			else
			{infoLabel.setText("아이디를 입력하세요.");}
		}
		
		else
		{
			setVisible(false);
			System.exit(1);
		}
		
	}
}