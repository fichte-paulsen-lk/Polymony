package polymony.Gamelogic.Fields;

import polymony.Gamelogic.Player.Player;

public class TaxField extends SpecialField {
  int tax;
   
  public TaxField(int taxset, int indexset, String nameset) { //Konstruktor
      tax = taxset;
      index = indexset;
      name = nameset;
  }
  @Override
  public void action (Player activePlayer){
      activePlayer.setBalance(activePlayer.getBalance()-tax);
  }
}
