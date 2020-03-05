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

public class OnRoll extends Drawer {

    //interval of the x coordinates of the players
    private final double minX = 0.35;
    private final double maxX = 0.65;
    private final double offX = 1;
    private final double offY = 1;
    private final double dimX = 50;
    private final double dimY = 100;

    //over which corners every player last moved
    private Travel[] lastTravels;

    public OnRoll(GameInterface ga, Stage st) {
        super(ga, st);

        lastTravels = new Travel[Settings.getInstance().numberOfPlayers];

        for (int i = 0; i < lastTravels.length; i++) {
            lastTravels[i] = new Travel();
        }
    }

    @Override
    public void handle() {
        //show the dice
        //System.out.println("=================================================");

        Player cPlayer = gameLogic.getCurrentPlayer();
        int oldPosition = cPlayer.getPosition();
        showDice();

        //move the current player to the new position
        //drawPlayer(cPlayer);
        if (lastTravels[cPlayer.getIndex()] == null) {
            System.out.println("Bakkpulver with " + cPlayer.getIndex() + " eggs");
        }
        lastTravels[cPlayer.getIndex()].calculateCornersPassed(oldPosition, cPlayer.getPosition());
        drawPlayerWithAnimation(cPlayer, 5000, oldPosition);
        //move the player
        //System.out.println("=================================================");

    }

    public void drawPlayerWithAnimation(Player p, int duration, int oldPosition) {
        if (p == null) {
            System.out.println("Player = null");
        } else {
            if (getPlayerNode(p.getIndex()) == null) {
                System.out.println("PlayNode is null");
            }
            if (lastTravels[p.getIndex()] == null) {
                System.out.println("Last Travels at " + p.getIndex() + " is null");
            }

            PathTransition anim = new PathTransition(
                    Duration.millis(duration),
                    (Shape) lastTravels[p.getIndex()].getPlayerTransitionPath(p, oldPosition),
                    getPlayerNode(p.getIndex()));

            if (anim == null) {
                System.out.println("bash ur head");
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

            //get the player's shape from the controller
            Node playerShape = getPlayerNode(p.getIndex());

            if (playerShape == null) {
                System.out.println("PlayNode is null");
            }

            DoublePair checkpoint;
            DoublePair startPosition = fieldPosition(oldPosition,
                    p.getIndex(),
                    Settings.getInstance().playerRadius);

            //start from the player's current position
            path.getElements().add(new MoveTo(startPosition.getX(), startPosition.getY()));

            boolean moved = false;

            for (int i = 0; i < 4; ++i) {
                if (cornersPassed[i]) {

                    System.out.println("Passed corner " + i);
                    //calculate the x and y coordinates of the player if it
                    //were standing on the current corner
                    checkpoint = fieldPosition(i * 10, p.getIndex(), Settings.getInstance().playerRadius);

                    //add that corner to the path
                    path.getElements().add(new LineTo(checkpoint.getX(), checkpoint.getY()));

                    moved = true;
                }
            }

            System.out.println();
            System.out.println("Player position passed");
            checkpoint = fieldPosition(p.getPosition(), p.getIndex(), Settings.getInstance().playerRadius);
            //add the player's final position to the path as well

            if (p.getPosition() != oldPosition || moved) {
                path.getElements().add(
                        new LineTo(checkpoint.getX(), checkpoint.getY())
                );
            }

            System.out.println("Path:");

            for (int i = 0; i < path.getElements().size(); i++) {
                System.out.println(path.getElements().get(i).toString());
            }

            return path;
        }

        public void calculateCornersPassed(int oldIndex, int newIndex) {
            int rowLength = Settings.getInstance().rowLength;

            int count = (((newIndex - oldIndex) % (4 * rowLength)) / rowLength);

            //passed over go
            if (newIndex < oldIndex) {
                cornersPassed[0] = true;
            }

            for (int i = 1; i < 4; ++i) {

                //if the player is before or on the i-th corner
                if (oldIndex <= i * rowLength && oldIndex > ((i - 1) * rowLength)) {
                    //set count-many corners from this one on
                    //as passed
                    for (int j = 0; j < count; j++) {
                        cornersPassed[i + j] = true;
                    }

                    //we don't need to iterate anymore
                    break;
                }
            }
        }
    }

    public void drawPlayerAt(int index) {
        if (gameLogic.getAllPlayers()[index] == null) {
            System.out.println("getAllPlayersArray = null");
        } else {
            //System.out.println("Drawing player at " + index);
            drawPlayer(gameLogic.getAllPlayers()[index]);
        }
    }

    private Node getPlayerNode(int index) {
        return GamefieldController.getPlayerNode(Settings.getInstance().gameStackPane, index);
    }

    //gets: a field position, the player's index and an offset towards the top left of the field
    //does: returns a DoublePair with the coordiantes
    private DoublePair fieldPosition(int position, int playerIndex, double offset) {
        System.out.println("Called fieldPosition");

        

        //the players position inside the field relative to the top left corner
        DoublePair insideField = fieldPos(playerIndex, maxX, minX);

        
        
        //calculate the row and column of the player's new position
        IntPair gridCoorinates = IntPair.indexToPos(position, Settings.getInstance().rowLength);

        double yInset;
        double xInset;

        double longSide = Settings.getInstance().fieldWidth;
        double shortSide = Settings.getInstance().fieldHeight;

        DoublePair fieldCorner;
                
        //if the player is on a the top or bottom row
        if (gridCoorinates.getSecond() == 0 || gridCoorinates.getSecond() == Settings.getInstance().rowLength) {
            xInset = insideField.getX() * shortSide - offset;
            yInset = insideField.getY() * longSide - offset;
            
            fieldCorner = DoublePair.indexToPoint(position,
                Settings.getInstance().rowLength, 0, 0, longSide, shortSide);
            
        } else {
            xInset = insideField.getY() * longSide - offset;
            yInset = insideField.getX() * shortSide - offset;
            
            fieldCorner = DoublePair.indexToPoint(position,
                Settings.getInstance().rowLength, 0, 0, shortSide, longSide);
        }
        
        System.out.println("insets relative to top left: " + xInset + ", " + yInset);
        System.out.println("corner of the field: " + fieldCorner.getX() + ", " + fieldCorner.getY());

        return new DoublePair(fieldCorner.getX() + xInset, fieldCorner.getY() + yInset);
    }

    //gets: player that moved
    //does: changes players X and Y coordinates to his new field
    public void drawPlayer(Player p) {

        int position = p.getPosition();

        //calculate the row and column of the player's new position
        IntPair playerGridCoordinates = IntPair.indexToPos(position, Settings.getInstance().rowLength);

        DoublePair pPos;

        if (getPlayerStrip(p.getPosition()) == 1 || getPlayerStrip(p.getPosition()) == 2) {
            //calculates the position of the player in the field
            pPos = fieldPos(p.getIndex(), maxX, minX);
        } else {
            pPos = fieldPos(p.getIndex(), minX, maxX);
        }

        //System.out.println("Position inside the field: " + pPos.getX() + ", " + pPos.getY() + " of " + p.getIndex());
        //get the player's shape that should be moved from the global gridpane in settings
        //using getPlayerNode from the controller
        Node playerShape = getPlayerNode(p.getIndex());

        if (playerShape == null) {
            System.out.println("Couldn't get the player's shape from the controller");
        }

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
            yInset = pPos.getY() * longSide - radius;

            /*
            if (getPlayerStrip(p.getPosition()) == 2) {
                xInset = shortSide - xInset;
                yInset = longSide  - yInset;
            }
             */
        } else {
            xInset = pPos.getY() * longSide - radius;
            yInset = pPos.getX() * shortSide - radius;

            /*
            if (getPlayerStrip(p.getPosition()) == 1) {
                //System.out.println("rotating");
                xInset = longSide  - xInset;
                yInset = shortSide - yInset;
            }
             */
        }

        //System.out.println("xInset = " + xInset + ", yInset = " + yInset);
        //set the margins for the playe's position inside the field
        GridPane.setMargin(playerShape, new Insets(yInset, 0, 0, xInset));
    }

    //gets: index of player whose position in a field should be calculated
    //does: calculates the position of the n-th player in a single field
    //      (0,0) is the top left corner the field and (1,1) the bottom right
    private DoublePair fieldPos(int player, double min, double max) {

        //number of players playing the game
        int numP = Settings.getInstance().numberOfPlayers;

        double spacing;

        //x coordinate of the player is the minimum x coordinate plus a certain spacing
        double y;

        if (numP > 1) {
            //space between the x-coords of players
            spacing = (max - min) / (double) (numP - 1);
            y = min + player * spacing;
        } else {
            spacing = ((max - min) / 2);
            y = min + spacing;
        }

        //System.out.println("Spacing = " + spacing);
        //the players are on the middle vertical axis with the calculated x coordinate
        return new DoublePair(0.5f, y);
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
