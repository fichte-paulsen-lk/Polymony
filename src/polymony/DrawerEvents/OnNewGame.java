/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polymony.DrawerEvents;

import javafx.scene.Group;
import polymony.Drawer;
import polymony.GameInterface;;

/**
 *
 * @author joel.rose
 */
public class OnNewGame implements Drawer {
    GameInterface spielLogik;
    Group root;
       
    public OnNewGame(Group g) {
        root = g;
    }
    
    public void handle () {
        System.out.println("OnNewGame!");
    }
}
