package com.fichtepaulsen.polymony.Gamelogic;

import com.fichtepaulsen.polymony.Gamelogic.Dice.Dice;
import com.fichtepaulsen.polymony.Gamelogic.Fields.Field;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;
import javafx.scene.paint.Color;
import com.fichtepaulsen.polymony.Gamelogic.Fields.OwnableField;

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
             to get the player balance: getBalance().
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
              to get the owner of a field: getOwner().
              to see if the the field has hypothek: isHypothek().
    */ 
    public Field getNthField(int index);
   
    /*
    requires: index from a field 
    returns:  nothing
    effect: buys a house on the given field if the active player owns the Field
    */ 
     public void buyHouse (int fieldIndex);
    
     /*
     requires: Player attribute
     returns: OwnableField- Array with all OwnableFields the player owns
     effect: nothing 
     */
     public OwnableField [] getFieldsOwnedBy (Player player);
    
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
    requires: index of the field where a player wants to add a mortgage to
    does:     set mortgage on the field at the given fieldIndex 
    */
    public void addMortgage(int fieldIndex);
    
    /*
    requires: index of the field where a player wants to add a mortgage
    returns:  boolean if the current player is able to add a mortgage at a field at fieldIndex
    */
    public boolean isAbleToAddMortgage(int fieldIndex);
    
    /*
    requires: index of the field where a player wants to remove the mortgage 
    does:     remove mortgage from the field at the given fieldIndex 
    */
    public void removeMortgage(int fieldIndex);
    
    /*
    requires: index of the field where a player wants to remove the mortgage
    returns:  boolean if the current player is able to remove a mortgage from a field at fieldIndex
    */
    public boolean isAbleToRemoveMortgage(int fieldIndex);
}

