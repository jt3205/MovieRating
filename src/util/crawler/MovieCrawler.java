package util.crawler;

import org.jsoup.select.Elements;

import info.InfoVO;
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
	private final String SUBINFOS_SPAN_TAG_CINE = ".mov_info > .sub_info > span";
	private final String SUBINFOS_TAG_CINE = ".mov_info > .sub_info";
	private final String ENGTITLE_TAG_CINE = ".mov_info > .tit_eng > span";

	private String naverSearchLink = "http://movie.naver.com/movie/search/result.nhn?query=title&ie=utf8";
	private String cgvSearchLink = "http://www.cgv.co.kr/search/?query=title";
	private String cineSearchLink = "http://www.cine21.com/search/?q=title";

	public enum site {
		naver, cgv, cine
	};

	Elements elements;
	MovieVO movieVO;
	MovieVO voArr[];
	InfoVO infoVO;
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

	public InfoVO crawlSubInfos() {
		int i = 0; // 관람가 등급이 없을경우 -1
		crawler = new Crawler(getMovieLink(site.cine));
		infoVO = new InfoVO();

		elements = crawler.getElements(ENGTITLE_TAG_CINE);
		infoVO.setTitle(title);
		String engTitle = "";
		for (int j = 0; j < elements.size(); j++) {
			if (j == elements.size() - 1) {
				engTitle += elements.get(j).text();
			} else {
				engTitle += elements.get(j).text() + "  ";
			}
		}
		infoVO.setEnglishTitle(engTitle);

		elements = crawler.getElements(SUBINFOS_SPAN_TAG_CINE);
		infoVO.setYear(Integer.parseInt(elements.get(i + 0).text()));
		infoVO.setCountry(elements.get(i + 1).text());
		if (elements.get(2).text().substring(elements.get(2).text().length() - 4, elements.get(2).text().length())
				.equals("관람가")) {
			infoVO.setGrade(elements.get(i + 2).text());
		} else {
			infoVO.setGrade(null);
			i = -1;
		}
		infoVO.setGenre(elements.get(i + 3).text());
		infoVO.setShowtimes(cutBasedColon(elements.get(i + 4).text()));
		infoVO.setReleaseDate(cutBasedColon(elements.get(i + 5).text()));
		infoVO.setSpectator(cutBasedColon(elements.get(i + 6).text()));

		elements = crawler.getElements(SUBINFOS_TAG_CINE);
		infoVO.setDirector(cutBasedColon(elements.get(3).text()));
		infoVO.setPerformer(cutBasedColon(elements.get(4).text().substring(0, elements.get(1).text().length() - 1)));

		return infoVO;
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

	public String cutBasedColon(String str) {
		return str.substring(str.indexOf(":") + 2);
	}
}
