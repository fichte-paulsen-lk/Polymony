package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class GoToPrisonCard  extends Card{
    
    public GoToPrisonCard(String text,boolean community){
        super(text,community);
    }

    @Override
    public void action(Game game) {
        game.getActivePlayer().setInPrison();
    }
}