package ServerPackage;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;


public class memberSystem {
	
	public static Connection makeConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/worldtourgamedb?characterEncoding=UTF-8&serverTimezone=UTC", "root","1234");
			System.out.println("DB 연결 완료");
			} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
			} catch (SQLException e) {
			System.out.println("DB 연결 오류"+e);}
		return con;
		
	}
	
	public member login(String enterid, String enterpassword) throws SQLException
	{	
		Connection con = makeConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM members WHERE id LIKE \""+enterid+"\"");
		
		try {
		if(!rs.next())//결과가 없으면
		{
			System.out.println("해당 아이디가 존재하지 않음");
			return null;//id가 다르다.
		}
		else {
			String id = rs.getString("id");
			String name = rs.getString("name");
			String password = rs.getString("password");
			int score = rs.getInt("score");
			
			if(password.equals(enterpassword))
			{
				System.out.println(id+"    "+password+"    "+name+"    "+score);
				member m = new member(name,id,password,score);
				return m;
			}
			
			else
				return null;//패스워드가 다르다.
			}
		}catch(SQLException e) {
			System.out.println("DB 오류 "+e);
		}
		
		finally {
			rs.close();
			s.close();
			con.close();
		}
		
		return null;//error
	}
	
	public int join(String id, String password, String name) throws SQLException{

		Connection con = makeConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM members WHERE id LIKE \""+id+"\"");
		
		try {
			if(rs.next())
			{	
				System.out.println("중복된 ID");
				return -1;//중복된 id
			
			}
			else
			{
				String ss = "INSERT INTO members (id,password,name,score) VALUES ";
				ss+="('"+id+"','"+password+"','"+name+"',"+0+");";
				System.out.println(ss);
				s.executeUpdate(ss);
				
				return 1;//완료
			}
		}catch(SQLException e) {
			System.out.println("DB 오류 "+e);
		}finally {
			rs.close();
			s.close();
			con.close();
		}
		return 0;//error
	}
	
	public int remove(String id, String password) throws SQLException
	{	Connection con = makeConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM members WHERE id LIKE \""+id+"\"");
	
	try {
		if(!rs.next())
		{	
			System.out.println("존재하지 않는 ID");
			return -1;//존재하지 않는 ID
		
		}
		else
		{
			String del = "DELETE FROM members WHERE id=";
			del+="\""+id+"\"";
			s.executeUpdate(del);
			System.out.println(del);
			return 1;//완료
		}
		}catch(SQLException e) {
			System.out.println("DB 오류 "+e);
		}finally {
			rs.close();
			s.close();
			con.close();
		}
		return 0;//error
	}
	
	public int modifyScore(String id, int score) throws SQLException
	{
		Connection con = makeConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM members WHERE id LIKE \""+id+"\"");
	
		try {
			if(!rs.next())
			{	
				System.out.println("존재하지 않는 ID");
				return -1;//존재하지 않는 ID
			
			}
		else
		{
			String modify = "UPDATE members SET score=";
			modify+=score;
			modify+=" WHERE id="+"\""+id+"\"";
			s.executeUpdate(modify);
			System.out.println(modify);
			return 1;//완료
		}
		}catch(SQLException e) {
			System.out.println("DB 오류 "+e);
		}finally {
			rs.close();
			s.close();
			con.close();
		}
		return 0;//error
		
	}
	
	public String[][] getTable() throws SQLException{
		String[][] res = new String[30][2];
		Connection con = makeConnection();
		Statement s = con.createStatement();
		try {
			ResultSet rs = s.executeQuery("SELECT * FROM members ORDER BY score DESC LIMIT 30");
			int i = 0;
			while(rs.next()) {
				String id = rs.getString("id");
				int score = rs.getInt("score");
				//System.out.println(id+"   "+score);
				res[i][0] = id;
				res[i][1] = score+"";
				i++;
			}

		} catch (SQLException e) {e.printStackTrace();}
		finally {
			s.close();
			con.close();
			return res;}

	}

}
