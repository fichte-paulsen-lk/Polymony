package polymony.Gamelogic.Fields;

import polymony.Gamelogic.Player.Player;

public class TaxField extends Field {
  int tax;
   
  public TaxField(int taxset, int indexset, String nameset) { //Konstruktor
      tax = taxset;
      index = indexset;
      name = nameset;
  }
  public void action (Player activePlayer){
      activePlayer.setBalance(activePlayer.getBalance()-tax);
  }
}
