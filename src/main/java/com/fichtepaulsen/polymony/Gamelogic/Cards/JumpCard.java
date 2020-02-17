package com.fichtepaulsen.polymony.Gamelogic.Cards;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

import com.fichtepaulsen.polymony.Gamelogic.Game;

public class JumpCard extends Card {
    private int value; //value on how much the player moves from a field to another field
    private Player [] players;
    
    public JumpCard (int value, String title) {
      super(title);
      this.value = value;
    }

    @Override
    public void action(Game game) {
       players=game.getPlayers();
       players[game.getActivePlayerIndex()].setPosition(players[game.getActivePlayerIndex()].getPosition()+value);
    }         //activePlayer is set forward to a field
}
