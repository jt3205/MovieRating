package daemon;

import java.util.TimerTask;

import util.crawler.MovieCrawler;

public class CrawlerJob extends TimerTask{
	private MovieCrawler crawler;
	public CrawlerJob() {
		crawler = new MovieCrawler("title");	//�ް� ���ٿͼ� �����Ұ�..
	}
	
	@Override
	public void run() {
		//������Ʈ
	}

}
