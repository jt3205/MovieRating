package rank;

public class RankService {
	public void insertMovies(RankVO vo[]) {
		RankDAO dao = new RankDAO();
		for (int i = 0; i < vo.length; i++) {
			dao.insertMovie(i+1, vo[i].getTitle(), vo[i].getDate());
		}
	}
}