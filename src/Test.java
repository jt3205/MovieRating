import rank.RankService;
import util.crawler.MovieCrawler;
import util.crawler.RankCrawler;

public class Test {
	public static void main(String[] args) {
		MovieCrawler crawler = new MovieCrawler("http://movie.naver.com/movie/search/result.nhn?query=������ ¯���� ������ : ����!! �ܰ��� ������&section=movie&ie=utf8");
		System.out.println(crawler.crawlRating());
	}
}
