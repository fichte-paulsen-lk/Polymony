package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class StartField extends Field{
    public StartField(){
        
    }

    @Override
    public void action(Game game) {
        Player activePlayer = game.getActivePlayer();
        activePlayer.changeBalance(2000);
    }
}
