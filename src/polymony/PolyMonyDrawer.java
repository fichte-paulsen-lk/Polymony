package polymony;

import javafx.scene.Group;
import polymony.DrawerEvents.OnNewGame;

public class PolyMonyDrawer {
    OnNewGame onNewGame;
    
    public PolyMonyDrawer(Group root) {
        onNewGame = new OnNewGame(root);
    }
}
