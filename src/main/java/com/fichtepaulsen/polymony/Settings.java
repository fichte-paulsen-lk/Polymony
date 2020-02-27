package com.fichtepaulsen.polymony;

import com.fichtepaulsen.polymony.Gamelogic.Game;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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
    
    public final int GameFields = 40;
    
    public final String ApplicationVersion = "1.2";
    
    // Public Settings
    public GridPane gameGridPane;
    public Label diceResult1;
    public Label diceResult2;
    public Label playerLabel;
    public VBox infoBox;
    public Button rollDice;
    public Game gameInteface;

    public static boolean isNextTurnButton = false;

    public static void toggleRollDiceButton() {  
        String buttonText = isNextTurnButton ? "Roll Dice" : "Next Turn";
        Settings.getInstance().rollDice.setText(buttonText);
        isNextTurnButton = !isNextTurnButton;
    }
}

