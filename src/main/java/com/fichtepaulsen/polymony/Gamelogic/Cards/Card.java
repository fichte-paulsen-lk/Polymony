package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Game;

public abstract class Card {
    private String title;
    private boolean isCommunityCard;
    
    public Card(String title,boolean community){
       this.title = title;
       this.isCommunityCard= community;
    }
    
    public abstract void action(Game game);
    
    public String getTitle(){
        return title;
    }
    
    public boolean isCommunityCard(){
        return isCommunityCard;
    }//use cardType() function
    
    /*
    requires: -
    returns: the type of the card as String
    */
    public String cardType(){
        if(isCommunityCard){
            return "Community Chest";
        }else{
            return "Chance";
        }
    }
}
