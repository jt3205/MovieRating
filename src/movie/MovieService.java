package movie;

public class MovieService {
	MovieDAO dao = new MovieDAO();
	
	public void insertMovie(MovieVO vo[]) {
		dao.insertMovie(vo);
	}
}
