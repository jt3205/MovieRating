import info.InfoVO;
import util.crawler.MovieCrawler;

public class Test {
	public static void main(String[] args) {
		MovieCrawler crawler = new MovieCrawler("±ØÀåÆÇ Â¯±¸´Â ¸ø¸»·Á : ½À°Ý!! ¿Ü°èÀÎ µ¢µ¢ÀÌ");
		String str = "";
		InfoVO vo = crawler.crawlSubInfos();
		str += vo.getTitle()+"\n";
		str += vo.getEnglishTitle()+"\n";
		str += vo.getYear()+"\n";
		str += vo.getCountry()+"\n";
		str += vo.getGrade()+"\n";
		str += vo.getGenre()+"\n";
		str += vo.getShowtimes()+"\n";
		str += vo.getReleaseDate()+"\n";
		str += vo.getSpectator()+"\n";
		str += vo.getDirector()+"\n";
		str += vo.getPerformer()+"\n";
		System.out.println(str);
	}
}
