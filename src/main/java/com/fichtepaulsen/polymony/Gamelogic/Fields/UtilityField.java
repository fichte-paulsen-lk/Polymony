package com.fichtepaulsen.polymony.Gamelogic.Fields;

public class UtilityField extends OwnableField {
    
    public UtilityField(String nameset, int priceset){
        name=nameset;
        price=priceset;
    }
    
    public int action(int Dieceresult){
       return 0;  
    }
}
