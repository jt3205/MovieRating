import movie.MovieService;
import rank.RankService;
import util.crawler.Crawler;
import util.crawler.MovieCrawler;
import util.crawler.RankCrawler;

public class Test {
	public static void main(String[] args) {
		MovieCrawler crawler = new MovieCrawler("극장판 짱구는 못말려 : 습격!! 외계인 덩덩이");
		System.out.println(crawler.crawlIframeVidioLink());
	}
}
