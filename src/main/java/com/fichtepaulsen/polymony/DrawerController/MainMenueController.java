package com.fichtepaulsen.polymony.DrawerController;

import com.fichtepaulsen.polymony.PolyMonyDrawer;
import com.fichtepaulsen.polymony.Settings;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;

public class MainMenueController implements Initializable {
    
    @FXML
    private Slider playerCount;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       playerCount.valueProperty().addListener((obs, oldval, newVal) -> 
            playerCount.setValue(newVal.intValue()));
    }   
    
    public void startNewGame (Event e) {
        Settings.getInstance().numberOfPlayers = (int)this.playerCount.getValue();
        PolyMonyDrawer.getInstance().onNewGame.handle();
    }
}