
public class Movie implements Comparable<Movie> {
	
	private String title;
	private String genre;
	private float rating;
	
	public Movie(String title, String genre, float rating) {
		if (title == null || genre == null || rating < 0 || rating > 10) {
			throw new IllegalArgumentException("invalid movie parameters");
		}
		this.title = title;
		this.genre = genre;
		this.rating = rating;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public float getRating() {
		return rating;
	}
	
	@Override
	public int compareTo(Movie other) {
		if (other.getRating() != this.getRating()) {
			if (other.getRating() > this.getRating()) {
				return -1;
			} else {
				return 1;
			}
		} else if (other.getRating() == this.getRating() && other.getTitle().equals(this.getTitle())) {
			return 0;
		} else {
			if (other.getTitle().compareTo(this.getTitle()) > 0) {
				return -1;
			} else {
				return 1;
			}
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		Movie movie = (Movie) other;
		return this.title.equals(movie.title) &&
				this.genre.equals(movie.genre) &&
				this.rating == movie.rating;
	}
	
	@Override
	public String toString() {
		return this.title + " (" + this.genre + ") : " + Float.toString(this.rating);
	}

}
