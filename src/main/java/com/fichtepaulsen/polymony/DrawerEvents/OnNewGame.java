package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import com.fichtepaulsen.polymony.Drawer;

public class OnNewGame implements Drawer {
    GameInterface spielLogik;
    Stage s;
       
    public OnNewGame(Stage st,GameInterface sp) {
        this.s = st;
        this.spielLogik = sp;
    }
    
    public void handle () {
    }
}
