package controllers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.net.ssl.HttpsURLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import objects.Movie;

public class NewMovie implements Initializable {

	    @FXML
	    private ImageView cover;

	    @FXML
	    private TextField year;

	    @FXML
	    private TextField location;

	    @FXML
	    private TextArea synopsis;

	    @FXML
	    private TextField name;

	    @FXML
	    private ImageView bottonImage;
	    
	    @FXML
	    private HBox buttonBox;
	    
	    @FXML
	    private HBox topBox;
	    
	    @FXML
	    private VBox addMovie;
	    
	    public Dimension screen;
	    public static String sUrlFilme = "";
	    
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    	bottonImage.fitWidthProperty().bind(buttonBox.widthProperty());
	    	bottonImage.fitHeightProperty().bind(buttonBox.heightProperty());
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
	    	confirmationWindow();
	    	
	    }

	    @FXML
	    void backToHome(ActionEvent event) {
	    	//.getChildren().remove(MainScreen.screenNewMovie);
	    }

	    @FXML
	    void searchMovie(ActionEvent event) {	
	    	try {
				escolhaFilme();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    public void escolhaFilme() throws MalformedURLException, IOException {
	    	final ProgressBar progress = new ProgressBar();
	    	sUrlFilme = "https://www.imdb.com/find?ref_=nv_sr_fn&q="+name.getText()+"&s=all";
	    	screen = Toolkit.getDefaultToolkit().getScreenSize();
	    	Window browser = null;	    	
	    	final Stage web = new Stage();
	    	VBox page = new VBox();
	    	WebView browserSet = new WebView();
	    	WebEngine webSurfer = browserSet.getEngine();
	    	
	    	VBox.setVgrow(browserSet, Priority.ALWAYS);
	    	page.getChildren().addAll(progress,browserSet);
	    	webSurfer.load(sUrlFilme);
	    	
	    	progress.setProgress(-1.0f);
	    	progress.progressProperty().bind(webSurfer.getLoadWorker().progressProperty());

	    	webSurfer.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
	                    @Override
	                    public void changed(ObservableValue ov, State oldState, State newState) {
	                        if (newState == State.SUCCEEDED) {
	                             // hide progress bar then page is ready
	                             progress.setVisible(false);
	                             while(webSurfer.getLocation().contains("find")) {
	                            	 try {
	                            		 System.out.println("Selecione filme");
										Thread.sleep(5);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
	                             }
	                             if(!webSurfer.getLocation().contains("find")) {
	                 				sUrlFilme = webSurfer.getLocation();
	                 				web.close();
	                 				try {
	                 					setDados(leituraDados());
	                 				} catch (IOException e) {
	                 					e.printStackTrace();
	                 				}		    				
	                 			}
	                        }
	                    }
	                });
	    	
	    	Scene scene = new Scene(page);
	    	
	    	web.initModality(Modality.APPLICATION_MODAL);
            web.initOwner(browser);
            web.setTitle("Choose the movie you looked for");
            web.setScene(scene);
            web.show();
            
	    }
	    
	    public String leituraDados() throws MalformedURLException, IOException {
	    	URL site = new URL(sUrlFilme);
	    	HttpsURLConnection connection = (HttpsURLConnection)site.openConnection();
			
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);
			
			String content;
			
			StringBuilder sb = new StringBuilder();

			while ((content = in.readLine()) != null) {
				sb.append(content);
				sb.append(System.lineSeparator());
			}
	    	
			content = sb.toString();
			
			if (in != null) {
                in.close();
                is.close();
                isr.close();
                connection.disconnect();
            }
			
			return content;			
	    }
	        
	    public void setDados(String content) {
	    	Document doc = Jsoup.parse(content);
			
	    	Elements images = doc.select("div.poster img[src]");
			String link = images.attr("abs:src");
	    	Image image = new Image(link);
			cover.setImage(image);
					
			Elements siteTitle= doc.getElementsByClass("title_wrapper");
			String[] sTitle = siteTitle.text().split("\\(");
			name.setText(sTitle[0]);
			
			Element siteYear = siteTitle.select("a").first();
			year.setText(siteYear.text());
			year.setDisable(true);
				
			Elements siteSypnosis= doc.getElementsByClass("summary_text");
			synopsis.setText(siteSypnosis.text());
			synopsis.setDisable(true);
	    }

		public void confirmationWindow() {
	    	Window primaryStage = null;	    	
	    	final Stage dialog = new Stage();
	    	VBox dialogVbox = new VBox(20);
	    	Button ok = notificationButton();
	    	Scene dialogScene = new Scene(dialogVbox, 190, 100);
	    	
            dialogVbox.getChildren().add(new Text("New Movie Added Successfully"));
            dialogVbox.getChildren().add(ok);
            dialogVbox.setAlignment(Pos.CENTER);
            
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(primaryStage);
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setScene(dialogScene);
            dialog.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent e) {
                    clearFields();
                    dialog.close();
                }
            });        
            dialog.show();
	    }
	    
	    public void clearFields() {
	    	 cover.setImage(null);
             name.clear();
             year.clear();
             synopsis.clear();
             location.clear();
	    }
	    
	    public Button notificationButton() {
	    	Button ok = new Button("Ok");
	    	
	    	ok.setPrefSize(50, 30);
	    	ok.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	               Stage close = (Stage)ok.getScene().getWindow();
	               clearFields();
	               close.close();
	            }
	        });
	    	return ok;
	    	
	    }
	  
}