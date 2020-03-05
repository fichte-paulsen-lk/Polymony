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
    private Label player = new Label();
    private Label balance = new Label();
    private Label prisonFreeCards = new Label();
    private Label utilitiesOwned = new Label();
    
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
        

        
        Player currentPlayer = gameLogic.getCurrentPlayer();
         player.setText("Player " +currentPlayer.getIndex());
         balance.setText("Balance: " + currentPlayer.getBalance());
         prisonFreeCards.setText("PrisonFreeCards: " + currentPlayer.getAmountPrisonFreeCard()); 
         utilitiesOwned.setText("Owned Utilities: " + currentPlayer.getUtilitiesOwned());
        Settings.getInstance().rightVBox.setAlignment(Pos.TOP_CENTER);
        
 
 
        
        Settings.getInstance().rightVBox.getChildren().addAll(player, balance, prisonFreeCards, utilitiesOwned);
    } 
    
}
