package daemon;

import java.util.TimerTask;

import util.crawler.MovieCrawler;
import util.crawler.RankCrawler;

public class CrawlerJob extends TimerTask{
	private MovieCrawler movieCrawler;
	private RankCrawler rankCrawler;
	public CrawlerJob() {
		movieCrawler = new MovieCrawler("title");	//�ް� ���ٿͼ� �����Ұ�..
	}
	
	@Override
	public void run() {
		//������Ʈ
	}

}
