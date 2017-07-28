package movies;

public class MoviesService {
	public void insertMovies(MoviesVO vo[]) {
		MoviesDAO dao = new MoviesDAO();
		for (int i = 0; i < vo.length; i++) {
			dao.insertMovie(i, vo[i].getTitle(), vo[i].getDate());
		}
	}
}
