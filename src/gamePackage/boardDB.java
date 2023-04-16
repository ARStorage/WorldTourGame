package gamePackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class boardDB {
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
	
	public destination[] setBoard() throws SQLException
	{	
		Connection con = makeConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * " + 
				"FROM board natural join nationInit " + 
				"ORDER BY board.ordernum;");
		destination[] d = new destination[40];
		int i = 0;
		
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				int ordernum = rs.getInt("ordernum");
				String nation = rs.getString("nation");
				String name = rs.getString("name");
				int tollPrice = rs.getInt("tollPrice");
				int buildingPrice = rs.getInt("buildingPrice")*10000;
				int villaPrice = rs.getInt("villaPrice")*10000;
				int hotelPrice = rs.getInt("hotelPrice")*10000;
				String color = rs.getString("color");
				int tollFee = rs.getInt("tollFee");
				int buildingFee = rs.getInt("buildingFee");
				int villaFee = rs.getInt("villaFee");
				int hotelFee = rs.getInt("hotelFee");
				d[i++] = new destination(id,ordernum,nation, name,tollPrice,
						buildingPrice,villaPrice, hotelPrice,color,tollFee,
						buildingFee,villaFee,hotelFee);
			}
			
			return d;
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
	
}
