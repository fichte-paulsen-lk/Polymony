package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Cards.Card;
import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class ActionField extends SpecialField{
    boolean community;
    boolean freeParking;
    public ActionField(boolean community,boolean freeParking){
        this.community = community;
        this.freeParking = freeParking;
    }
    @Override
    public void action(Game game) {
        if (!this.freeParking){
            if (this.community){
                
            }
        }
    }
    
}
