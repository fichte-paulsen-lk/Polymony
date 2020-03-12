package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.Player.*;

public class TaxField extends SpecialField {
  private final int tax;
  private final int index;
   
  public TaxField(int tax,String name,int index) { //Konstruktor
      this.tax = tax;
      this.index = index;
      this.name = name;
  }
  
    @Override
    public void action(Game game) {
        Player activePlayer = game.getCurrentPlayer();
        activePlayer.substractBalance(tax);
    }
}
