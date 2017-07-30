package util.crawler;

import org.jsoup.select.Elements;

import rank.RankService;
import rank.RankVO;
import util.date.DateUtil;

public class RankCrawler {
	private String url = "http://movie.naver.com/movie/sdb/rank/rmovie.nhn";
	private String tag = ".tit3 > a";
	
	Elements elements;
	RankVO vo;
	RankVO voArr[];
	Crawler crawler;
	RankService service;
	
	public RankVO[] CrawlRank() {
		crawler = new Crawler(url);
		elements = crawler.getElements(tag);
		
		return elementsToVo(elements);
	}
	
	public RankVO[] elementsToVo(Elements elements){
		voArr = new RankVO[elements.size()];
		String today = DateUtil.getToDate();
		
		for (int i = 0; i < voArr.length; i++) {
			voArr[i] = new RankVO();
			
			voArr[i].setTitle(elements.get(i).text());
			voArr[i].setDate(today);
		}
		return voArr;
	}
}
