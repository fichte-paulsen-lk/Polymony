package com.fichtepaulsen.polymony.Gamelogic.Cards;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

import com.fichtepaulsen.polymony.Gamelogic.Game;

public class PrisonFreeCards extends Card{
    private Player [] players;
    
    public PrisonFreeCards (String title){
        super(title);
    }
    
    @Override
    public void action (Game game){
            players=game.getPlayers();
            players[game.getActivePlayerIndex()].setAmountPrisonFreeCard(players[game.getActivePlayerIndex()].getAmountPrisonFreeCard()+1);
    }
}
