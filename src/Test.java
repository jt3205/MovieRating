import movie.MovieService;
import rank.RankService;
import util.crawler.Crawler;
import util.crawler.MovieCrawler;
import util.crawler.RankCrawler;

public class Test {
	public static void main(String[] args) {
		MovieCrawler crawler = new MovieCrawler("������ ¯���� ������ : ����!! �ܰ��� ������");
		System.out.println(crawler.crawlIframeVidioLink());
	}
}
