package rank;

import java.sql.*;

import util.jdbc.JdbcUtil;

public class RankDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private final String INSERT_MOVIE = "insert into rank(rank, title, date) values (?,?,?)";
	public void insertMovie(int rank, String title, String date) {
		con = JdbcUtil.getConnection();
		try {
			pstmt = con.prepareStatement(INSERT_MOVIE);
			pstmt.setInt(1, rank);
			pstmt.setString(2, title);
			pstmt.setString(3, date);
			
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.close(con, pstmt);
	}
}
