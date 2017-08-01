package util.crawler;

import org.jsoup.select.Elements;

import movie.MovieVO;
import rank.RankService;
import rank.RankVO;
import util.date.DateUtil;

public class MovieCrawler {
	
	private final String RATING_TAG_NAVER = ".point > .num";
	private final String SHOWING_TAG_NAVER = ".opening > em";
	private final String LINK_TAG_NAVER = ".result_thumb > a";
	private final String LINK_TAG_DAUM = "#contents_result > li > a";
	private final String LINK_TAG_CINE = ".mov_list > li > a";
	
	private String naverSearchLink = "http://movie.naver.com/movie/search/result.nhn?query=(title)&ie=utf8";
	private String daumSearchLink = "http://movie.daum.net/search/main?returnUrl=http%3A%2F%2Fmovie.daum.net%2Fmain%2Fnew&searchText=(title)#searchType=movie&page=1&sortType=acc";
	private String cineSearchLink = "http://www.cine21.com/search/?q=(title)";
	
	enum site {naver, daum, cine};
	
	Elements elements;
	MovieVO vo;
	MovieVO voArr[];
	Crawler crawler;
	String title;
	
	public String getSearchLink(site site) {
		switch (site) {
		case naver:
			return naverSearchLink.replaceAll("(title)", title.replaceAll(" ", "%20"));
		case daum:
			return daumSearchLink.replaceAll("(title)", title.replaceAll(" ", "%20"));
		case cine:
			return cineSearchLink.replaceAll("(title)", title.replaceAll(" ", "%20"));
		}
		return null;
	}
	
	public String getMovieLink(site site){
		String tag = "";
		
		switch (site) {
		case naver:
			tag = LINK_TAG_NAVER;
			break;
		case daum:
			tag = LINK_TAG_DAUM;
			break;
		case cine:
			tag = LINK_TAG_CINE;
			break;
		}

		crawler = new Crawler(getSearchLink(site));
		return crawler.getElements(tag).get(0).attr("abs:href");
	}
	
	public MovieCrawler(String title) {
		this.title = title;
	}
	
	public double crawlRating() {
		crawler = new Crawler(getMovieLink(site.naver));
		elements = crawler.getElements(RATING_TAG_NAVER);
		return Double.parseDouble(elements.get(0).text());
	}
	
	public boolean crawlShowingNow() {
		String url = crawlLink();
		Crawler movie = new Crawler(url);
		
		elements = movie.getElements(SHOWING_TAG_NAVER);
		if(elements != null || elements.get(0) == null){
			return false;
		}
		else if(elements.get(0).text().equals("»ó¿µÁß")){
			return true;
		}
		return false;
	}
	
	public String crawlLink() {
		elements = crawler.getElements(LINK_TAG_NAVER);
		return elements.get(0).attr("abs:href");
	}
	
	public String crawlPosterLink() {
		
		return LINK_TAG_NAVER;
	}
}
