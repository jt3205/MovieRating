package daemon;

import java.util.TimerTask;

import util.crawler.MovieCrawler;

public class CrawlerJob extends TimerTask{
	private MovieCrawler crawler;
	public CrawlerJob() {
		crawler = new MovieCrawler("title");	//휴가 갔다와서 수정할것..
	}
	
	@Override
	public void run() {
		//업데이트
	}

}
