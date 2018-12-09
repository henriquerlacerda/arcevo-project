package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import objects.BookCase;

public class SideBar implements Initializable {
	
	@FXML
	private VBox sideBar;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}
	
	public VBox getSideBar() {
		return this.sideBar;
	}
	
	public void loadSideBar() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/sideBar.fxml"));
    	try {
    		sideBar = loader.load();
    		if(NewMovie.teste == null) {
    			sideBar.getChildren().add(emptyLists());
    		}
    		
		} 
    	catch (IOException e1) {
			System.out.println("Erro ao carregar arquivo de resultados da pesquisa");
			e1.printStackTrace();
		}
	}
	
	public Label emptyLists() {
		
		Label a = null;
		a = new Label("Não há filmes");
		a.setFont(Font.font("Baskerville Old Face", 24));
    	a.setTextFill(Color.WHITESMOKE);
		
    	return a;
	}
	
	public void listMovies() {
		Label [] listas = new Label[6];
	}

}
