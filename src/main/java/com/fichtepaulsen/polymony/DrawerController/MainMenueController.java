package com.fichtepaulsen.polymony.DrawerController;

import com.fichtepaulsen.polymony.PolyMonyDrawer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainMenueController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }   
    
    public void startNewGame (Event e) {
        PolyMonyDrawer.getInstance().onNewGame.handle();
        
    }
}