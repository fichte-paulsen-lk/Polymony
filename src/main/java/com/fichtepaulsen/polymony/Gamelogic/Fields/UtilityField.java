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
    
    //public void buyField(Player currentPlayer){
        //this.setOwner(currentPlayer);
        //currentPlayer.setTrainstationOwned(currentPlayer.getUtilitiesOwned() + 1);
    //}
}
