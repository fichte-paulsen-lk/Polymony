package com.fichtepaulsen.polymony.Gamelogic.Cards;


import com.fichtepaulsen.polymony.Gamelogic.Player.Player;
import com.fichtepaulsen.polymony.Gamelogic.Game;

public class MoneyCardOtherPlayers extends Card{
    private final int value;
    
    public MoneyCardOtherPlayers(String title, int value,boolean community){
        super(title,community);
        this.value = value;
    }
    
    @Override
    public void action(Game game){
        for (Player player : game.getAllPlayers()) {
            if (player != game.activePlayer) {
                player.setBalance(player.getBalance() - (value));
                game.activePlayer.addBalance(value);
            }
        }
     }
}