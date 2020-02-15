package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class MoneyCard  extends Card{
    int value;
    
    public MoneyCard(String settext,int value){
        super(settext);
        this.value = value;
    }

    @Override
    public void action(Game game) {
        Player [] players = game.getPlayers();
        int activePlayerIndex = game.getActivePlayerIndex();
        players[activePlayerIndex].setBalance(players[activePlayerIndex].getBalance()+value);
    }
}
