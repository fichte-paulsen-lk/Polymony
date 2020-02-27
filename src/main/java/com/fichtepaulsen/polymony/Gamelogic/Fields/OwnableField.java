package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public abstract class OwnableField extends Field{
  public int price;
  private Player owner;
  public String name;
  private int houseAmount;
  private boolean isMortgage;
  private int mortgageAmount;

    public boolean getIsMortgage() {
        return isMortgage;
    }

    public void setIsMortgage(boolean isMortgage) {
        this.isMortgage = isMortgage;
    }
    
    public void addMortgage(){
        owner.setBalance(owner.getBalance()+mortgageAmount);
        isMortgage = true;
    }
    
    public void freeMortgage(){
        if(isMortgage==true){
            owner.setBalance(owner.getBalance()-(mortgageAmount+mortgageAmount*1/10));
            isMortgage=false;
        }
    }
    
    public int getMortgageAmount() {
        return mortgageAmount;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHouseAmount() {
        return houseAmount;
    }

    public void setHouseAmount(int houseAmount) {
        this.houseAmount = houseAmount;
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
