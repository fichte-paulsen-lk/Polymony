package com.fichtepaulsen.polymony;

import com.fichtepaulsen.polymony.DrawerEvents.OnNewGame;
import javafx.stage.Stage;

public class PolyMonyDrawer {
    OnNewGame onNewGame;
    
    public PolyMonyDrawer(Stage stage) {
        onNewGame = new OnNewGame(stage);
    }
}
