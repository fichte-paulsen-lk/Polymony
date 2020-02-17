package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class GoToPrisonField extends SpecialField {
    public GoToPrisonField() { }
    
    @Override
    public void action(Player activePlayer){
        activePlayer.setPosition(10);
        activePlayer.setIncarcerated(true);
    }
}
