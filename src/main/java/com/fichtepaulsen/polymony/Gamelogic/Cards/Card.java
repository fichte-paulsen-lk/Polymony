package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public abstract class Card {
    private String text;
    public Card(String text){
       this.text = text;
    }
    public abstract void action(Game game);
    
    public String getText(){
        return text;
    }
}
