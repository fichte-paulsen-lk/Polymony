package com.fichtepaulsen.polymony;

import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import javafx.stage.Stage;

public abstract class Drawer {
    protected GameInterface gameLogic;
    protected Stage stage;
    
    public Drawer(GameInterface ga, Stage st) {
        this.gameLogic = ga;
        this.stage = st;
    }
    
    public abstract void handle();
}
