package polymony.Main;

public class Point {
    private double x,y;
    public Point(double x, double y) {
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
     * Takes the field index and the width of the game field(amount of fields / 4)
     * Returns (x,y) as int "coordinates" - top left is (0,0), bottom right (width, width)
     * these coordinates are independent of the actual floating point coordinates for graphics
     * and can be used to calculated these using field width and other parameters
     */
    private static IntPair indexToPos(int index, int width) {
        
        //there are four strips on the field
        int strip = index / width;
        
        return 
            (strip == 0) ? new IntPair(index, 0) : 
            (strip == 1) ? new IntPair(width, index-width) : 
            (strip == 2) ? new IntPair(width-((index-width)%index), width) :
                           new IntPair(0, width-((index-width)%index));
        
    }
    
    /*
     * Takes the field index, the width of the game field(amount of fields / 4), the X and Y offset from the top left corner
     * of the screen to the top left corner of the first field. The width (dimX) and height (dimY) of the individual fields
     * should be given
     * method returns the coordinates of the top left corner of the field at the n-th index
     */
    public static Point indexToPoint(int index, int width, double offsetX, double offsetY, double dimX, double dimY) {
        IntPair pair = indexToPos(index, width);
        return new Point(offsetX+pair.n0*dimX,offsetY+pair.n1*dimY);
    }
    private static class IntPair{
        public int n0,n1;
        public IntPair(int a,int b){
            n0 = a;
            n1 = b;
        }
        
    
    }
} 
