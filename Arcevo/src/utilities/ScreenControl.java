package utilities;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
public class ScreenControl { 
	
	public ScreenControl() {
		
	}
	
	public AnchorPane mainScreen() throws IOException {
		
		AnchorPane main = new AnchorPane();
		main = FXMLLoader.load(getClass().getResource("/layouts/anchorPane.fxml"));
		
		return main;
	}
	
	public void setScreen(AnchorPane mainScreen, Node screen,int indexScreen) {
		
		mainScreen.getChildren().remove(indexScreen);
		mainScreen.getChildren().add(screen);
	
	}
}
