package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class UtilityField extends OwnableField {
    
    public UtilityField(String nameset, int priceset){
        name=nameset;
        price=priceset;
    }

    @Override
    public void action(Game game) {
        Player activePlayer = game.getActivePlayer();
    }
    
    @Override
    public void buyField(Player currentPlayer, Field[] fields){
        this.setOwner(currentPlayer);
        currentPlayer.setTrainstationOwned(currentPlayer.getUtilitiesOwned() + 1);
    }
    
    @Override
    public int getPayPrice(int sum){
        if(getOwner().getUtilitiesOwned() == 1){
            return sum * 80;
        }
        else{
            return sum * 200;
        }
    }
}
