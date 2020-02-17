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
        //show the dice
        showDice();
        
        //move the current player to the new position
        drawPlayer(gameLogic.getCurrentPlayer());
        
    }
    
    public void drawPlayerAt(int index) {
        if (gameLogic.getAllPlayers()[index] == null) {
            System.out.println("NULLLLLLLLLLLLLL");
        }
        else {
            System.out.println("Drawing player at " + index);
            drawPlayer(gameLogic.getAllPlayers()[index]);
        }
    }
    
    
    //gets: player that moved 
    //does: changes players X and Y coordinates to his new field
    public void drawPlayer(Player p){
        
        int position = p.getPosition();
        System.out.println("Position = " + position);
       
        //creates DoublePair to calculate the fields x and y coordinates
        DoublePair dP = new DoublePair(0,0);

        //calculates the fields x and y coordiantes
        dP = dP.indexToPoint(position, width, offX, offY, dimX, dimY);

        System.out.println("Top left of new field: " + dP.getX() + " , " + dP.getY());

        //calculates the position of the player in the field
        DoublePair pPos = fieldPos(p.getIndex());

        System.out.println("Position inside the field: " + pPos.getX() + ", " + pPos.getY() + " of " + p.getIndex());
        //get the player's shape that should be moved from the global gridpane in settings
        //using getPlayerNode from the controller 
        Node playerShape = GamefieldController.getPlayerNode(Settings.getInstance().gameGridPane, 
                p.getIndex());

        if (playerShape == null) {
            System.out.println("Couldn't get the player's shape from the controller");
        }


        //calculate the row and column of the player's new position
        IntPair playerGridCoordinates = IntPair.indexToPos(position, Settings.getInstance().rowLength);

        System.out.println("new grid coordinates: " + playerGridCoordinates.getX() + ", " + playerGridCoordinates.getY());
         //applies the new position
        GridPane.setConstraints(playerShape, playerGridCoordinates.getX(), playerGridCoordinates.getY());

        double yInset = pPos.getY();
        double xInset = pPos.getX();
        
        //if the player is on a row 
        if (playerGridCoordinates.getY() == 0 || playerGridCoordinates.getY() == Settings.getInstance().rowLength) {
            yInset = pPos.getY() * Settings.getInstance().fieldWidth;
            xInset = pPos.getX() * Settings.getInstance().fieldHeight;
        }
        else {
            yInset = pPos.getX() * Settings.getInstance().fieldHeight;
            xInset = pPos.getY() * Settings.getInstance().fieldWidth;
        }
      
        //set the margins for the playe's position inside the field
        GridPane.setMargin(playerShape, new Insets(pPos.getY(), 0, 0, pPos.getX()));

        System.out.println("Tried to draw the player's new position");
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
         double x       = minX + player * spacing;

         //the players are on the middle vertical axis with the calculated x coordinate
         return new DoublePair(x, 0.5f);
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
        
        Settings.getInstance().rollDice.setVisible(false);
        Settings.getInstance().nextButton.setVisible(true);

    } 
}
