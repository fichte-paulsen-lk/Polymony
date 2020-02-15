package com.fichtepaulsen.polymony.Gamelogic.Dice;

import org.junit.Test;
import static org.junit.Assert.*;

public class NormalDiceTest {
    
    public NormalDiceTest() {
    }

    @Test
    public void testNormalDice() {
        NormalDice dice = new NormalDice();
        int result = dice.roll();
        
        assertTrue(result > 0 || result <= 6);
    }
}
