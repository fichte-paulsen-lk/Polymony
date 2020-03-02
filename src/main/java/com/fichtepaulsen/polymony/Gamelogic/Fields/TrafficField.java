package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class TrafficField extends OwnableField {
    public TrafficField(String name, int price, int rent) {
         this.name = name;
         this.price = price;
         this.rent = rent;
         this.currentRent = rent;
    }
    
    public void buyField(Player currentPlayer, Field[] fields){
        this.setOwner(currentPlayer);
        currentPlayer.setTrainstationOwned(currentPlayer.getTrainstationOwned() + 1);
        for(int i = 0; i < fields.length; i++){
            if(fields[i] instanceof TrafficField){
                Field temp = (TrafficField) fields[i];
                if(temp.getOwner() ==  currentPlayer){
                    currentRent *= 2;
                }
            }
        }
    }
    
    public int getCurrentRent(){
        return currentRent;
    }
}
