package movie;

public class MovieVO {
	private String title;
	private double rating;
	private boolean showing;
	private String spectator;
	private String posterLink;
	private String iframeVidioLink;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public boolean isShowing() {
		return showing;
	}
	public void setShowing(boolean showing) {
		this.showing = showing;
	}
	public String getSpectator() {
		return spectator;
	}
	public void setSpectator(String spectator) {
		this.spectator = spectator;
	}
	public String getPosterLink() {
		return posterLink;
	}
	public void setPosterLink(String posterLink) {
		this.posterLink = posterLink;
	}
	public String getIframeVidioLink() {
		return iframeVidioLink;
	}
	public void setIframeVidioLink(String iframeVidioLink) {
		this.iframeVidioLink = iframeVidioLink;
	}
}
