# Movie Database - Binary Search Tree

A Binary Search Tree implementation for organizing and managing a personal movie database, developed for CS 300 at UW-Madison.

## Project Overview

This project implements a custom Binary Search Tree (BST) data structure to create an efficient movie database system. Movies are organized by rating, enabling fast search, insertion, and deletion operations. The system also supports iteration through movies in sorted order and filtering by genre.

## Features

- **Binary Search Tree Organization**: Movies sorted by rating for efficient operations
- **Recursive BST Operations**: Add, remove, and traverse movies using recursive algorithms
- **Title-Based Lookup**: Search for movies by title with full tree traversal
- **Custom Iterator**: Iterate through movies in ascending order by rating
- **Genre Filtering**: Filter movies by genre using enhanced for-loops
- **Comparable Interface**: Custom comparison logic based on rating and title
- **Min/Max Rating Queries**: Quickly find lowest and highest rated movies

## Project Structure

### Provided Classes
- `Movie.java` - Models individual movies with title, rating, and genre
- `BSTNode.java` - Generic binary tree node with left/right child references

### Implemented Classes
- `MovieDatabase.java` - Main BST implementation with recursive operations
- `MovieIterator.java` - Custom iterator for in-order BST traversal

## How to Run

### Prerequisites
- Java 8 or higher
- Eclipse IDE (or any Java IDE)

### Running the System

1. Clone this repository:
```
   git clone https://github.com/CadenDay/movie-database-bst.git
```

2. Open Eclipse and import the project:
   - File → Open Projects from File System
   - Select the cloned folder

3. Run the main class:
   - Right-click `Main.java`
   - Run As → Java Application

### Example Usage
```java
// Create a new movie database
MovieDatabase db = new MovieDatabase();

// Add movies to the database
db.addMovie(new Movie("The Shawshank Redemption", 9.3, "Drama"));
db.addMovie(new Movie("The Godfather", 9.2, "Crime"));
db.addMovie(new Movie("The Dark Knight", 9.0, "Action"));
db.addMovie(new Movie("Pulp Fiction", 8.9, "Crime"));

// Look up a movie by title
Movie movie = db.lookup("The Dark Knight");
System.out.println(movie);  // Output: The Dark Knight (9.0) [Action]

// Get min and max rated movies
System.out.println("Lowest rated: " + db.getMinRating());
System.out.println("Highest rated: " + db.getMaxRating());

// Iterate through all movies in order
System.out.println("\nAll movies by rating:");
for (Movie m : db) {
    System.out.println(m);
}

// Filter by genre
ArrayList<Movie> crimeMovies = db.filterByGenre("Crime");
System.out.println("\nCrime movies: " + crimeMovies);

// Remove a movie
db.removeMovie(new Movie("Pulp Fiction", 8.9, "Crime"));
System.out.println("Movies after removal: " + db.size());
```

## Implementation Details

### Movie Comparison Logic
Movies are compared using the following rules:
1. **Different ratings**: Movie with higher rating is "larger"
2. **Same rating, different titles**: Alphabetically later title is "larger"
3. **Same rating and title**: Movies are considered equal (genre ignored)

### Binary Search Tree Operations

**Add Movie** (`addMovieHelper`)
- Recursively inserts movie maintaining BST property
- No duplicate movies allowed
- Non-balancing implementation (standard BST)
- Time Complexity: O(h) where h is tree height

**Remove Movie** (`removeMovieHelper`)
- Handles three cases: leaf node, one child, two children
- Uses successor method for two-child case
- Maintains BST structure after removal
- Time Complexity: O(h)

**Lookup by Title** (`lookupHelper`)
- Full tree traversal (not binary search)
- Required because search is by title, not rating
- Time Complexity: O(n)

**Get Next/Successor** (`nextHelper`)
- Finds smallest movie larger than given movie
- Used for iteration and removal operations
- Time Complexity: O(h)

### Recursive Helper Methods

All core operations are implemented recursively without loops:
- `countHelper` - Counts total nodes in subtree
- `getMinRatingHelper` - Finds minimum rating
- `getMaxRatingHelper` - Finds maximum rating
- `lookupHelper` - Searches for movie by title
- `addMovieHelper` - Inserts new movie
- `nextHelper` - Finds successor movie
- `removeMovieHelper` - Deletes movie from tree

### MovieIterator Implementation

Custom iterator implementing the `Iterator<Movie>` interface:
- Provides in-order traversal (ascending rating order)
- Uses `next()` method internally for sequential access
- Supports enhanced for-loop syntax
- Throws `NoSuchElementException` when exhausted

**Example iteration:**
```java
for (Movie m : movieDatabase) {
    System.out.println(m);  // Prints in ascending rating order
}
```

### Genre Filtering

`filterByGenre` method:
- Uses enhanced for-loop over the database
- Returns `ArrayList<Movie>` of matching movies
- Results are in ascending rating order
- Demonstrates practical use of custom iterator

## Learning Outcomes

This project demonstrates:
- Implementation of Binary Search Tree data structure
- Recursive algorithm design and implementation
- Use of `Comparable` interface for custom ordering
- Implementation of `Iterator` and `Iterable` interfaces
- Tree traversal algorithms (in-order, pre-order)
- Pointer manipulation in tree structures
- Edge case handling (empty tree, single node, etc.)

## Technical Constraints

- No Java collection classes allowed
- All BST operations must be recursive (no loops)
- Non-self-balancing BST implementation required
- No additional instance or static variables

## Course Information

- **Course:** COMP SCI 300 - Programming II
- **Institution:** University of Wisconsin-Madison
- **Semester:** Fall 2025

## Academic Integrity

This project was completed independently following UW-Madison's academic integrity policies. All code was written personally as part of coursework requirements.