package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Game;
import javafx.scene.paint.Color;

public class StreetField extends OwnableField{
     private final Color color;
     
     public StreetField(String name, int price, Color color) {
         this.name = name;
         this.price = price;
         this.color = color;
     }
     
     public Color getColor(){
        return this.color;
     }

    @Override
    public void action(Game game) {
        
    }
}