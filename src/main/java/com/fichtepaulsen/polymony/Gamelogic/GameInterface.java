package com.fichtepaulsen.polymony.Gamelogic;

import com.fichtepaulsen.polymony.Gamelogic.Dice.Dice;
import com.fichtepaulsen.polymony.Gamelogic.Fields.Field;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;
import javafx.scene.paint.Color;

public interface GameInterface {
    
    /* 
    constructor: public Game()
    */
    
    
    /* Requires:
    *  Returns: integer array with all results of the rolled dices.
    *           the indices 0 and 1 are the normal dices.
    */
    public int[] rollDices();

    /* requires: -
       does: makes the next player active
    */
    public void nextTurn();

    /*
    requires: integer number of players. 
    does: initializes players,fields and dice to start the game.
    */
    public void startGame(int playerCount);
    
    /*
    requires:
    returns: player object from the active player.
             to get the player index: getIndex().
             to get the player position: getPosition().
    */
    public Player getCurrentPlayer();
    
    /*
    requires: index from a player 
    returns:  player object from players at the given index
    */ 
    public Player getNthPlayer(int index);
    
    /*
    requires: 
    returns: player array with all players
    */
    public Player[] getAllPlayers();
    
    /*
    requires: 
    returns: dice array with all dices
    */
    public Dice[] getAllDice();
    
    /*
    requires: 
    returns: field array with all fields
    */
    public Field[] getAllFields();
    
    /*
    requires: index from a field 
    returns:  field object from fields at the given index
    */ 
    public Field getNthField(int index);
    
    /*
    requires: Color index from json file
    returns:  Color object for the corresponding index
    */
    //public static Color getColor(int n);
    
    /*
    requires: 
    does:  current player buys the ownableField he stands on
    */
    public void buyField();
    
    /*
    requires: 
    returns: boolean if the current player is able to buy the street he stands on
    */
    public boolean isAbleToBuyField();
    
    /*
    requires: 
    does:  current player buys himself out of prison
    */
    public void prisonPayment();
    
    /*
    requires: 
    returns: boolean if the current player is able to buy himself out of prison
    */
    public boolean isAbleToBuyOutOfPrison();
    
    /*
    requires: integer for the last position of a player
              integer for the current position of a player
    returns:  boolean if a player past start in the last turn
    */
    public boolean pastStart(int lastPosition, int newPosition);
    
    /*
    requires: 
    does:  current player pays the rent on the ownableField he stands on
    */
    public void payRent();
    
    /*
    requires: 
    returns: boolean if the current player has to pay rent on the field he stands on
    */
    public boolean hasToPayRent();
    
    /*
    requires: 
    returns: boolean if the current player is able to pay the rent on the field he stands on
    */
    public boolean isAbleToPayRent();
}

