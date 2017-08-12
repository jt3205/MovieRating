package rank;

import java.sql.*;

import util.jdbc.JdbcUtil;

public class RankDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private final String INSERT_MOVIE = "insert into rank(rank, title, date) values (?,?,?)";
	private final String GET_ALL = "SELECT DISTINCT title FROM rank";
	
	void insertMovie(RankVO vo[]) {
		con = JdbcUtil.getConnection();
		try {
			pstmt = con.prepareStatement(INSERT_MOVIE);
			
			for (int i = 0; i < vo.length; i++) {
				pstmt.setInt(1, i+1);
				pstmt.setString(2, vo[i].getTitle());
				pstmt.setString(3, vo[i].getDate());
				
				pstmt.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.close(con, pstmt);
	}
	
	String[] getAllMovies(){
		String result[] = null;
		con = JdbcUtil.getConnection();
		try {
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			rs.last();
			result = new String[rs.getRow()];
			rs.beforeFirst();
			
			for (int i = 0; rs.next(); i++) {
				result[i] = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
