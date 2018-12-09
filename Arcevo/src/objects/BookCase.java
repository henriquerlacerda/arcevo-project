package objects;

import java.util.ArrayList;
import java.util.List;

public class BookCase {

	private List<Movie> bookcase;
	private String name;

	public BookCase(String name) {
		this.name = name;
		bookcase = new ArrayList<Movie>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public BookCase(List<Movie> bookcase) {
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
	
	public void bookcaseLength(BookCase bookcase) {
		System.out.println(this.bookcase.size());
	}
}
