package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import com.fichtepaulsen.polymony.DoublePair;

public class OnRoll extends Drawer{

    //interval of the x coordinates of the players
    private final double minX = 0.1;
    private final double maxX = 0.9;
    
    public OnRoll(GameInterface ga, Stage st) {
        super(ga, st);
    }
    
    public void handle() {
        
    }
    
    //gets: index of player whose position in a field should be calculated
    //does: calculates the position of the n-th player in a single field
    //      (0,0) is the top left corner the field and (1,1) the bottom right
    private DoublePair fieldPos(int player) {
        //TODO hole Anzahl der Spieler vom GameInterface
        int numP = 4;
        
        //space between the x-coords of players
        double xSpacing = (maxX - minX) / (double) (numP-1);

        //x coordinate of the player is the minimum x coordinate plus a certain spacing
        double x        = minX + player*space;
        
        //Spieler steht immer in der Mitte des Feldes 
        return new DoublePair(x, 0.5); 
    }
    
}
