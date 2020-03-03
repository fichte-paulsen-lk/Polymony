package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;
import javafx.scene.paint.Color;

public class StreetField extends OwnableField{
     private Color color;
     private int houseamount;
     private int[] rents;
     
     public StreetField(String name, int price, Color color, int rent, int house1, int house2, int house3, int house4, int hotel) {
         this.name = name;
         this.price = price;
         this.color = color;
         rents[0] = rent;
         rents[1] = house1;
         rents[2] = house2;
         rents[3] = house3;
         rents[4] = house4;
         rents[5] = hotel;
     }
     
     public Color getColor(){
        return this.color;
     }
     
    public int getHouseamount() {
        return houseamount;
    }

    public void setHouseamount(int houseamount) {
        this.houseamount = houseamount;
    }
    
    public void buyField(Player currentPlayer){
        //this.setOwner(currentPlayer);
    }
    
    public int[] getRents(){
        return rents;
    }
}