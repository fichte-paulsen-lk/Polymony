package com.fichtepaulsen.polymony;

import com.fichtepaulsen.polymony.DrawerEvents.OnNewGame;
import javafx.stage.Stage;

public class PolyMonyDrawer {
    
    public OnNewGame onNewGame;
    
    private PolyMonyDrawer(Stage stage) {
        onNewGame = new OnNewGame(null, stage);
    }
    
    private static PolyMonyDrawer instance;

    private PolyMonyDrawer () {}
    
    public static synchronized PolyMonyDrawer getInstance () {
      return PolyMonyDrawer.instance;
    }
    
    public static void createInstance(Stage stage) {
        instance = new PolyMonyDrawer(stage);
    }
}
