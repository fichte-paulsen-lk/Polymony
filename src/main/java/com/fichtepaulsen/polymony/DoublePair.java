package com.fichtepaulsen.polymony;

public class DoublePair {
    private double x,y;
    
    public DoublePair(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
    
    /*
     * Takes the field index, the width of the game field (amount of fields / 4), the X and Y offset from the top left corner
     * of the screen to the top left corner of the first field. The width (dimX) and height (dimY) of the individual fields
     * should be given
     * method returns the coordinates of the top left corner of the field at the n-th index
     */
    public static DoublePair indexToPoint(int index, int width, double offsetX, double offsetY, double dimX, double dimY) {
        IntPair pair = IntPair.indexToPos(index, width);
        
        System.out.println("Indices: " + pair.getFirst() + ", " +  pair.getSecond());
        
        return new DoublePair(offsetX + pair.getFirst()*dimX, offsetY + pair.getSecond()*dimY);
    }
} 
