package util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
	private static String user = "Y20112";
	private static String password = "1234";
	private static String url = "jdbc:mysql://gmsgondr.net:3306/Y20112?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Seoul";
	
	private static Connection con;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
