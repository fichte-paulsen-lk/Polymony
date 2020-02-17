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
    requires: 
    does: switches the activePlayerIndex to the next player
    */
    public void endTurn();
    
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
}

