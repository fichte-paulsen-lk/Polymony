package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
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
        gameLogic.startGame(2);

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
    }
}
