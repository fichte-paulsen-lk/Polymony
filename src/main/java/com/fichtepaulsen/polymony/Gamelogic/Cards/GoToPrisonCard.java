package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Game;

public class GoToPrisonCard  extends Card{
    
    public GoToPrisonCard(String text,boolean community){
        super(text,community);
    }

    @Override
    public void action(Game game) {
        game.activePlayer.setInPrison();
    }
}