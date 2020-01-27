package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class GoToPrison extends SpecialField {
    public GoToPrison(){
}
    @Override
    public void action(Player activePlayer){
        activePlayer.setPosition(10);
        activePlayer.setIncarcerated(true);
    }
}
