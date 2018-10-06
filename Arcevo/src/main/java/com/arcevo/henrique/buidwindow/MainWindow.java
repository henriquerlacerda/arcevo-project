package com.arcevo.henrique.buidwindow;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainWindow extends Application {

	private Group main;
	private Scene container;
	Dimension screenSize;
		
	public Group getMain() {
		return main;
	}

	public void setMain(Group main) {
		this.main = main;
	}

	public Scene getContainer() {
		return container;
	}

	public void setContainer(Scene container) {
		this.container = container;
	}

	public Dimension getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(Dimension screenSize) {
		this.screenSize = screenSize;
	}

	public MainWindow() {
		this.main = new Group();
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.container = new Scene(main,screenSize.getWidth(),screenSize.getHeight(),Color.LIGHTGRAY);
	}

	public MainWindow(Group main, Scene container, Dimension screenSize) {
		this.main = main;
		this.container = container;
		this.screenSize = screenSize;
	}

	@Override
	public void start(Stage content) throws Exception {
		content.setMaximized(true);
		content.setTitle("Arcevo Henrique DVDs e Livros");
		content.setScene(this.container);
		content.show();
	}

}
