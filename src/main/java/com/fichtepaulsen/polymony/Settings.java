package com.fichtepaulsen.polymony;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    
    public final String ApplicationVersion = "1.2";
    
    // Public Settings
    public GridPane gameGridPane;
    public Label diceResult1;
    public Label diceResult2;
    public Label playerLabel;
    public Button nextButton;
    public Button rollDice;
}

