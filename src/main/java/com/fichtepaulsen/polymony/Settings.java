package com.fichtepaulsen.polymony;

import javafx.scene.layout.GridPane;

public class Settings {
      
    private static Settings instance;
    
    private Settings() { }
    
    public static synchronized Settings getInstance () {
      return Settings.instance;
    }
    
    public static void createInstance() {
        instance = new Settings();
    }
    
    // Const Settings 
    public final int WindowWidth = 500;
    public final int WindowHeight = 500;
    
    //one fourth of the total amount of field on the
    //game board (so that no non multiples of four can be used)
    public final int rowLength = 10;
    
    //number of players playing the game, can be made
    //non final if we want to change the number of 
    //players during runtime
    public final int numberOfPlayers = 4;
    
    // Public Settings
    public GridPane gameGridPane;
    
}

