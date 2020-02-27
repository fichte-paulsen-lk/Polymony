package com.fichtepaulsen.polymony.DrawerEvents;

import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import com.fichtepaulsen.polymony.Gamelogic.GameInterface;
import com.fichtepaulsen.polymony.Settings;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;
import javafx.geometry.Pos;


public class OnNextTurn extends Drawer{

    public OnNextTurn(GameInterface ga, Stage st) {
        super(ga, st);
    }

    @Override
    public void handle() {
        Settings.toggleRollDiceButton();
        gameLogic.nextTurn();
        getPlayerInfo();
    }
    
    public void getPlayerInfo() {
      /*  VBox info = Settings.getInstance().infoBox;
        Player currentPlayer = gameLogic.getCurrentPlayer();
        
        Label balance = new Label("Balance: " + currentPlayer.getBalance());
        Label prisonFreeCards = new Label("PrisonFreeCards: " + currentPlayer.getAmountPrisonFreeCard());
        Label utilitiesOwned = new Label("Owned Utilities: " + currentPlayer.getUtilitiesOwned());
        StackPane infoStack = new StackPane(balance, prisonFreeCards, utilitiesOwned);
        
        infoStack.setAlignment(balance, Pos.BOTTOM_CENTER);
        infoStack.setAlignment(prisonFreeCards, Pos.TOP_RIGHT);
        infoStack.setAlignment(utilitiesOwned, Pos.CENTER_RIGHT);
        
        info.getChildren().add(infoStack);*/
    }
    
}
