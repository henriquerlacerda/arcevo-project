package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public static BorderPane root;
		
	public static void main(String[] args) {
		launch(args); 
	}
	
	@Override
	public void start(Stage janela) throws IOException {	
		root = FXMLLoader.load(getClass().getResource("/layouts/mainScreen.fxml"));
		Scene scene = new Scene(root);
		janela.setTitle("Arcevo");
		janela.setScene(scene);
		janela.show();
		
		janela.maximizedProperty().addListener(new ChangeListener<Boolean>() {

		    @Override
		    public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
		        System.out.println("maximized:" + t1.booleanValue());
		    }
		});
	}	
	
}
