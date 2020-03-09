package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class TrafficField extends OwnableField {

    @Override
    public void action(Game game) {
        Player activePlayer = game.getActivePlayer();
    }
}
