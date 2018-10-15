package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ResultsPanel implements Initializable {
	    
	@FXML
	private VBox resultsBox;
	
	private final ToggleGroup group = new ToggleGroup();
	private Elements links = null;
	public static Document link = null;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public VBox getResultBox() {
		return this.resultsBox;
	}

	public void  loadResultsPanel(String movie) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/resultsPanel.fxml"));
    	try {
    		resultsBox = loader.load();
    		resultsBox.getChildren().add(loadResultados(movie));
		} 
    	catch (IOException e1) {
			System.out.println("Erro ao carregar arquivo de resultados da pesquisa");
			e1.printStackTrace();
		}
	}
	    
	public GridPane loadResultados(String find) {
    	GridPane options = null;
		try {
				Document doc = Jsoup.connect("https://www.imdb.com/find?ref_=nv_sr_fn&q="+find+"&s=all").get();
				Element results = doc.select("div.findSection").first();
				Elements images = results.select("td.primary_photo img[src]");
				Elements titles = results.select("td.result_text");
				links = titles.select("a[href]");
				
				RadioButton [] select = new RadioButton[titles.size()];
				Text [] sTitle  = new Text[titles.size()];
		    	Image[] imagens = new Image[images.size()];
				ImageView [] view = new ImageView[links.size()];
						
				options = new GridPane();
			   	options.setHgap(10);
			   	options.setVgap(10);
			   	options.setAlignment(Pos.CENTER);
			   	options.setId("gridOptions");		   	
			   	
		    	for(int i=0;i<titles.size();i++) {
		    		
		    		select[i] = new RadioButton();
		    		select[i].setToggleGroup(group);
		    		select[i].setId(Integer.toString(i));
		    		
		    		imagens[i] = new Image(images.get(i).absUrl("abs:src").toString());
		    		view[i] = new ImageView();
		    		view[i].setImage(imagens[i]);
		    		select[i].setGraphic(view[i]);
		    		
		    		sTitle[i] = new Text(titles.get(i).text());
		    		sTitle[i].setFont(Font.font("Baskerville Old Face", 24));
		    		sTitle[i].setFill(Color.WHITESMOKE);
		    		
		    		options.add(select[i], 0, i);
		    		options.add(sTitle[i], 1, i);
		    	}
		    	getInformationLink();
		} 
    	catch (IOException e) {
    		System.out.println("Erro ao buscar título digitado");
		}
		
		return options;
	}
		
	public void getInformationLink() {
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
    	    public void changed(ObservableValue<? extends Toggle> ov,
    	        Toggle old_toggle, Toggle new_toggle) {
    	    	try {
    	    		MainScreen.changes.setCenter(null);
    	    		NewMovie movie = new NewMovie();
    		    	link = Jsoup.connect(links.get(group.getToggles().indexOf(group.getSelectedToggle())).absUrl("abs:href")).get();
    		    	movie.loadNewMovieData(link);
					MainScreen.changes.setCenter(movie.getAddMovie());
			}
			catch (IOException e1) {
				System.out.println("Erro ao carregar os dados do filme selecionado");
		    } 
    	    }
    	});
	}
		
}
