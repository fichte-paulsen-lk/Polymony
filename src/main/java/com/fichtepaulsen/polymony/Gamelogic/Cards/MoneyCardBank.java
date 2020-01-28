package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class MoneyCardBank  extends Card{
    int value;
    public MoneyCard(String text,int value){
        super(text);
        this.value = value;
    }

    @Override
    public void action(Game game) {
        Player [] players = game.getPlayers();
        int activePlayerIndex = game.getActivePlayerIndex();
        players[activePlayerIndex].setBalance(players[activePlayerIndex].getBalance()+value);
    }
}
