package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class UtilityField extends OwnableField {
    
    public UtilityField(String nameset, int priceset){
        name=nameset;
        price=priceset;
    }
    
    public int action(int Dieceresult){
       return 0;  
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
