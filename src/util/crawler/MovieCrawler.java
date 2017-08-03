package util.crawler;

import org.jsoup.select.Elements;
import movie.MovieVO;

public class MovieCrawler {
	private final String RATING_TAG_NAVER = ".point > .num";
	private final String SHOWING_TAG_NAVER = ".opening > em";
	private final String MOVIELINK_TAG_NAVER = ".result_thumb > a";
	private final String MOVIELINK_TAG_CGV = ".sect-chart > ul > li > .box-image > a";
	private final String MOVIELINK_TAG_CINE = ".mov_list > li > a";
	private final String POSTER_TAG_CINE = ".mov_poster > img";
	private final String VIDIOLINK_TAG_NAVER = ".video_thumb > li > .video_obj";
	private final String IFRAME_VIDIO_TAG_NAVER = ".video_ar > ._videoPlayer";

	private String naverSearchLink = "http://movie.naver.com/movie/search/result.nhn?query=title&ie=utf8";
	private String cgvSearchLink = "http://www.cgv.co.kr/search/?query=title";
	private String cineSearchLink = "http://www.cine21.com/search/?q=title";

	public enum site {
		naver, cgv, cine
	};

	Elements elements;
	MovieVO vo;
	MovieVO voArr[];
	Crawler crawler;
	String title;

	public MovieCrawler(String title) {
		this.title = title;
	}

	public double crawlRating() {
		crawler = new Crawler(getSearchLink(site.naver));
		elements = crawler.getElements(RATING_TAG_NAVER);
		return Double.parseDouble(elements.get(0).text());
	}

	public boolean crawlShowingNow() {
		crawler = new Crawler(getMovieLink(site.naver));
		elements = crawler.getElements(SHOWING_TAG_NAVER);
		if (elements == null) {
			return false;
		} else if (elements.get(0).text().equals("상영중")) {
			return true;
		}
		return false;
	}

	public String crawlPosterLink() {
		crawler = new Crawler(getMovieLink(site.cine));
		elements = crawler.getElements(POSTER_TAG_CINE);
		return elements.get(0).attr("src");
	}

	public String crawlIframeVidioLink() {
		crawler = new Crawler(getMovieLink(site.naver));
		elements = crawler.getElements(VIDIOLINK_TAG_NAVER);
		
		crawler = new Crawler(elements.get(0).attr("abs:href"));
		elements = crawler.getElements(IFRAME_VIDIO_TAG_NAVER);
		return elements.get(0).attr("abs:src");
	}
	
	private String getMovieTag(site site) {
		String tag = "";

		switch (site) {
		case naver:
			tag = MOVIELINK_TAG_NAVER;
			break;
		case cgv:
			tag = MOVIELINK_TAG_CGV;
			break;
		case cine:
			tag = MOVIELINK_TAG_CINE;
			break;
		}
		return tag;
	}

	public String getSearchLink(site site) {
		String link = "";
		switch (site) {
		case naver:
			link = naverSearchLink;
			break;
		case cgv:
			link = cgvSearchLink;
			break;
		case cine:
			link = cineSearchLink;
			break;
		}
		if (site == site.cgv && title.length() > 15) { // cgv는 15자 이상 검색이 안됨; 극혐
			title = title.substring(0, 15);
		}
		return link.replaceAll("title", title.replaceAll(" ", "%20"));
	}

	public String getMovieLink(site site) {
		String tag = getMovieTag(site);
		crawler = new Crawler(getSearchLink(site));
		return crawler.getElements(tag).get(0).attr("abs:href");
	}
}
