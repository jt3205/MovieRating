package movie;

import java.sql.*;

import util.jdbc.JdbcUtil;

public class MovieDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	private final String INSERT_MOVIE = "insert into movie(title, rating, showing) values (?,?,?)";
	private final String UPDATE_MOVIE = "update movie set rating = ?, showing = ?, where title = ?";

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
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				pstmt.execute();
			} catch (SQLException e) {
				System.out.println("[" + vo[i].getTitle() + "] �� �̹� DB�� �ֽ��ϴ�. ������Ʈ �մϴ�");
				updateMovie(vo[i], con);
			}
		}
		JdbcUtil.close(con, pstmt);
	}

	// ���� ������ ȣ���ؾ��ϴµ� �ѹ��� ó���Ҽ��� ���� ������ Connection�� �޾Ƽ� ó����
	public void updateMovie(MovieVO vo, Connection con) {
		try {
			pstmt = con.prepareStatement(UPDATE_MOVIE);
			pstmt.setFloat(1, (float) vo.getRating());
			pstmt.setBoolean(2, vo.isShowing());
			pstmt.setString(3, vo.getTitle());
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
