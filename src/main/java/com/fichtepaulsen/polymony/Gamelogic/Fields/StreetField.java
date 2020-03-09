package com.fichtepaulsen.polymony.Gamelogic.Fields;


import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;
import javafx.scene.paint.Color;

public class StreetField extends OwnableField{
     private Color color;
     private int houseamount;
     private int[] rents = new int[6];
     private boolean allStreetsOwned;
     
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


    @Override
    public void action(Game game) {
        Player activePlayer = game.getActivePlayer();
    }
     
    public int getHouseamount() {
        return houseamount;
    }

    public void setHouseamount(int houseamount) {
        this.houseamount = houseamount;
    }
    
    @Override
    public void buyField(Player currentPlayer, Field[] fields){
        int counter = 0;
        int expected;
        if(this.getColor() == color.BROWN || this.getColor() == color.BLUE){
            expected = 2;
        }    
        else{
            expected = 3;
        }
        
        this.setOwner(currentPlayer);
        
        for(int i = 0; i < fields.length; i++){
            if (fields[i] instanceof StreetField){
                StreetField f = (StreetField)fields[i];
                if(f.getColor() == this.getColor() && f.getOwner() == this.getOwner()){
                    counter++;
                }
            }
        }
        
        if(counter == expected){
            this.allStreetsOwned = true;
        }
    }
    
    public int[] getRents(){
        return rents;

    }
    
    @Override
    public int getPayPrice(int sum){
        int payPrice;
        if(this.houseamount == 0 && this.allStreetsOwned){
            payPrice = rents[0] * 2;
        }
        else{
            payPrice = rents[houseamount];
        }
        return payPrice;
    }
}
