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

	InfoVO vo;
	
	private final String INSERT_INFO = "INSERT INTO `info`(`title`, `englishTitle`, `year`, `country`, `grade`, `genre`, `showtimes`, `releaseDate`, "
			+ "`director`, `performer`, `story`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private final String GET_INFO = "select * from info where title = ?";
	
	public void insertInfo(InfoVO vo[]) {
		con = JdbcUtil.getConnection();
		for (int i = 0; i < vo.length; i++) {
			try {
				pstmt = con.prepareStatement(INSERT_INFO);
				pstmt.setString(1, vo[i].getTitle());
				pstmt.setString(2, vo[i].getEnglishTitle());
				pstmt.setInt(3, vo[i].getYear());
				pstmt.setString(4, vo[i].getCountry());
				pstmt.setString(5, vo[i].getGrade());
				pstmt.setString(6, vo[i].getGenre());
				pstmt.setString(7, vo[i].getShowtimes());
				pstmt.setString(8, vo[i].getReleaseDate());
				pstmt.setString(9, vo[i].getDirector());
				pstmt.setString(10, vo[i].getPerformer());
				pstmt.setString(11, vo[i].getStoryHtml());

				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		JdbcUtil.close(con, pstmt);
	}
	
	public InfoVO getInfo(String title){
		vo = new InfoVO();
		con = JdbcUtil.getConnection();
		try {
			pstmt = con.prepareStatement(GET_INFO);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			
			rs.next();
			vo.setTitle(rs.getString(1));
			vo.setEnglishTitle(rs.getString(2));
			vo.setYear(rs.getInt(3));
			vo.setCountry(rs.getString(4));
			vo.setGrade(rs.getString(5));
			vo.setGenre(rs.getString(6));
			vo.setShowtimes(rs.getString(7));
			vo.setReleaseDate(rs.getString(8));
			vo.setDirector(rs.getString(9));
			vo.setPerformer(rs.getString(10));
			vo.setStoryHtml(rs.getString(11));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.close(con, pstmt, rs);
		return vo;
	}
}
