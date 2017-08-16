package util.crawler;


public class CrawlerUtil {
	Crawler crawler;
	String title;

	private final String MOVIELINK_TAG_NAVER = ".result_thumb > a";
	private final String MOVIELINK_TAG_CGV = ".sect-chart > ul > li > .box-image > a";
	private final String MOVIELINK_TAG_CINE = ".mov_list > li > a";
	
	private String naverSearchLink = "http://movie.naver.com/movie/search/result.nhn?query=title&ie=utf8";
	private String cgvSearchLink = "http://www.cgv.co.kr/search/?query=title";
	private String cineSearchLink = "http://www.cine21.com/search/?q=title";
	
	public CrawlerUtil(String title) {
		System.out.println(title);
		this.title = title;
	}
	
	String getMovieTag(site site) {
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

	String getSearchLink(site site) {
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

	String getMovieLink(site site) {
		try {
			String tag = getMovieTag(site);
			crawler = new Crawler(getSearchLink(site));
			return crawler.getElements(tag).get(0).attr("abs:href");
		} catch (Exception e) {
			System.out.println("영화 링크를 찾을 수 없습니다");
		}
		return "";
	}

	String cutBasedColon(String str) {
		return str.substring(str.indexOf(":") + 2);
	}
}
