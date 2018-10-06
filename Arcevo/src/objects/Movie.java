package objects;

import javafx.scene.image.Image;

public class Movie {

	private String name;
	private int year;
	private String synopsis;
	private String location;
	private Image cover;
	
	public Movie() {
		
	}
	
	public Movie(String name, int year, String synopsis, String location, Image cover) {
		super();
		this.name = name;
		this.year = year;
		this.synopsis = synopsis;
		this.location = location;
		this.cover = cover;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Image getCover() {
		return cover;
	}
	
	public void setCover(Image cover) {
		this.cover = cover;
	}
	
}
