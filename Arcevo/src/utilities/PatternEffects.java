package utilities;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PatternEffects {

	public static FadeTransition fade;
	
	public void fadeInEffect(Node conteudo) {
		fade = new FadeTransition(Duration.seconds(4),conteudo); 
	    fade.setFromValue(0);
	    fade.setToValue(1);
	    fade.setCycleCount(1);
		fade.play();
		System.out.println("FadeIn efetuado com sucesso");
	}
	
	public void fadeOutEffect(Node conteudo) {
		fade = new FadeTransition(Duration.seconds(4),conteudo);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.setCycleCount(1);
	    fade.play();
		System.out.println("FadeOut efetuado com sucesso");
	}
	
	public void fadeInOutFinish(Node conteudo, Stage load) {
		FadeTransition fade = new FadeTransition(Duration.seconds(4),conteudo);
		
		fade.setFromValue(0);
    	fade.setToValue(1);	
    	fade.setCycleCount(1);
		fade.play();
		
		fade.setOnFinished((e) -> {
			fade.setFromValue(1);
			fade.setToValue(0);
			fade.play();
			System.out.println("FadeInOut efetuado com sucesso");
			
			fade.setOnFinished((r) -> {
				load.hide();
			});
		});	
	}
	
}
