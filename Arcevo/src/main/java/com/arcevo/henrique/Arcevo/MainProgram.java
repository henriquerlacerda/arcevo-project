package com.arcevo.henrique.Arcevo;

import com.arcevo.henrique.buidwindow.MainWindow;

import javafx.stage.Stage;

public class MainProgram 
{
    public static void main( String[] args ) throws Exception
    {
    	MainWindow.launch(MainWindow.class); 
    	Stage window = new Stage();
    	MainWindow arcevo = new MainWindow();
    	arcevo.start(window);
    }
}
