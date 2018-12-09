package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class ConfirmationWindow implements Initializable {

	@FXML
	private Label notificationBox;
	
	@FXML
	private Button okButton;

	@FXML
	private VBox notificationWindow;
	
	private Stage dialog;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Window primaryStage = null;	    	
    	dialog = new Stage();
    	
    	Scene dialogScene = new Scene(notificationWindow);
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setScene(dialogScene);
        dialog.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e) {
                dialog.close();
            }
        });        
        dialog.show();
    }
		
	@FXML
    void okClicked(ActionEvent event) {
		 dialog.close();
    }
	
	public Label getNotificationBox() {
		return notificationBox;
	}

	public void setNotificationText(String message) {
		notificationBox.setText(message);
	}

	public Button getOkButton() {
		return okButton;
	}

	public void setOkButton(Button okButton) {
		this.okButton = okButton;
	}
	
	public void closeWindow(Stage message) {
		
	}

	public void loadConfirmationWindow(String message) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/confirmationWindow.fxml"));
    	try {
    		loader.load();
    		ConfirmationWindow a = loader.getController();
    		a.setNotificationText(message);
		} 
    	catch (IOException e1) {
			System.out.println("Erro ao subir tela de confirma��o");
			e1.printStackTrace();
		}
	}
	
}
