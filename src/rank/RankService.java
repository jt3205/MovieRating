package rank;

public class RankService {
	RankDAO dao = new RankDAO();
	
	public void insertMovies(RankVO vo[]) {
		dao.insertMovie(vo);
	}
}