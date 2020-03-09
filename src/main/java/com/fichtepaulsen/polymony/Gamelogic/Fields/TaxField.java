package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Game;
import com.fichtepaulsen.polymony.Gamelogic.Player.*;

public class TaxField extends SpecialField {
  private int tax;
  private int index;
   
  public TaxField(int tax,String name,int index) { //Konstruktor
      this.tax = tax;
      this.index = index;
      this.name = name;
  }
  
    @Override
    public void action(Game game) {
        game.getActivePlayer().setBalance(game.getActivePlayer().getBalance()-tax);
    }
}
