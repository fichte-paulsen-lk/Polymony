package com.fichtepaulsen.polymony.Gamelogic;

import com.fichtepaulsen.polymony.Gamelogic.Cards.Card;
import com.fichtepaulsen.polymony.Gamelogic.Fields.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameLogicTest {
    private Game game;
    
    public GameLogicTest() { 
       
    }
    
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
            field = game.readJson();
            
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, ex.getMessage());
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
            game.readCardsJson();
        } catch (Exception ex) {
            assertNotNull(ex);
        }
        
        assertNotNull(game.getCommunityCards());
        assertNotNull(game.getChanceCards());
        //assertEquals(cards.length, 4);
    }
    
    @Test
    public void testAmountColoredFields() {
    
        game.startGame(2);

        assertNotNull(game.getAllOwnableFields());
        assertNotNull(game.getAllStreetFields());

    } 
}
