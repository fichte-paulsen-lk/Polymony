package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public abstract class OwnableField extends Field{
  int price;
  private Player owner;
  String name;
  private int houseamount;
  private boolean ishypothek;
  private int hypothekamount;

    public boolean isIshypothek() {
        return ishypothek;
    }

    public void setIshypothek(boolean ishypothek) {
        this.ishypothek = ishypothek;
    }
    
    public void gethypothek(){
        owner.setBalance(owner.getBalance()+hypothekamount);
        ishypothek = true;
    }
    
    public void freehypothek(){
        owner.setBalance(owner.getBalance()-(hypothek+hapothek*1/10));
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHouseamount() {
        return houseamount;
    }

    public void setHouseamount(int houseamount) {
        this.houseamount = houseamount;
    }
  
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
   
}
