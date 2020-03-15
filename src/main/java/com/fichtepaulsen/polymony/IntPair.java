package com.fichtepaulsen.polymony;

public class IntPair {
    private int first, second;
    
    public IntPair(int a,int b){
        first = a;
        second = b;
    }
    
    public int getFirst() {
        return first;
    }
    
    public int getSecond() {
        return second;
    }
    
    /*
     * Takes the field index and the width of the game field(amount of fields / 4)
     * Returns (x,y) as int "coordinates" - top left is (0,0), bottom right (width, width)
     * these coordinates are independent of the actual floating point coordinates for graphics
     * and can be used to calculated these using field width and other parameters
     */ 
    //gets: the field index and thw 
    public static IntPair indexToPos(int index, int width) {
        
        //translate all indices by half of the amount of fields
        index += (Settings.getInstance().rowLength * 2);
        index %= 4 * width;
        
        //there are four strips on the field, 
        //each of which is a different case for (row,col) for the grid
        int strip = index / width;
        
        return 
            (strip == 0) ? new IntPair(index, 0) : 
            (strip == 1) ? new IntPair(width, index-width) : 
            (strip == 2) ? new IntPair(width-((index-width)%width), width) :
                           new IntPair(0, width-((index-width)%width));  
    }
}
