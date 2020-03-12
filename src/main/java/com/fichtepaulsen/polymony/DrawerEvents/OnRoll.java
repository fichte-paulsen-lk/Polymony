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
import com.fichtepaulsen.polymony.Settings;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class OnRoll extends Drawer{

    //interval of the x coordinates of the players
    private final double minX = 0.35;
    private final double maxX = 0.65;
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
        //show the dice
        //System.out.println("=================================================");
        showDice();
        
        //move the current player to the new position
        drawPlayer(gameLogic.getCurrentPlayer());
        //System.out.println("=================================================");
        
    }
    
    public void drawPlayerAt(int index) {
        if (gameLogic.getAllPlayers()[index] == null) {
            System.out.println("getAllPlayersArray = null");
        }
        else {
            //System.out.println("Drawing player at " + index);
            drawPlayer(gameLogic.getAllPlayers()[index]);
        }
    }
    
    
    //gets: player that moved 
    //does: changes players X and Y coordinates to his new field
    public void drawPlayer(Player p){
        
        int position = p.getPosition();
        
        //System.out.println("Player = " + p.getIndex());
        //System.out.println("Position = " + position);
       
        //creates DoublePair to calculate the x and y coordinates of the top left
        //corner of the position'th field
        DoublePair fieldCorner = DoublePair.indexToPoint(position, 
                Settings.getInstance().rowLength, offX, offY, dimX, dimY);
 
        //System.out.println("Top left of new field: " + fieldCorner.getX() + " , " + fieldCorner.getY());

        //calculates the position of the player in the field
        DoublePair pPos = fieldPos(p.getIndex());

        //System.out.println("Position inside the field: " + pPos.getX() + ", " + pPos.getY() + " of " + p.getIndex());
        //get the player's shape that should be moved from the global gridpane in settings
        //using getPlayerNode from the controller 
        Node playerShape = GamefieldController.getPlayerNode(Settings.getInstance().gameGridPane, 
                p.getIndex());

        if (playerShape == null) {
            System.out.println("Couldn't get the player's shape from the controller");
        }

        //calculate the row and column of the player's new position
        IntPair playerGridCoordinates = IntPair.indexToPos(position, Settings.getInstance().rowLength);

        //System.out.println("new grid coordinates: " + playerGridCoordinates.getFirst() + ", " + playerGridCoordinates.getSecond());
         //applies the new position
        GridPane.setConstraints(playerShape, playerGridCoordinates.getFirst(), playerGridCoordinates.getSecond());

        double yInset;
        double xInset;
        
        double longSide = Settings.getInstance().fieldWidth;
        double shortSide = Settings.getInstance().fieldHeight;
        
        double radius = Settings.getInstance().playerRadius;
        
        //if the player is on a row 
        if (playerGridCoordinates.getSecond() == 0 || playerGridCoordinates.getSecond() == Settings.getInstance().rowLength) {
            xInset = pPos.getX() * shortSide - radius;
            yInset = pPos.getY() * longSide  - radius;
        }
        else {
            xInset = pPos.getY() * longSide  - radius;
            yInset = pPos.getX() * shortSide - radius ;
        }
        
        //System.out.println("xInset = " + xInset + "yInset = " + yInset);
      
        //set the margins for the playe's position inside the field
        GridPane.setMargin(playerShape, new Insets(yInset, 0, 0, xInset));

        //System.out.println("Tried to draw the player's new position");
        }

        //gets: index of player whose position in a field should be calculated
        //does: calculates the position of the n-th player in a single field
        //      (0,0) is the top left corner the field and (1,1) the bottom right
        private DoublePair fieldPos(int player) {

         //number of players playing the game
         int numP = Settings.getInstance().numberOfPlayers;

         //space between the x-coords of players
         double spacing = (maxX - minX) / (double) (numP - 1);

         //x coordinate of the player is the minimum x coordinate plus a certain spacing
         double y       = minX + player * spacing;

         //the players are on the middle vertical axis with the calculated x coordinate
         return new DoublePair(0.5f, y);
    }

    //private Object getPlayerShape() {}

       
    
    public void showDice(){
        Label diceLabel1 = Settings.getInstance().diceResult1;
        Label diceLabel2 = Settings.getInstance().diceResult2;
        
        int[] diceResult = gameLogic.rollDices();
                
        Image diceFace1 = new Image(getClass().getResourceAsStream("/img/Alea_" +  diceResult[0] + ".png"));
        Image diceFace2 = new Image(getClass().getResourceAsStream("/img/Alea_" +  diceResult[1] + ".png"));
        
        diceLabel1.setGraphic(new ImageView(diceFace1));
        diceLabel2.setGraphic(new ImageView(diceFace2));
        
        Settings.toggleRollDiceButton();
    } 
}
