package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import com.fichtepaulsen.polymony.Settings;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class OnNewGame extends Drawer {

    public OnNewGame(GameInterface ga, Stage st) {
        super(ga, st);
    }
    
    @Override
    public void handle () {
        
        //actually start the game with the set amount of players from the settings
        this.gameLogic.startGame(Settings.getInstance().numberOfPlayers);
        
        Parent parentRoot = null;
        
        try {
            parentRoot = FXMLLoader.load(getClass().getResource("/fxml/Gamefield.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(OnNewGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(parentRoot);
        scene.getStylesheets().add("/styles/Styles.css");
        
        this.stage.setScene(scene);
        this.stage.show();
        this.stage.centerOnScreen();    
    }
}
