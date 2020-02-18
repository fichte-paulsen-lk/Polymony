
package com.fichtepaulsen.polymony.Gamelogic;

public class Container {
    int[]ergebnis;
    int activePlayerIndex;
    
    public Container(int[] e, int aPI){
        ergebnis = e;
        activePlayerIndex = aPI;
    }
    public int[] getErebnis(){
        return ergebnis;
    }
    public int getActivePlayerIndex(){
        return activePlayerIndex;
    }
}
