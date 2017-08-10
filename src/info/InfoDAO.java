package info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.jdbc.JdbcUtil;

public class InfoDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private final String INSERT_INFO = "INSERT INTO `info`(`title`, `englishTitle`, `year`, `country`, `grade`, `genre`, `showtimes`, `releaseDate`, `spectator`, "
															+ "`director`, `performer`, `story`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	public void insertInfo(InfoVO vo[]) {
		con = JdbcUtil.getConnection();
		try {
			for (int i = 0; i < vo.length; i++) {
				pstmt = con.prepareStatement(INSERT_INFO);
				pstmt.setString(0, vo[i].getTitle());
				pstmt.setString(1, vo[i].getEnglishTitle());
				pstmt.setInt(2, vo[i].getYear());
				pstmt.setString(3, vo[i].getCountry());
				pstmt.setString(4, vo[i].getGrade());
				pstmt.setString(5, vo[i].getGenre());
				pstmt.setString(6, vo[i].getShowtimes());
				pstmt.setString(7, vo[i].getReleaseDate());
				pstmt.setString(8, vo[i].getSpectator());
				pstmt.setString(9, vo[i].getDirector());
				pstmt.setString(10, vo[i].getPerformer());
				pstmt.setString(11, vo[i].getStoryHtml());
				
				pstmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.close(con, pstmt);
	}
}
