package polymony.Gamelogic.Fields;

import polymony.Gamelogic.Player.Player;

public class TaxField extends SpecialField {
  int tax;
  int index;
  String name;
   
  public TaxField(int tax,String name,int index) { //Konstruktor
      this.tax = tax;
      this.index = index;
      this.name = name;
  }
  @Override
  public void action (Player activePlayer){
      activePlayer.setBalance(activePlayer.getBalance()-tax);
  }
}
