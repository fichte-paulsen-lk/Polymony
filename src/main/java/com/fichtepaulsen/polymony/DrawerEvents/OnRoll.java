package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class OnRoll extends Drawer{

    public OnRoll(GameInterface ga, Stage st) {
        super(ga, st);
    }
    public void handle() {
       showDice(); 
    }
    
    public void showDice(){
        Parent parentRoot = null;
        
        try {
            parentRoot = FXMLLoader.load(getClass().getResource("/fxml/Gamefield.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(OnNewGame.class.getName()).log(Level.SEVERE, null, ex);
        }   }
    
}
