package com.fichtepaulsen.polymony;

import com.fichtepaulsen.polymony.Gamelogic.Game;
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
    
    public static void destroyInstance() {
        instance = null;
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

    public final int GameFields = 40;
    
    public final String ApplicationVersion = "1.2";

    
    // Public Settings
    public GridPane gameGridPane;
    public Label diceResult1;
    public Label diceResult2;
    public Label playerLabel;
    public Button nextButton;
    public Button rollDice;
    public Game gameInteface;
}

