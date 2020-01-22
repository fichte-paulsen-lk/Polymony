package com.fichtepaulsen.polymony;

import javafx.scene.Group;
import com.fichtepaulsen.polymony.DrawerEvents.OnNewGame;

public class PolyMonyDrawer {
    OnNewGame onNewGame;
    
    public PolyMonyDrawer(Group root) {
        onNewGame = new OnNewGame(null, null);
    }
}
