package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

import com.fichtepaulsen.polymony.Gamelogic.Game;

public class JumpToCard extends Card {

    private int position; //position on which the player jumps
    private Player[] players;
    private boolean getMoney; 

    public JumpToCard(int position, String title,boolean getMoney, boolean community) {
        super(title, community);
        this.position = position;
        this.getMoney = getMoney;
    }

    @Override
    public void action(Game game) {
        players = game.getAllPlayers();
        Player currentPlayer = game.getCurrentPlayer();
        /*if(game.passedStart(currentPlayer.getPosition(),position) && getMoney){
        currentPlayer.changeBalance(200);
      }*/
        currentPlayer.setPosition(position);

    }
}
