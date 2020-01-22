package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import com.fichtepaulsen.polymony.Drawer;

public class OnNextTurn implements Drawer{
    GameInterface spielLogik;
    private Stage s;
    public OnNextTurn(Stage st,GameInterface sp){
        this.s = st;
        this.spielLogik = sp;
    }
    public void handle(){
        
    }
}
