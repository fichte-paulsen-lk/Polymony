package com.fichtepaulsen.polymony.Cards;
import com.fichtepaulsen.polymony.Gamelogic.Cards.Card;
import com.fichtepaulsen.polymony.Gamelogic.Cards.MoneyCard;
import org.junit.Test;
import static org.junit.Assert.*;

public class CardsTest {
    @Test
    public void test(){
        Card card = new MoneyCard("test",100,true);
        assertEquals(card.cardType(), "Community Chest");
    }
    
}
