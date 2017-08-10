package daemon;

import java.util.TimerTask;

import util.crawler.MovieCrawler;
import util.crawler.RankCrawler;

public class CrawlerJob extends TimerTask{
	private MovieCrawler movieCrawler;
	private RankCrawler rankCrawler;
	public CrawlerJob() {
		movieCrawler = new MovieCrawler("title");	//휴가 갔다와서 수정할것..
	}
	
	@Override
	public void run() {
		//업데이트
	}

}
