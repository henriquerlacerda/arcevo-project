package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import objects.BookCase;
import objects.Movie;

public class NewMovie implements Initializable {

	    @FXML
	    private ImageView cover;

	    @FXML
	    private TextField name;
	    
	    @FXML
	    private TextField year;

	    @FXML
	    private TextField location;

	    @FXML
	    private TextArea synopsis;

	    @FXML
	    private ImageView bottonImage;
	    
	    @FXML
	    private HBox buttonBox;
	    
	    @FXML
	    private HBox topBox;
	    
	    @FXML
	    private VBox addMovie;
	    
	    public static BookCase teste = null;
	    
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    	bottonImage.fitWidthProperty().bind(buttonBox.widthProperty());
	    	cover.fitHeightProperty().bind(topBox.heightProperty());
	    }
	    
	    @FXML
	    void addNewMovie(ActionEvent event) {
	    	Movie movie = new Movie();
	    	movie.setCover(cover.getImage());
	    	movie.setName(name.getText());
	    	movie.setYear(Integer.parseInt(year.getText()));
	    	movie.setSynopsis(synopsis.getText());
	    	movie.setLocation(location.getText());
	    	
	    	teste = new BookCase(location.getText());
	    	teste.addMovie(movie);
	    	
	    	ConfirmationWindow a = new ConfirmationWindow();
    		a.loadConfirmationWindow("New Movie Added Successfully");
	    	clearFields();
	    }

	    @FXML
	    void backToHome(ActionEvent event) {
	    	MainScreen.changes.setCenter(null);
	    }

	    @FXML
	    void searchMovie(ActionEvent event) {	
			escolhaFilme();
	    }
	    
	    @FXML
	    void searchAction(KeyEvent event) {
	    	if (event.getCode()==KeyCode.ENTER) {
	    		escolhaFilme();
	        }	
	    }
	    
	    public VBox getAddMovie() {
	    	return this.addMovie;
	    }
	    
	    public void loadNewMovie() {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/newMovie.fxml"));
	    	try {
	    		addMovie = loader.load();
	    	} 
	    	catch (IOException e1) {
				System.out.println("Erro ao carregar arquivo de filme novo");
				e1.printStackTrace();
			}
	    }
	    
	    public void escolhaFilme() {
		   	MainScreen.changes.setCenter(null);
		   	ResultsPanel resultPanel = new ResultsPanel();
		   	resultPanel.loadResultsPanel(name.getText());
	    	MainScreen.changes.setCenter(resultPanel.getResultBox());
	    }
	    
	    public void loadNewMovieData(Document doc) {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/newMovie.fxml"));
	    	try {
	    		
	    		loader.load();
	    		NewMovie movie = loader.getController();
	    		Elements images = doc.select("div.poster img[src]");
				String link = images.attr("abs:src");
		    	Image image = new Image(link);
				movie.cover.setImage(image);
						
				Elements siteTitle= doc.getElementsByClass("title_wrapper");
				String[] sTitle = siteTitle.text().split("\\(");
				movie.name.setText(sTitle[0]);
				
				Element siteYear = siteTitle.select("a").first();
				movie.year.setText(siteYear.text());
				//movie.year.setDisable(true);
					
				Elements siteSypnosis= doc.getElementsByClass("summary_text");
				movie.synopsis.setText(siteSypnosis.text());
				movie.synopsis.setWrapText(true);
				//movie.synopsis.setDisable(true);
	    		
	    		addMovie = movie.addMovie;
	    	} 
	    	catch (IOException e1) {
				System.out.println("Erro ao carregar arquivo de filme novo");
				e1.printStackTrace();
			}
	    	
	    }
	    
	    public void clearFields() {
	    	 cover.setImage(null);
             name.clear();
             year.clear();
             synopsis.clear();
             location.clear();
	    }	  
}