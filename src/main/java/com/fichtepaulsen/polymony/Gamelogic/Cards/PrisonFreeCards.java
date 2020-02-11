package com.fichtepaulsen.polymony.Gamelogic.Cards;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

import com.fichtepaulsen.polymony.Gamelogic.Game;

public class PrisonFreeCards extends Card{
    Player [] players;
    public PrisonFreeCards (String settext){
        super(settext);
    }
    
    @Override
    public void action (Game game){
            players=game.getPlayers();
            players[game.getActivePlayerIndex()].setAmountPrisonFreeCard(players[game.getActivePlayerIndex()].getAmountPrisonFreeCard()+1);
            //sets the amount of PrisonFreeCards that one player has one higher
    }
}
