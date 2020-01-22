package com.fichtepaulsen.polymony;

import com.fichtepaulsen.polymony.DrawerEvents.OnNewGame;
import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import javafx.stage.Stage;

public class PolyMonyDrawer {
    
    OnNewGame onNewGame;
    
    public PolyMonyDrawer(Stage stage) {
        onNewGame = new OnNewGame(null, stage);
    }
}
