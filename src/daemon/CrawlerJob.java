package daemon;

import java.util.TimerTask;

import info.InfoDAO;
import info.InfoVO;
import movie.MovieService;
import movie.MovieVO;
import rank.RankService;
import rank.RankVO;
import util.crawler.MovieCrawler;
import util.crawler.RankCrawler;

public class CrawlerJob extends TimerTask{
	private MovieCrawler movieCrawler;
	private RankCrawler rankCrawler = new RankCrawler();
	private MovieService movieService = new MovieService();
	private RankService rankService = new RankService();
	private InfoDAO infoDAO = new InfoDAO();
	private MovieVO movieVO[];
	private RankVO rankVO;
	private InfoVO infoVO;
	
	public CrawlerJob() {
		movieCrawler = new MovieCrawler("title");	//휴가 갔다와서 수정할것..
	}
	
	@Override
	public void run() {
		//업데이트
	}
	
	public void update(){
		rankService.insertMovies(rankCrawler.CrawlRank());
		
		movieService.insertMovie(null);
	}
}
