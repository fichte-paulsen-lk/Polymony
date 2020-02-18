package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Cards.Card;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;
import com.fichtepaulsen.polymony.Gamelogic.Game;

public class MoneyCardOtherPlayers extends Card{
    private int value;
    
    public MoneyCardOtherPlayers(String title, int value,boolean community){
        super(title,community);
        this.value = value;
    }
    
    @Override
    public void action(Game game){
        Player [] players = game.getAllPlayers();
        int activePlayerIndex = game.getCurrentPlayer().getIndex();
        for (Player player : players) {
            player.setBalance(player.getBalance() - (value));
            players[activePlayerIndex].setBalance(players[activePlayerIndex].getBalance()+value);
        }
     }
}