package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class GoToPrisonField extends SpecialField {
    public GoToPrisonField() { }
    
    @Override
    public void action(Game game){
        
        game.getActivePlayer().setInPrison();
    }
}
