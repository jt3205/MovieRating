import movie.MovieService;
import rank.RankService;
import util.crawler.MovieCrawler;
import util.crawler.RankCrawler;

public class Test {
	public static void main(String[] args) {
		RankCrawler crawler = new RankCrawler();
		RankService service = new RankService();
		
		service.insertMovies(crawler.CrawlRank());
	}
}
