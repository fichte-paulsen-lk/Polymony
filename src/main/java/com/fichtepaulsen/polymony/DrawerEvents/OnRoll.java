package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import com.fichtepaulsen.polymony.DoublePair;
import com.fichtepaulsen.polymony.DrawerController.GamefieldController;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;
import com.fichtepaulsen.polymony.IntPair;
import com.fichtepaulsen.polymony.Settings;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class OnRoll extends Drawer{

    //interval of the x coordinates of the players
    private final double minX = 0.1;
    private final double maxX = 0.9;
    private final int width = 40;
    private final double offX = 1;
    private final double offY = 1;
    private final double dimX = 50;
    private final double dimY = 100;
    
    public OnRoll(GameInterface ga, Stage st) {
        super(ga, st);
    }
    
    @Override
    public void handle() {
        //move the current player to the new position
        drawPlayer(gameLogic.getCurrentPlayer());
    }
    
    public void drawPlayerAt(int index) {
        if (gameLogic.getAllPlayers()[index] == null) {
            System.out.println("NULLLLLLLLLLLLLL");
        }
        else {
            drawPlayer(gameLogic.getAllPlayers()[index]);
        }
    }
    //gets: player that moved 
    //does: changes players X and Y coordinates to his new field
    public void drawPlayer(Player p){
        
       //creates DoublePair to calculate the fields x and y coordinates
       DoublePair dP = new DoublePair(0,0);
       
       //calculates the fields x and y coordiantes
       dP = dP.indexToPoint(p.getPosition(), width, offX, offY, dimX, dimY);
       
       //calculates the position of the player in the field
       DoublePair pPos = fieldPos(p.getIndex());
       
       //get the player's shape that should be moved from the global gridpane in settings
       //using getPlayerNode from the controller 
       Node playerShape = GamefieldController.getPlayerNode(Settings.getInstance().gameGridPane, 
               p.getIndex());
       
       if (playerShape == null) {
           System.out.println("Couldn't get the player's shape from the controller");
       }
       
       GridPane.setMargin(playerShape, new Insets(pPos.getX(), 0, 0, pPos.getY()));
       
       //calculate the row and column of the player's new position
       IntPair playerGridCoordinates = IntPair.indexToPos(p.getIndex(), Settings.getInstance().rowLength);
       
        //aplies the new position
       GridPane.setConstraints(playerShape, playerGridCoordinates.getX(), playerGridCoordinates.getY());
       
       System.out.println("Tried to draw the player's new position");
    }
    
    //gets: index of player whose position in a field should be calculated
    //does: calculates the position of the n-th player in a single field
    //      (0,0) is the top left corner the field and (1,1) the bottom right
    private DoublePair fieldPos(int player) {
        
        //number of players playing the game
        int numP = gameLogic.getAllPlayers().length;
        
        //space between the x-coords of players
        double spacing = (maxX - minX) / (double) (numP - 1);

        //x coordinate of the player is the minimum x coordinate plus a certain spacing
        double x       = minX + player * spacing;
       
        //the players are on the middle vertical axis with the calculated x coordinate
        return new DoublePair(x, 0.5f);
    }

    //private Object getPlayerShape() {}
}
