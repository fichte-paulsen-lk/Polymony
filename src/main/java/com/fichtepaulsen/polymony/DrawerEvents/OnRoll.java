package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import com.fichtepaulsen.polymony.DoublePair;
import com.fichtepaulsen.polymony.DrawerController.GamefieldController;
import com.fichtepaulsen.polymony.Gamelogic.Fields.Field;
import com.fichtepaulsen.polymony.Gamelogic.Fields.OwnableField;
import com.fichtepaulsen.polymony.Gamelogic.Fields.StreetField;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;
import com.fichtepaulsen.polymony.IntPair;
import com.fichtepaulsen.polymony.PolyMonyDrawer;
import com.fichtepaulsen.polymony.PolyMonyPopup;
import com.fichtepaulsen.polymony.Settings;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import com.fichtepaulsen.polymony.Settings;
import javafx.scene.control.Button;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;
import javafx.util.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OnRoll extends Drawer {

    //interval of the x coordinates of the players
    private final double minX = 0.1;
    private final double maxX = 0.65;

    public OwnableField currentField;
  
    public OnRoll(GameInterface ga, Stage st) {
        super(ga, st);
    }

    //gets: nothing
    //does: logs errors etc.
    private void log(Level lvl, String msg) {
        Logger.getLogger(OnRoll.class.getName()).log(lvl, msg);
    }

    @Override
    public void handle() {
 
        Player currentPlayer = gameLogic.getCurrentPlayer();
        
        //save the old position before calling gamelogic and moving
        int oldPosition = currentPlayer.getPosition();

        //roll the dices and show them
        //changes currentPlayer position
        showDice();

        //move the player using an animation
        drawPlayerWithAnimation(currentPlayer, 1200, oldPosition);
    }

    //gets: the current player, the duration of animation, the oldPosition
    //does: moves the player with an animation
    public void drawPlayerWithAnimation(Player p, int duration, int oldPosition) {

        //make an ad hoc travel class to calculate how many corners have been
        //passed by the player and later access the path for the movement
        Travel travel = new Travel();
        log(Level.INFO, "oldPosition: " + oldPosition + ", newPosition: " + p.getPosition());
        
        //calculates which corners the Player passed
        travel.calculateCornersPassed(oldPosition, p.getPosition());
        
        //error handeling
        if (p == null) {
            log(Level.SEVERE, "Player is null");
        }
        else {
            if (getPlayerNode(p.getIndex()) == null) {
                log(Level.SEVERE, "Player's Shape is null");
            }
            //creates the Player animation and starts it
            new PathTransition(
                    Duration.millis(duration),
                    travel.getPlayerTransitionPath(p, oldPosition),
                    getPlayerNode(p.getIndex())).play();
        }
    }
    
    //inner class that calculates the animation path
    private class Travel {

        //one boolean value for each corner (0 is bottom right, then clockwise)
        boolean[] cornersPassed = new boolean[4];

        //empty constructor - cornersPassed is already initialized
        public Travel() {
        }

        //gets: a player who just moved and his old position
        //does: returns a path of the movement the player should
        //      perform, this is never empty because the player will
        //      move in every imaginable case (can't roll 0 or 40)
        public Path getPlayerTransitionPath(Player p, int oldPosition) {

            //contains the return value
            Path path = new Path();
            
            double playerRadius = Settings.getInstance().playerRadius;

            //calculate the starting position of the player, where the animation
            //starts from
            DoublePair startPosition = fieldPosition(oldPosition,
                    p.getIndex(),
                    playerRadius);

            //start from the player's current position
            path.getElements().add(0, new MoveTo(startPosition.getX(), startPosition.getY()));
                
            //flag as to whether any corners have been passed
            boolean passedCorner = false;

            //for a point where the player should change direction or stop
            DoublePair checkpoint;

            for (int i = 0; i < cornersPassed.length; ++i) {
                if (cornersPassed[i]) {

                    log(Level.INFO, "Passed corner " + i);

                    //calculate the x and y coordinates of the player if it
                    //were standing on the current corner
                    checkpoint = fieldPosition(i * Settings.getInstance().rowLength, p.getIndex(), playerRadius);

                    //add that corner to the path
                    path.getElements().add(new LineTo(checkpoint.getX(), checkpoint.getY()));
                    //a corner was passed
                    passedCorner = true;
                }
            }
            
            //the current position of the player
            checkpoint = fieldPosition(p.getPosition(), p.getIndex(), playerRadius);
            
            //also add the final position
            path.getElements().add(
                new LineTo(checkpoint.getX(), checkpoint.getY())
            );
                

            log(Level.INFO, "Path: ");
            //Logs the path
            for (int i = 0; i < path.getElements().size(); i++) {
                log(Level.INFO, path.getElements().get(i).toString());
            }
            
            //returns the created path
            return path;
        }
        
        //gets: the old and the new index of the player
        //does: fills the cornersPassed array with the corners that were passed
        public void calculateCornersPassed(int oldIndex, int newIndex) {
            int rowLength = Settings.getInstance().rowLength;
            
            //special case for passed over go
            if (newIndex < oldIndex) {
                cornersPassed[0] = true;
            }

            for (int i = 1; i < 4; ++i) {
                //if the player was previously before this corner but is now after 
                //it, the player has passed it
                if (oldIndex < i * rowLength && newIndex > i * rowLength) {
                    cornersPassed[i] = true; 
                    
                    log(Level.INFO, "Went over corner " + i);
                } 
            }
        }
    }

    //gets: the index of the Player
    //does: returns the PlayerNode at the given Index
    private Node getPlayerNode(int index) {
        return GamefieldController.getPlayerNode(Settings.getInstance().gameStackPane, index);
    }

    //gets: a field position, the player's index and an offset towards the top left of the field
    //does: returns a DoublePair with the coordiantes
    private DoublePair fieldPosition(int position, int playerIndex, double offset) {
        double longSide = Settings.getInstance().fieldWidth;
        double shortSide = Settings.getInstance().fieldHeight;
        
        //the players position inside the field relative to the top left corner, accounting for rotation
        DoublePair insideField = insideFieldOffsets(playerIndex, position, maxX, minX, shortSide, longSide);

        //the top left corner of the field 
        DoublePair fieldCorner = DoublePair.indexToPoint(position,
                Settings.getInstance().rowLength, shortSide, longSide);

        return new DoublePair(fieldCorner.getX() + insideField.getX() - offset, 
                              fieldCorner.getY() + insideField.getY() - offset);
    }

    //gets: index of player whose position in a field should be calculated,
    //      the position of the field, the minimum and maximum offset and
    //      and the short and longSide
    //does: calculates the position of the n-th player in a single field
    //      (0,0) is the top left corner the field and (1,1) the bottom right
    private DoublePair insideFieldOffsets(int player, int position, double min, double max, double shortSide, double longSide) {

        //number of players playing the game
        int numP = Settings.getInstance().numberOfPlayers;

        //the space between two player's paths
        double spacing;

        //variable coordinate is the minimum plus a certain spacing
        double variable;
        //checks if ther are more than one player
        //prevents division by zero
        if (numP > 1) {
            //space between the x-coords of players
            spacing = (max - min) / (double) (numP - 1);
            variable = min + player * spacing;
        } else {
            spacing = ((max - min) / 2);
            variable = min + spacing;
        }
        //calculates how often the position needs to bee rotated
        int rotations = (position / Settings.getInstance().rowLength + 2) % 4;
        //checks if the field the player stands on is a corner
        position %= Settings.getInstance().rowLength;
        
        double offsetX;
        double offsetY;
        
        double xLength;
        double yLength;
        
        //special case for corners
        if (position == 0) {
            //players stand on a diagonal
            offsetX = variable;
            offsetY = variable;
            //corners consist of two longsides
            xLength = longSide;
            yLength = longSide;
        }
        else {
            offsetX = 0.5f;
            offsetY = variable;
            
            xLength = shortSide;
            yLength = longSide;
        }
        //creates a temp variable for swapping
        double temp;
        //recalculate the offsets and swap the lengths
        for (int i = 0; i < rotations; i++) {
            temp = offsetX;
            offsetX = 1 - offsetY;
            offsetY = temp;
            
            temp = xLength;
            
            xLength = yLength;
            yLength = temp;
        }
        
        //the players are on the middle vertical axis with the calculated x coordinate
        
        log(Level.INFO, "Relative offset: "  + offsetX + ", " + offsetY);
        log(Level.INFO, "Offset factor: "  + xLength + ", " + yLength);
         
        return new DoublePair(offsetX * xLength, offsetY * yLength);
    }

    public void showDice() {
        Label diceLabel1 = Settings.getInstance().diceResult1;
        Label diceLabel2 = Settings.getInstance().diceResult2;

        int[] diceResult = gameLogic.rollDices();

        Image diceFace1 = new Image(getClass().getResourceAsStream("/img/Alea_" + diceResult[0] + ".png"));
        Image diceFace2 = new Image(getClass().getResourceAsStream("/img/Alea_" + diceResult[1] + ".png"));

        diceLabel1.setGraphic(new ImageView(diceFace1));
        diceLabel2.setGraphic(new ImageView(diceFace2));

        Settings.toggleRollDiceButton();
    } 
    
    public void showBuyButton(){
        if(gameLogic.isAbleToBuyField()){
            
        
        currentField = (OwnableField) this.getCurrentField();
        PolyMonyPopup.show 
        (
            "Do you want to buy " + currentField.getName()+ " for " + currentField.getPrice() +"?",
            (b) -> {
               if(b) gameLogic.buyField();
               PolyMonyDrawer.getInstance().onNextTurn.getPlayerInfo(gameLogic.getCurrentPlayer());
            }
        );
        }
        
    }
    
    public Field getCurrentField(){
        return gameLogic.getNthField(gameLogic.getCurrentPlayer().getPosition());
    }

}
