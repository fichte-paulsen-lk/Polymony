package com.fichtepaulsen.polymony.Gamelogic.Fields;


import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class TrafficField extends OwnableField {

    @Override
    public void action(Game game) {
        Player activePlayer = game.getActivePlayer();
    }
    public TrafficField(String name, int price, int rent) {
         this.name = name;
         this.price = price;
         this.rent = rent;
    }
    
    public void buyField(Player currentPlayer){
        this.setOwner(currentPlayer);
        currentPlayer.setTrainstationOwned(currentPlayer.getTrainstationOwned() + 1);
    }
    
    public int getPayPrice(Player currentPlayer){
        int payPrice = rent;
        for (int i = 1; i < currentPlayer.getTrainstationOwned(); i++){
            payPrice *= 2;
        }
        return payPrice;
    }
    

}
