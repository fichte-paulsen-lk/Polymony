package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import com.fichtepaulsen.polymony.Settings;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OnRoll extends Drawer{

    public OnRoll(GameInterface ga, Stage st) {
        super(ga, st);
    }
    
    @Override
    public void handle() {
       showDice();
    }
    
    public void showDice(){
        Label diceLabel1 = Settings.getInstance().diceResult1;
        Label diceLabel2 = Settings.getInstance().diceResult2;
        
        int[] diceResult = gameLogic.rollDices();
                
        Image diceFace1 = new Image(getClass().getResourceAsStream("/img/Alea_" +  diceResult[0] + ".png"));
        Image diceFace2 = new Image(getClass().getResourceAsStream("/img/Alea_" +  diceResult[1] + ".png"));
        
        diceLabel1.setGraphic(new ImageView(diceFace1));
        diceLabel2.setGraphic(new ImageView(diceFace2));
        
        Settings.getInstance().rollDice.setDisable(false);
        Settings.getInstance().nextButton.setVisible(true);

    } 
}
