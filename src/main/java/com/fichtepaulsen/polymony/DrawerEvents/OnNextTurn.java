package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;

public class OnNextTurn extends Drawer{

    public OnNextTurn(GameInterface ga, Stage st) {
        super(ga, st);
    }

    @Override
    public void handle() {
    }
}
