package movie;

import java.sql.*;

import util.jdbc.JdbcUtil;

public class MovieDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private final String INSERT_MOVIE = "insert into movie(title, rating, showing) values (?,?,?)";
	private final String UPDATE_MOVIE = "update movie set rating = ?, showing = ?, where title = ?";
	
	public void insertMovie(String title, double rating, boolean isShowing) throws SQLException {
		con = JdbcUtil.getConnection();
		
		try {
			pstmt = con.prepareStatement(INSERT_MOVIE);
			pstmt.setString(1, title);
			pstmt.setFloat(2, (float)rating);
			pstmt.setBoolean(3, isShowing);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		pstmt.execute();
		JdbcUtil.close(con, pstmt);
	}
	
	public void updateMovie(String title, double rating, boolean isShowing) {
		con = JdbcUtil.getConnection();
		
		try {
			pstmt = con.prepareStatement(UPDATE_MOVIE);
			pstmt.setFloat(1, (float)rating);
			pstmt.setBoolean(2, isShowing);
			pstmt.setString(3, title);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.close(con, pstmt);
	}
}
