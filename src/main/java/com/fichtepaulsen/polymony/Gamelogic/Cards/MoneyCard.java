package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Game;

public class MoneyCard  extends Card{
    private final int value;
    
    public MoneyCard(String text,int value,boolean community){
        super(text,community);
        this.value = value;
    }

    @Override
    public void action(Game game) {
        game.activePlayer.addBalance(value);
    }
}
