package daemon;

import java.util.TimerTask;

import info.InfoDAO;
import info.InfoVO;
import movie.MovieService;
import movie.MovieVO;
import rank.RankService;
import rank.RankVO;
import util.crawler.InfoCrawler;
import util.crawler.MovieCrawler;
import util.crawler.RankCrawler;

public class CrawlerJob extends TimerTask{
	private MovieCrawler movieCrawler;
	private InfoCrawler infoCrawler;
	private RankCrawler rankCrawler = new RankCrawler();
	private MovieService movieService = new MovieService();
	private RankService rankService = new RankService();
	private InfoDAO infoDAO = new InfoDAO();
	private MovieVO movieVO[];
	private InfoVO infoVO[];
	
	public CrawlerJob() {
		movieCrawler = new MovieCrawler("title");	//휴가 갔다와서 수정할것..
	}
	
	@Override
	public void run() {
		update();
	}
	
	public void update(){
		try {
			rankService.insertMovies(rankCrawler.CrawlRank());
			
			String titles[] = rankService.getAllMovies();
			movieVO = new MovieVO[titles.length];
			infoVO = new InfoVO[titles.length];
			for (int i = 0; i < movieVO.length; i++) {
				movieCrawler = new MovieCrawler(titles[i]);
				movieVO[i] = movieCrawler.getMovieVO();
				System.out.println("현재영화 : "+titles[i]);
				infoCrawler = new InfoCrawler(titles[i]);
				System.out.println(i);
				infoVO[i] = infoCrawler.getSubInfoVO();
			}
			movieService.insertMovie(movieVO);
			infoDAO.insertInfo(infoVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
