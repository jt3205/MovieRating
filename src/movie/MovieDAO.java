package movie;

import java.sql.*;

import util.jdbc.JdbcUtil;

public class MovieDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	private final String INSERT_MOVIE = "insert into movie(title, rating, showing, spectator) values (?,?,?,?)";
	private final String UPDATE_MOVIE = "update movie set rating = ?, showing = ?, spectator = ? where title = ?";

	public void insertMovie(MovieVO vo[]) {
		con = JdbcUtil.getConnection();
		try {
			pstmt = con.prepareStatement(INSERT_MOVIE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < vo.length; i++) {
			try {
				pstmt.setString(1, vo[i].getTitle());
				pstmt.setFloat(2, (float) vo[i].getRating());
				pstmt.setBoolean(3, vo[i].isShowing());
				pstmt.setString(4, vo[i].getSpectator());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				pstmt.execute();
			} catch (SQLException e) {
				System.out.println("[" + vo[i].getTitle() + "] 이 이미 DB에 있습니다. 업데이트 합니다");
				updateMovie(vo[i], con);
			}
		}
		JdbcUtil.close(con, pstmt);
	}

	// 보통 여러번 호출해야하는데 한번에 처리할수는 없기 때문에 Connection을 받아서 처리함
	public void updateMovie(MovieVO vo, Connection con) {
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
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
