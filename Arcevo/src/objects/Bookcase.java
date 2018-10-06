package objects;

import java.util.List;

public class Bookcase {

	private List<Movie> bookcase;

	public Bookcase() {
		
	}
	
	public Bookcase(List<Movie> bookcase) {
		super();
		this.bookcase = bookcase;
	}

	public List<Movie> getBookcase() {
		return bookcase;
	}

	public void setBookcase(List<Movie> bookcase) {
		this.bookcase = bookcase;
	}
	
	public void addMovie(Movie movie) {
		this.bookcase.add(movie);
	}
	
	public void removeMovie(Movie movie) {
		this.bookcase.remove(movie);
	}
	
	public void bookcaseLength(Bookcase bookcase) {
		System.out.println(this.bookcase.size());
	}
}
