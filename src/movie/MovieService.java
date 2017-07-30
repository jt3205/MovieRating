package movie;

import java.sql.SQLException;

public class MovieService {
	MovieDAO dao = new MovieDAO();
	
	public void insertMovie(MovieVO vo) {
		try {
			dao.insertMovie(vo.getTitle(), vo.getRating(), vo.isShowing());
		} catch (SQLException e) {
			System.out.println("["+vo.getTitle()+"] DB�� �̹� �����մϴ�");
			dao.updateMovie(vo.getTitle(), vo.getRating(), vo.isShowing());
		}
	}
}
