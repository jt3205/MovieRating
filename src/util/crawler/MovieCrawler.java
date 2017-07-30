package util.crawler;

import org.jsoup.select.Elements;

import movie.MovieVO;
import rank.RankService;
import rank.RankVO;
import util.date.DateUtil;

public class MovieCrawler {
	private String url;
	private String tag;
	
	private final String RATING_TAG = ".point > .num";
	
	Elements elements;
	MovieVO vo;
	MovieVO voArr[];
	Crawler crawler;
	
	public MovieCrawler(String url) {
		url = url.replaceAll(" ", "%20");
		this.url = url;
	}
	
	public void setUrl(String url) {
		url = url.replaceAll(" ", "%20");
		this.url = url;
	}
	
	public double crawlRating() {
		crawler = new Crawler(url);
		
		tag = RATING_TAG;
		elements = crawler.getElements(tag);
		return Double.parseDouble(elements.get(0).text());
	}
}
