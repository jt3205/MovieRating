package util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	private static String user = "Y20112";
	private static String password = "1234";
	private static String url = "jdbc:mysql://gmsgondr.net:3306/Y20112?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Seoul";
	
	private static Connection con;
	
	private JdbcUtil() {}
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con, PreparedStatement pstmt) {
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(pstmt);
	}
	
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		close(con, pstmt);
		
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
