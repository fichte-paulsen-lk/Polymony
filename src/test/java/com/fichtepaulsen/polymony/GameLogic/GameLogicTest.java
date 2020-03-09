package com.fichtepaulsen.polymony.Gamelogic;

import com.fichtepaulsen.polymony.Gamelogic.Cards.Card;
import com.fichtepaulsen.polymony.Gamelogic.Fields.Field;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;
import java.awt.Color;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameLogicTest {
    private Game game;
    
    public GameLogicTest() {  }
    
    @Before
    public void setUp() {
        game = new Game();
    }
    
    @After
    public void tearDown() {
        game = null;
    }

    @Test
    public void testStartGame() {
        game.startGame(2);
    }
    
    @Test
    public void testReadJSON() {
        Field[] field = null;
        
        try {
            field = game.readJson(40);
        } catch (Exception ex) {
            assertNotNull(ex);
        } 
        
        assertNotNull(field);
        assertEquals(field.length, 40);
    }
    
    @Test
    public void testIsDoublets() {
        int[] doublets1 = {5,2};
        int[] doublets2 = {2,2};
        
        assertFalse(Game.isDoublets(doublets1));
        assertTrue(Game.isDoublets(doublets2));
    }
    
    @Test
    public void testReadCardsJson() {
        Card[] cards = null;
                
        try {
            cards = game.readCardsJson(3);
        } catch (Exception ex) {
            assertNotNull(ex);
        }
        
        assertNotNull(cards);
        assertEquals(cards.length, 3);
    }
    
    @Test
    public void testAmountColoredFields() {
    
        HashMap<Color, Integer> map = game.amountOfColoredFields();
        
        assertEquals(map.get(Color.BLUE), new Integer(2));
        assertEquals(map.get(Color.RED), new Integer(3));
    } 
}
