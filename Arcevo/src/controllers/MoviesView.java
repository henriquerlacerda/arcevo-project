package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MoviesView implements Initializable {
	
	@FXML
	private VBox moviesView; 
	
	@FXML
	private StackPane mainInformation;

    @FXML
    private HBox slideView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public VBox getMoviesView() {
		return this.moviesView;
	}
	
	public void loadMoviesView() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/sideBar.fxml"));
    	try {
    		moviesView = loader.load();	
		} 
    	catch (IOException e1) {
			System.out.println("Erro ao carregar arquivo de resultados da pesquisa");
			e1.printStackTrace();
		}
	}
}
