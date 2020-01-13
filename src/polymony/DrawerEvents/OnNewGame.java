package polymony.DrawerEvents;

import javafx.scene.Group;
import polymony.Main.Drawer;
import polymony.Main.GameInterface;;

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
