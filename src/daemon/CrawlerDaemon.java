package daemon;

import java.util.Date;
import java.util.Timer;

import javax.servlet.http.HttpServlet;

public class CrawlerDaemon extends HttpServlet{
	private CrawlerJob job;
	private Timer jobScheduler;
	
	@Override
	public void init(){
		job = new CrawlerJob();
		jobScheduler = new Timer();

		jobScheduler.scheduleAtFixedRate(job, 0, 1000 * 60 * 60 * 24);
	}
	
	public void finalize() {
		jobScheduler.cancel();
	}
}
