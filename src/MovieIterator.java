import java.util.Iterator;
import java.util.NoSuchElementException;

public class MovieIterator implements Iterator<Movie> {
	
	private MovieDatabase movieDatabase;
	private Movie nextMovie;
	
	public MovieIterator(MovieDatabase movieDatabase) {
		this.movieDatabase = movieDatabase;
		this.nextMovie = movieDatabase.getMinRating();
	}
	
	public boolean hasNext() {
		return nextMovie != null;
	}
	
	public Movie next() {
		if (!hasNext()) {
			throw new NoSuchElementException("no next movie");
		}
		Movie currentMovie = nextMovie;
		nextMovie = movieDatabase.next(nextMovie);
		return currentMovie;
	}

}
