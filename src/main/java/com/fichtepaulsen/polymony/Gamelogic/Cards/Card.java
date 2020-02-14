package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Game;

public abstract class Card {
    private String title;
    
    public Card(String title){
       this.title = title;
    }
    
    public abstract void action(Game game);
    
    public String getTitle(){
        return title;
    }
}
