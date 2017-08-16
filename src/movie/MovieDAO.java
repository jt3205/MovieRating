package movie;

import java.sql.*;

import util.jdbc.JdbcUtil;

public class MovieDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	MovieVO vo;
	
	private final String INSERT_MOVIE = "insert into movie(title, rating, showing, spectator, poster, vidioIframe) values (?,?,?,?,?,?)";
	private final String UPDATE_MOVIE = "update movie set rating = ?, showing = ?, spectator = ? where title = ?";
	private final String GET_MOVIE = "select * from movie where title = ?";
	
	void insertMovie(MovieVO vo[]) {
		con = JdbcUtil.getConnection();
		for (int i = 0; i < vo.length; i++) {
			try {
				pstmt = con.prepareStatement(INSERT_MOVIE);
				pstmt.setString(1, vo[i].getTitle());
				pstmt.setDouble(2, vo[i].getRating());
				pstmt.setBoolean(3, vo[i].isShowing());
				pstmt.setString(4, vo[i].getSpectator());
				pstmt.setString(5, vo[i].getPosterLink());
				pstmt.setString(6, vo[i].getIframeVidioLink());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				pstmt.execute();
			} catch (SQLException e) {
				System.out.println("[" + vo[i].getTitle() + "] 이 이미 DB에 있습니다. 업데이트 합니다");
				JdbcUtil.close(pstmt);
				updateMovie(vo[i], con);
			}
		}
		JdbcUtil.close(con, pstmt);
	}

	// 보통 여러번 호출해야하는데 한번에 처리할수는 없기 때문에 Connection을 받아서 처리함
	void updateMovie(MovieVO vo, Connection con) {
		try {
			pstmt = con.prepareStatement(UPDATE_MOVIE);
			pstmt.setFloat(1, (float) vo.getRating());
			pstmt.setBoolean(2, vo.isShowing());
			pstmt.setString(3, vo.getSpectator());
			pstmt.setString(4, vo.getTitle());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.close(pstmt);
	}

	MovieVO getMovie(String title){
		con = JdbcUtil.getConnection();
		vo = new MovieVO();
		try {
			pstmt = con.prepareStatement(GET_MOVIE);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			rs.next();
			vo.setTitle(rs.getString(1));
			vo.setRating(rs.getDouble(2));
			vo.setShowing(rs.getBoolean(3));
			vo.setSpectator(rs.getString(4));
			vo.setPosterLink(rs.getString(5));
			vo.setIframeVidioLink(rs.getString(6));
			
			JdbcUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
}
