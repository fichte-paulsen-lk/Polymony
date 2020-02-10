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
    
    // Public Settings
    public GridPane gameGridPane;
    
}

