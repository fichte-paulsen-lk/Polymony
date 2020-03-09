package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class PrisonFreeCard extends Card{
    
    public PrisonFreeCard (String text,boolean community){
        super(text,community);
    }

    @Override
    public void action(Game game) {
        Player activePlayer = game.getActivePlayer();
        activePlayer.setAmountPrisonFreeCard(activePlayer.getAmountPrisonFreeCard()+1);
    }
}