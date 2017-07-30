import rank.RankService;
import util.crawler.MovieCrawler;
import util.crawler.RankCrawler;

public class Test {
	public static void main(String[] args) {
		MovieCrawler crawler = new MovieCrawler("http://movie.naver.com/movie/search/result.nhn?query=극장판 짱구는 못말려 : 습격!! 외계인 덩덩이&section=movie&ie=utf8");
		System.out.println(crawler.crawlRating());
	}
}
