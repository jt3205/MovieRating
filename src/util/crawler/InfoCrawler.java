package util.crawler;

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
	
	public InfoVO getSubInfoVO() { // ���� �� �и��ؾߵǴµ� �����Ͽ� �з��� �ڵ� ������ ���߿� �� �����丵�Ұ�
		int i = 0; 		// ������ ����� ������� -1
		crawler = new Crawler(util.getMovieLink(site.cine));
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
		if (elements.get(2).text().substring(elements.get(2).text().length() - 3, elements.get(2).text().length()).equals("������")) {
			infoVO.setGrade(elements.get(i + 2).text());
		} else {
			infoVO.setGrade(null);
			i = -1;
		}
		infoVO.setGenre(elements.get(i + 3).text());
		infoVO.setShowtimes(util.cutBasedColon(elements.get(i + 4).text()));
		infoVO.setReleaseDate(util.cutBasedColon(elements.get(i + 5).text()));

		elements = crawler.getElements(SUBINFOS_TAG_CINE);
		infoVO.setDirector(util.cutBasedColon(elements.get(3).text()));
		infoVO.setPerformer(util.cutBasedColon(elements.get(4).text().substring(0, elements.get(4).text().length() - 5)));

		elements = crawler.getElements(STORY_HTML_TAG_CINE);
		infoVO.setStoryHtml(elements.get(0).html());

		return infoVO;
	}
	
}
