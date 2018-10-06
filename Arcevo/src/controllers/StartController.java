package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;

public class StartController implements Initializable {

	@FXML
	public static ProgressBar progressBar;
	public static Label startLabel;
	public static StackPane stackLoading;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		progressBar.setProgress(-1.0f);
	}
	
}
