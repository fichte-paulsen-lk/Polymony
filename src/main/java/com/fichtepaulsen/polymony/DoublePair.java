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

    private static DoublePair topLeftCorner(double offsetX, double offsetY, double shortSide, int x, int y) {
        return new DoublePair(x*shortSide + offsetX, y*shortSide + offsetY);
    }
    /*
     * gets: the one dimesnional field index, the width of the game field (amount of fields / 4)
     *       the lengths of the short and long sides of individual fields should be given
     * does: returns the coordinates of the top left corner of the field at the n-th index
     */
    public static DoublePair indexToPoint(int position, int width, double lengthShortSide, double lengthLongSide) {
        IntPair gridCoordinates = IntPair.indexToPos(position, width);

        DoublePair ret;

        double cornerOffsetX = 0;
        double cornerOffsetY = 0;

        int x = gridCoordinates.getFirst();
        int y = gridCoordinates.getSecond();

        //account for the corners having a long side
        if (gridCoordinates.getFirst() > 0) {
            cornerOffsetX = lengthLongSide;
            x--;
        }
        if (gridCoordinates.getSecond() > 0) {
            cornerOffsetY = lengthLongSide;
            y--;
        }

        /*
        if (gridCoordinates.getFirst() == 0 || gridCoorinates.getFirst() == width) {
            ret = topLeftCorner(cornerOffsetX, cornerOffsetY, lengthShortSide, x, y);
            /*
            ret = new DoublePair((gridCoordinates.getFirst()-1)*lengthShortSide + cornerOffsetX,
                                 gridCoordinates.getSecond()*lengthShortSide + cornerOffsetY);

        }
        else if (gridCoordinates.getSecond() == 0 || gridCoorinates.getSecond() == width) {
            ret = topLeftCorner(cornerOffsetX, cornerOffsetY, lengthShortSide, x, y);
            /*
            ret = new DoublePair(gridCoordinates.getFirst()*lengthShortSide + cornerOffsetX,
                                 (gridCoordinates.getSecond()-1)*lengthShortSide + cornerOffsetY);

        }
        */

        return topLeftCorner(cornerOffsetX, cornerOffsetY, lengthShortSide, x, y);
    }
}
