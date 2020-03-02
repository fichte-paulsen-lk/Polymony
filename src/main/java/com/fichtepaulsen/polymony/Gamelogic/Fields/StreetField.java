package com.fichtepaulsen.polymony.Gamelogic.Fields;

import javafx.scene.paint.Color;

public class StreetField extends OwnableField{
     private Color color;
     private int housePrice;
     private int houseAmount;
     
     public StreetField(String name, int price, Color color) {
         this.name = name;
         this.price = price;
         this.color = color;
         this.houseAmount=0;
     }
     
     public Color getColor(){
        return this.color;
     }

    public int getHousePrice() {
        return housePrice;
    }

    public int getHouseAmount() {
        return houseAmount;
    }

    public void setHouseAmount(int houseAmount) {
        this.houseAmount = houseAmount;
    }
    
}