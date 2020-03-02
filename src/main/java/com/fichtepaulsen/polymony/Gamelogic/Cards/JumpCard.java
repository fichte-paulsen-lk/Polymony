package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

import com.fichtepaulsen.polymony.Gamelogic.Game;

public class JumpCard extends Card {

    private int value; //value on how much the player moves from a field to another field

    public JumpCard(int value, String title, boolean community) {
        super(title, community);
        this.value = value;
    }

    @Override
    public void action(Game game) {
   
        Player currentPlayer = game.getCurrentPlayer();
        /*if(game.passedStart(currentPlayer.getPosition(),currentPlayer.getPosition()+value)){
         currentPlayer.changeBalance(200);
       }*/
        currentPlayer.setPosition(currentPlayer.getPosition() + value);

    }         //activePlayer is set forward to a field
}
