package com.fichtepaulsen.polymony.Gamelogic.Fields;

public class Utility extends OwnableField {
    
    public Utility(String nameset, int indexset, int priceset){
        name=nameset;
        index=indexset;
        price=priceset;
    }
    
    public int action(int Dieceresult){
       return 0;  
    }
}
