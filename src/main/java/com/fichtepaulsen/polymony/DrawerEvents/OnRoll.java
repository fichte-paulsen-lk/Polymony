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
    private final double offX = 1;
    private final double offY = 1;
    private final double dimX = 50;
    private final double dimY = 100;

    public OnRoll(GameInterface ga, Stage st) {
        super(ga, st);
    }

    private void log(Level lvl, String msg) {
        Logger.getLogger(OnRoll.class.getName()).log(lvl, msg);
    }

    @Override
    public void handle() {

        Player currentPlayer = gameLogic.getCurrentPlayer();
        
        //save the old position before calling gamelogic and moving
        int oldPosition = currentPlayer.getPosition();

        showDice();

        //move the player using an animation
        drawPlayerWithAnimation(currentPlayer, 1200, oldPosition);
    }

    public void drawPlayerWithAnimation(Player p, int duration, int oldPosition) {

        //make an ad hoc travel class to calculate how many corners have been
        //passed by the player and later access the path for the movement
        Travel travel = new Travel();
        log(Level.INFO, "oldPosition: " + oldPosition + ", newPosition: " + p.getPosition());
        
        travel.calculateCornersPassed(oldPosition, p.getPosition());

        if (p == null) {
            log(Level.SEVERE, "Player is null");
        }
        else {
            if (getPlayerNode(p.getIndex()) == null) {
                log(Level.SEVERE, "Player's Shape is null");
            }

            PathTransition anim = new PathTransition(
                    Duration.millis(duration),
                    travel.getPlayerTransitionPath(p, oldPosition),
                    getPlayerNode(p.getIndex()));

            if (anim == null) {
                log(Level.SEVERE, "Animation couldn't be started (PathTransition is null)");
            } else {
                anim.play();
            }
        }
    }

    private class Travel {

        //one boolean value for each corner (0 is bottom right, then clockwise)
        boolean[] cornersPassed = new boolean[4];

        //empty constructor - cornersPassed is already initialized
        public Travel() {
        }

        //gets: a player who just moved
        //does: returns a path of the movement the player should
        //      perform
        public Path getPlayerTransitionPath(Player p, int oldPosition) {

            //contains the return value
            Path path = new Path();

            //calculate the starting position of the player, where the animation
            //starts from
            DoublePair startPosition = fieldPosition(oldPosition,
                    p.getIndex(),
                    Settings.getInstance().playerRadius);

            //flag as to whether any corners have been passed
            boolean passedCorner = false;

            //for a point where the player should change direction or stop
            DoublePair checkpoint;

            for (int i = 0; i < 4; ++i) {
                if (cornersPassed[i]) {

                    log(Level.INFO, "Passed corner " + i);

                    //calculate the x and y coordinates of the player if it
                    //were standing on the current corner
                    checkpoint = fieldPosition(i * 10, p.getIndex(), Settings.getInstance().playerRadius);

                    //add that corner to the path
                    path.getElements().add(new LineTo(checkpoint.getX(), checkpoint.getY()));

                    passedCorner = true;
                }
            }

            checkpoint = fieldPosition(p.getPosition(), p.getIndex(), Settings.getInstance().playerRadius);
            //add the player's final position to the path as well

            //only add start and end if the player has actually moved,
            //otherwise return an empty path
            if (p.getPosition() != oldPosition || passedCorner) {

                //start from the player's current position
                path.getElements().add(0, new MoveTo(startPosition.getX(), startPosition.getY()));

                //also add the final position
                path.getElements().add(
                        new LineTo(checkpoint.getX(), checkpoint.getY())
                );
            }

            log(Level.INFO, "Path: ");

            for (int i = 0; i < path.getElements().size(); i++) {
                log(Level.INFO, path.getElements().get(i).toString());
            }

            return path;
        }

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

    //gets: index of player whose position in a field should be calculated
    //does: calculates the position of the n-th player in a single field
    //      (0,0) is the top left corner the field and (1,1) the bottom right
    private DoublePair insideFieldOffsets(int player, int position, double min, double max, double shortSide, double longSide) {

        //number of players playing the game
        int numP = Settings.getInstance().numberOfPlayers;

        //the space between two player's paths
        double spacing;

        //variable coordinate is the minimum plus a certain spacing
        double variable;

        if (numP > 1) {
            //space between the x-coords of players
            spacing = (max - min) / (double) (numP - 1);
            variable = min + player * spacing;
        } else {
            spacing = ((max - min) / 2);
            variable = min + spacing;
        }
        
        int rotations = (position / Settings.getInstance().rowLength + 2) % 4;
        position %= Settings.getInstance().rowLength;
        
        double offsetX;
        double offsetY;
        
        double xLength;
        double yLength;
        
        //special case for corners
        if (position == 0) {
            offsetX = variable;
            offsetY = variable;
            
            xLength = longSide;
            yLength = longSide;
        }
        else {
            offsetX = 0.5f;
            offsetY = variable;
            
            xLength = shortSide;
            yLength = longSide;
        }
        
        for (int i = 0; i < rotations; i++) {
            //recalculate the offsets and swap the lengths
            double temp = offsetX;
            offsetX = 1 - offsetY;
            offsetY = temp;
            
            temp = xLength;
            
            xLength = yLength;
            yLength = temp;
        }
        
        //System.out.println("Spacing = " + spacing);
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

    //TODO move this method into a better situated class
    private int getPlayerStrip(int index) {
        return index / Settings.getInstance().rowLength;
    }
}
