package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public abstract class SpecialField extends Field {
    public abstract void action(Player activePlayer);
}
