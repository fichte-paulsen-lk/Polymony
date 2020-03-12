package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

import com.fichtepaulsen.polymony.Gamelogic.Game;

public class JumpToCard extends Card {

    private final int position; //position on which the player jumps
    private Player[] players;
    private final boolean getMoney; 

    public JumpToCard(int position, String title,boolean getMoney, boolean community) {
        super(title, community);
        this.position = position;
        this.getMoney = getMoney;
    }

    @Override
    public void action(Game game) {
        players = game.getAllPlayers();
        if(game.pastStart(game.activePlayer.getPosition(),position) && getMoney){
            game.activePlayer.addBalance(200);
        }
        game.activePlayer.setPosition(position);

    }
}
