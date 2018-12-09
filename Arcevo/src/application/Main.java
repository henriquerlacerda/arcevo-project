package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public static BorderPane root;
	public static Scene scene;
		
	public static void main(String[] args) {
		launch(args); 
	}
	
	@Override
	public void start(Stage janela) throws IOException {	
		root = FXMLLoader.load(getClass().getResource("/layouts/mainScreen.fxml"));
		scene = new Scene(root,980,700);
		janela.setTitle("Arcevo");
		janela.setScene(scene);
		janela.show();
	}	
	
}
