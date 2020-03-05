package com.fichtepaulsen.polymony;

import com.fichtepaulsen.polymony.Gamelogic.Game;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

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
    
    public final int GameFields = 40;
    
    public final String ApplicationVersion = "1.2";

    //defaults for the height and the width of a normal field
    //note that width and heigh are for a field on the right or 
    //left rows, not the top or buttom
    public final double fieldHeight = 50f;
    public final double fieldWidth = 100f;
    
    public final double playerRadius = 8f;
    
    public final String WindowTitle = "Polymony";

    
    // Public Settings
    public int numberOfPlayers = 4;

    public GridPane gameGridPane;
    public Label diceResult1;
    public Label diceResult2;
    public Label playerLabel;
    public StackPane infoStackPane;
    public Button rollDice;
    public Game gameInteface;

    public static boolean isNextTurnButton = false;

    public static void toggleRollDiceButton() {  
        String buttonText = isNextTurnButton ? "Roll Dice" : "Next Turn";
        Settings.getInstance().rollDice.setText(buttonText);
        isNextTurnButton = !isNextTurnButton;
    }
}

