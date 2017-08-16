package util.crawler;

import org.jsoup.select.Elements;
import info.InfoVO;
import movie.MovieVO;

public class MovieCrawler {
	private final String RATING_TAG_NAVER = ".point > .num";
	private final String SHOWING_TAG_NAVER = ".opening > em";
	private final String POSTER_TAG_CINE = ".mov_poster > img";
	private final String VIDIOLINK_TAG_NAVER = ".video_thumb > li > .video_obj";
	private final String IFRAME_VIDIO_TAG_NAVER = ".video_ar > ._videoPlayer";
	private final String SUBINFOS_SPAN_TAG_CINE = ".mov_info > .sub_info > span";

	Elements elements;
	MovieVO movieVO;
	Crawler crawler;
	String title;
	CrawlerUtil util;

	public MovieCrawler(String title) {
		this.title = title;
		util = new CrawlerUtil(title);
	}
	
	public MovieVO getMovieVO(){
		movieVO = new MovieVO();
		movieVO.setTitle(title);
		movieVO.setRating(crawlRating());
		movieVO.setShowing(crawlShowingNow());
		movieVO.setSpectator(crawlSpectator());
		movieVO.setPosterLink(crawlPosterLink());
		movieVO.setIframeVidioLink(crawlIframeVidioLink());
		return movieVO;
	}
	
	public String getGraphLink(){
		return util.getMovieLink(site.cgv);
	}
	
	private double crawlRating() {
		crawler = new Crawler(util.getSearchLink(site.naver));
		elements = crawler.getElements(RATING_TAG_NAVER);
		return Double.parseDouble(elements.get(0).text());
	}

	private boolean crawlShowingNow() {
		crawler = new Crawler(util.getMovieLink(site.naver));
		elements = crawler.getElements(SHOWING_TAG_NAVER);
		if (elements == null || elements.size() == 0) {
			return false;
		} else if (elements.get(0).text().equals("상영중")) {
			return true;
		}
		return false;
	}

	private String crawlPosterLink() {
		String result = null;
		crawler = new Crawler(util.getMovieLink(site.cine));
		elements = crawler.getElements(POSTER_TAG_CINE);
		if(elements != null){
			result = elements.get(0).attr("src");
		}
		return result;
	}

	private String crawlIframeVidioLink() {
		String result = null;
		crawler = new Crawler(util.getMovieLink(site.naver));
		elements = crawler.getElements(VIDIOLINK_TAG_NAVER);
		try {
			crawler = new Crawler(elements.get(0).attr("abs:href"));
			elements = crawler.getElements(IFRAME_VIDIO_TAG_NAVER);
			result = elements.get(0).attr("abs:src");
		} catch (Exception e) {
			System.out.println("Can't find Video");
		}
		return result;
	}

	private String crawlSpectator() {
		int i = 0;
		String result = null;
		if(util.getMovieLink(site.cine) != ""){
			crawler = new Crawler(util.getMovieLink(site.cine));
			elements = crawler.getElements(SUBINFOS_SPAN_TAG_CINE);
			if(elements.get(2).text().length() >= 3){
				if (!elements.get(2).text().substring(elements.get(2).text().length() - 3, elements.get(2).text().length()).equals("관람가")) {
					i = -1;
				}
				if(elements != null && elements.size() > 6 + i){
					result = util.cutBasedColon(elements.get(6 + i).text());
				}
			}
		}
		return result;
	}
	
}
