package com.fichtepaulsen.polymony.Gamelogic;

import com.fichtepaulsen.polymony.Gamelogic.Dice.Dice;
import com.fichtepaulsen.polymony.Gamelogic.Fields.Field;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

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
    
    public Player getCurrentPlayer();
    
    public Player getNthPlayer(int index);
    
    public Player[] getAllPlayers();
    
    public Dice[] getAllDice();
    
    public Field[] getAllFields();
    
    public Field getNthField(int index);
}

