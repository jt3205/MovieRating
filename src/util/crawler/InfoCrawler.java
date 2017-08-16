package util.crawler;

import java.util.List;

import org.jsoup.select.Elements;

import info.InfoVO;

public class InfoCrawler {
	private final String SUBINFOS_TAG_CINE = ".mov_info > .sub_info";
	private final String ENGTITLE_TAG_CINE = ".mov_info > .tit_eng > span";
	private final String STORY_HTML_TAG_CINE = ".story_area > div";
	private final String SUBINFOS_SPAN_TAG_CINE = ".mov_info > .sub_info > span";
	
	Elements elements;
	InfoVO infoVO;
	Crawler crawler;
	String title;
	CrawlerUtil util;
	
	public InfoCrawler(String title) {
		this.title = title;
		util = new CrawlerUtil(title);
	}
	
	public InfoVO getSubInfoVO() { // 원래 다 분리해야되는데 마감일에 밀려서 코드 더러움 나중에 꼭 리팩토링할것
		int i = 0; 		// 관람가 등급이 없을경우 -1
		crawler = new Crawler(util.getMovieLink(site.cine));
		infoVO = new InfoVO();

		elements = crawler.getElements(ENGTITLE_TAG_CINE);
		if(elements != null){
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
			if (elements.get(2).text().length() >= 4 &&
					(elements.get(2).text().substring(elements.get(2).text().length() - 3, elements.get(2).text().length()).equals("관람가") ||
					elements.get(2).text().substring(elements.get(2).text().length() - 4, elements.get(2).text().length()).equals("관람불가"))) {
				infoVO.setGrade(elements.get(i + 2).text());
			} else {
				infoVO.setGrade(null);
				i -= 1;
			}
			
			infoVO.setGenre(elements.get(i + 3).text());

			
			if(elements.size() > i + 4){
				
				if (elements.get(i + 4).text().length() > 4 && elements.get(i + 4).text().substring(0, 4).equals("상영시간")) {
					infoVO.setShowtimes(util.cutBasedColon(elements.get(i + 4).text()));
				} else {
					infoVO.setShowtimes(null);
					i -= 1;
				}
			}
			
			if(elements.size() > i + 5){
				if (elements.get(i + 5).text().length() > 3 && elements.get(i + 5).text().substring(0, 3).equals("개봉일")) {
					infoVO.setReleaseDate(util.cutBasedColon(elements.get(i + 5).text()));
				} else {
					infoVO.setReleaseDate(null);
					i -= 1;
				}
			}

			elements = crawler.getElements(SUBINFOS_TAG_CINE);
			if(util.cutBasedColon(elements.get(3).text()).length() > 10){
				infoVO.setDirector(util.cutBasedColon(elements.get(3).text()).substring(0,9));
			}
			else{
				infoVO.setDirector(util.cutBasedColon(elements.get(3).text()));
			}
	
			if(elements.size() > 4){
				if (elements.get(4).text().substring(0, 2).equals("출연")) {
					infoVO.setPerformer(util.cutBasedColon(elements.get(4).text().substring(0, elements.get(4).text().length() - 5)));
				} else {
					infoVO.setPerformer(null);
				}
			}
			elements = crawler.getElements(STORY_HTML_TAG_CINE);
			infoVO.setStoryHtml(elements.get(0).html());

			return infoVO;
		}
		return new InfoVO();
	}
	
}
