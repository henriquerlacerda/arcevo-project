package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class MainScreen implements Initializable {
	
	@FXML
	private ImageView newMovie;
	
	@FXML
	private ImageView listMovies;
	
	@FXML
	private ImageView editMovies;
	
	@FXML
	private BorderPane mainApplication;
	
	public static BorderPane changes;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
        changes = mainApplication;
	}
	
	@FXML 
	private void newMovie(MouseEvent event)  {
		NewMovie movie = new NewMovie();
		movie.loadNewMovie();
		mainApplication.setCenter(movie.getAddMovie());
	}
	
	@FXML 
	private void seeListMovies(MouseEvent event)  {
		SideBar side = new SideBar();
		side.loadSideBar();
		MoviesView view = new MoviesView();
		view.loadMoviesView();
		mainApplication.setLeft(side.getSideBar());
		//mainApplication.setCenter(view.getMoviesView());
	}
	
	@FXML 
	private void editMovieList(MouseEvent event)  {
		System.out.println("teste3");
	}
	
	public BorderPane getMainApplication() {
		return this.mainApplication;
	}
	
	public void setMainApplication(BorderPane screen) {
		this.mainApplication = screen;
	}
	
}
