package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import com.fichtepaulsen.polymony.Settings;

public class OnNextTurn extends Drawer{

    public OnNextTurn(GameInterface ga, Stage st) {
        super(ga, st);
    }

    @Override
    public void handle() {
        Settings.getInstance().rollDice.setVisible(true);
        Settings.getInstance().nextButton.setVisible(false);
        gameLogic.nextTurn();
    }
}
