package com.fichtepaulsen.polymony;

import com.fichtepaulsen.polymony.Gamelogic.Dice.NormalDice;
import org.junit.Test;
import static org.junit.Assert.*;

public class NormalDiceTest {
    private NormalDice dice;
    
    public NormalDiceTest() {
        dice = new NormalDice();
    }

    @Test
    public void testNormalDice() {
        int result = dice.roll();
        
        assertTrue(result > 0 || result <= 6);
    }
}
