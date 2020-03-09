package com.fichtepaulsen.polymony;

import com.fichtepaulsen.polymony.DrawerEvents.OnNewGame;
import com.fichtepaulsen.polymony.DrawerEvents.OnNextTurn;
import com.fichtepaulsen.polymony.DrawerEvents.OnRoll;
import com.fichtepaulsen.polymony.Gamelogic.Game;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PolyMonyDrawer {
    
    private Game gameInterface;
    
    public OnNewGame onNewGame;
    public OnNextTurn onNextTurn;
    public OnRoll onRoll;
    
    private Stage stage;
    
    private PolyMonyDrawer(Stage stage) {
        this.stage = stage;
        
        gameInterface = new Game();
        Settings.getInstance().gameInteface = this.gameInterface;

        onNewGame = new OnNewGame(gameInterface, stage);
        onNextTurn = new OnNextTurn(gameInterface, stage);
        onRoll = new OnRoll(gameInterface, stage);
    }
    
    public void showStage(Scene scene) {
        stage.setScene(scene);
        stage.show();
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
