package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import com.fichtepaulsen.polymony.Settings;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OnRoll extends Drawer{

    public OnRoll(GameInterface ga, Stage st) {
        super(ga, st);
    }
    public void handle() {
       showDice();
    }
    
    public void showDice(){
        Label diceLabel1 = Settings.getInstance().diceResult1;
        Label diceLabel2 = Settings.getInstance().diceResult2;
        
        int[] diceResult = gameLogic.rollDices();
        System.out.println(diceResult[0] + " " + diceResult[1]);
        
        Image diceFace1 = new Image(getClass().getResourceAsStream("/img/Alea_" +  diceResult[0] + ".png"));
        Image diceFace2 = new Image(getClass().getResourceAsStream("/img/Alea_" +  diceResult[1] + ".png"));
        
        diceLabel1.setGraphic(new ImageView(diceFace1));
        diceLabel2.setGraphic(new ImageView(diceFace2));

    }
    
}
