package com.fichtepaulsen.polymony.Gamelogic.Cards;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

import com.fichtepaulsen.polymony.Gamelogic.Game;

public class PrisonFreeCards extends Card{
    private Player [] players;
    
    public PrisonFreeCards (String title,boolean community){
        super(title,community);
    }
    
    @Override
    public void action (Game game){
            players=game.getAllPlayers();
            players[game.getCurrentPlayer().getIndex()].setAmountPrisonFreeCard(players[game.getCurrentPlayer().getIndex()].getAmountPrisonFreeCard()+1);
    }
}
