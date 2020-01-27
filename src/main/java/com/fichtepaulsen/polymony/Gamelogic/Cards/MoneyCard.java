package com.fichtepaulsen.polymony.Gamelogic.Cards;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public class MoneyCard extends Card{
    int value;
    public MoneyCard(String text,int value){
        super(text);
        this.value = value;
    }

    @Override
    public void action(Player player) {
        player.setBalance(player.getBalance() + this.value);
    }
}
