package util.crawler;

import org.jsoup.select.Elements;

import movies.MoviesService;
import movies.MoviesVO;
import util.date.DateUtil;

public class MovieCrawler {
	private String url = "http://movie.naver.com/movie/sdb/rank/rmovie.nhn";
	private String tags = ".tit3 > a";
	
	Elements elements;
	MoviesVO vo;
	MoviesVO voArr[];
	Crawler crawler;
	MoviesService service;
	
	public MoviesVO[] CrawlMovies() {
		crawler = new Crawler(url);
		elements = crawler.getElements(tags);
		
		return elementsToVo(elements);
	}
	
	public MoviesVO[] elementsToVo(Elements elements){
		voArr = new MoviesVO[elements.size()];
		String today = DateUtil.getToDate();
		
		for (int i = 0; i < voArr.length; i++) {
			voArr[i] = new MoviesVO();
			
			voArr[i].setTitle(elements.get(i).text());
			voArr[i].setDate(today);
		}
		return voArr;
	}
}
