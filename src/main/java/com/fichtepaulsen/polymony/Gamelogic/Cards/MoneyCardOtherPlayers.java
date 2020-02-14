package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Cards.Card;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;
import com.fichtepaulsen.polymony.Gamelogic.Game;

public class MoneyCardOtherPlayers extends Card{
    int value;
    public MoneyCardOtherPlayers(String settext){
        super(settext);
    }
    @Override
    public void action(Game game){
        Player [] players = game.getPlayers();
        int activePlayerIndex = game.getActivePlayerIndex();
        for (int i=0;i<players.length;i++){
            players[i].setBalance(players[i].getBalance()-(value));
            players[activePlayerIndex].setBalance(players[activePlayerIndex].getBalance()+value);
        }
     }
}