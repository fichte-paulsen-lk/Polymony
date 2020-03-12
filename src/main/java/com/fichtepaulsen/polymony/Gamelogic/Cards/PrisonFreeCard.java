package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class PrisonFreeCard extends Card{
    
    public PrisonFreeCard (String text,boolean community){
        super(text,community);
    }

    @Override
    public void action(Game game) {
        game.activePlayer.setAmountPrisonFreeCard(game.activePlayer.getAmountPrisonFreeCard()+1);
    }
}