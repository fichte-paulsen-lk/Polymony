package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Game;

public class UtilityField extends OwnableField {
    
    public UtilityField(String nameset, int priceset){
        name=nameset;
        price=priceset;
    }

    @Override
    public void action(Game game) {
     
    }
}
