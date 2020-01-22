package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import com.fichtepaulsen.polymony.Drawer;

public class OnRoll implements Drawer{
    GameInterface spielLogik;
    private Stage s;
    
    public OnRoll(Stage st, GameInterface sp) {
        this.s = st;
        this.spielLogik = sp;
    }
    
    public void handle() {
        
    }
    
}
