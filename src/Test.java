import util.crawler.Crawler;

public class Test {
	public static void main(String[] args) {
		for (int i = 0; i < new Crawler("http://movie.naver.com/movie/sdb/rank/rmovie.nhn").getElements(".tit3 > a").size(); i++) {
			System.out.println(new Crawler("http://movie.naver.com/movie/sdb/rank/rmovie.nhn").getElements(".tit3 > a").get(i).text());
		}
	}
}
