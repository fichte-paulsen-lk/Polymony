/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polymony;

import javafx.scene.Group;
import polymony.DrawerEvents.OnNewGame;


/**
 *
 * @author joel.rose
 */
public class PolyMonyDrawer {
    OnNewGame onNewGame;
    
    public PolyMonyDrawer(Group root) {
        onNewGame = new OnNewGame(root);
    }
}
