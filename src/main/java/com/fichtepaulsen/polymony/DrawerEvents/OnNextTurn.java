package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import com.fichtepaulsen.polymony.Settings;

public class OnNextTurn extends Drawer{

    public OnNextTurn(GameInterface ga, Stage st) {
        super(ga, st);
    }

    public void handle() {
        Settings.getInstance().rollDice.setDisable(false);
        Settings.getInstance().nextButton.setVisible(false);
    }
}
