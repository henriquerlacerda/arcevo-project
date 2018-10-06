package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainScreen implements Initializable {
	
	@FXML
	private ImageView newMovie;
	
	@FXML
	private ImageView listMovies;
	
	@FXML
	private ImageView editMovies;
	
	@FXML
	private BorderPane mainApplication;
	
	public static VBox screenNewMovie;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}
	
	@FXML 
	private void newMovie(MouseEvent event)  {
		try {
			screenNewMovie = FXMLLoader.load(getClass().getResource("/layouts/newMovie.fxml"));
			mainApplication.setCenter(screenNewMovie);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML 
	private void seeListMovies(MouseEvent event)  {
		System.out.println("teste2");
	}
	
	@FXML 
	private void editMovieList(MouseEvent event)  {
		System.out.println("teste3");
	}
	
	
}
