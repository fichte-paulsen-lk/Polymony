package com.fichtepaulsen.polymony.Gamelogic.Dice;

public class NormalDice extends Dice{
    @Override
    public int roll() {
        return (int) (Math.random() *6) + 1;
    }
}
