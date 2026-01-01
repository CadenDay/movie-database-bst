import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MovieDatabase implements Iterable<Movie> {
	
	private BSTNode<Movie> root;
	private int size;
	
	public MovieDatabase() {
		size = 0;
		root = null;
	}
	
	public int size() {
		return size;
	}
	
	protected BSTNode<Movie> getRoot() {
		return root;
	}
	
	public Iterator<Movie> iterator() {
		return new MovieIterator(this);
	}
	
	public int count() {
		return countHelper(root);
	}
	
	protected static int countHelper(BSTNode<Movie> node) {
		if (node == null) {
			return 0;
		}
		return 1 + countHelper(node.getLeft()) + countHelper(node.getRight());
	}
	
	public Movie getMinRating() {
		if (root == null) {
			return null;
		}
		return getMinRatingHelper(root);
	}
	
	protected static Movie getMinRatingHelper(BSTNode<Movie> node) {
		if (node.getLeft() == null) {
			return node.getData();
		}
		return getMinRatingHelper(node.getLeft());
	}
	
	public Movie getMaxRating() {
		if (root == null) {
			return null;
		}
		return getMaxRatingHelper(root);
	}
	
	protected static Movie getMaxRatingHelper(BSTNode<Movie> node) {
		if (node.getRight() == null) {
			return node.getData();
		}
		return getMaxRatingHelper(node.getRight());
	}
	
	public Movie lookup(String title) {
		if (root == null) {
			return null;
		}
		return lookupHelper(root, title);
	}
	
	protected static Movie lookupHelper(BSTNode<Movie> node, String title) {
		if (node == null) {
			return null;
		}
		if (node.getData().getTitle().equals(title)) {
			return node.getData();
		}
		Movie leftMovie = lookupHelper(node.getLeft(), title);
		if (leftMovie != null) {
			return leftMovie;
		}
		return lookupHelper(node.getRight(), title);
	}
	
	public boolean addMovie(Movie movie) {
		if (root == null) {
			root = new BSTNode<Movie>(movie);
			size++;
			return true;
		}
		boolean result = addMovieHelper(root, movie);
		if (result) {
			size++;
		}
		return result;
	}
	
	protected static boolean addMovieHelper(BSTNode<Movie> node, Movie movie) {
		int compare = movie.compareTo(node.getData());
		if (compare < 0) {
			if (node.getLeft() == null) {
				node.setLeft(new BSTNode(movie));
				return true;
			} else {
				return addMovieHelper(node.getLeft(), movie);
			}
		} else if (compare > 0) {
			if (node.getRight() == null) {
				node.setRight(new BSTNode(movie));
				return true;
			} else {
				return addMovieHelper(node.getRight(), movie);
			}
		} else {
			return false;
		}
	}
	
	public Movie next(Movie movie) {
		if (root == null) {
			return null;
		}
		return nextHelper(root, movie);
	}
	
	protected static Movie nextHelper(BSTNode<Movie> node, Movie movie) {
		if (node == null) {
			return null;
		}
		int compare = movie.compareTo(node.getData());
		if (compare < 0) {
			Movie left = nextHelper(node.getLeft(), movie);
			if (left != null) {
				return left;
			} else {
				return node.getData();
			}
		} else {
			return nextHelper(node.getRight(), movie);
		}
	}
	
	public boolean removeMovie(Movie movie) {
		if (root == null) {
			return false;
		}
		try {
			root = removeMovieHelper(root, movie);
			size--;
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	protected static BSTNode<Movie> removeMovieHelper(BSTNode<Movie> node, Movie movie) {
		if (node == null) {
			throw new NoSuchElementException("movie was not found");
		}
		int compare = movie.compareTo(node.getData());
		if (compare < 0) {
			node.setLeft(removeMovieHelper(node.getLeft(), movie));
			return node;
		} else if (compare > 0) {
			node.setRight(removeMovieHelper(node.getRight(), movie));
			return node;
		} else {
			if (node.getLeft() == null) {
				return node.getRight();
			}
			if (node.getRight() == null) {
				return node.getLeft();
			}
			Movie nextMovie = nextHelper(node.getRight(), node.getData());
			BSTNode<Movie> right = removeMovieHelper(node.getRight(), nextMovie);
			BSTNode<Movie> newNode = new BSTNode<Movie>(nextMovie);
			newNode.setLeft(node.getLeft());
			newNode.setRight(right);
			return newNode;
		}
	}
	
	public ArrayList<Movie> filterByGenre(String genre) {
		ArrayList<Movie> organized = new ArrayList<>();
		for (Movie i : this) {
			if (i.getGenre().equals(genre)) {
				organized.add(i);
			}
		}
		return organized;
	}

}
