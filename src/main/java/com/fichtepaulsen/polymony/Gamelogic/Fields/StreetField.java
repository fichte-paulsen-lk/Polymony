package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;
import javafx.scene.paint.Color;

public class StreetField extends OwnableField{
     private Color color;
     private int houseamount;
     
     public StreetField(String name, int price, Color color) {
         this.name = name;
         this.price = price;
         this.color = color;
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
    
    //public void buyField(Player currentPlayer, ){
        //this.setOwner(currentPlayer);
    //}
}