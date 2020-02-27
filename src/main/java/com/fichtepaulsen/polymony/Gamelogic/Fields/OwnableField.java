package com.fichtepaulsen.polymony.Gamelogic.Fields;

import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

public abstract class OwnableField extends Field{
  public int price;
  private Player owner;
  public String name;
  private int houseAmount;
  private boolean isHypothek;
  private int hypothekAmount;

    public boolean getIsHypothek() {
        return isHypothek;
    }

    public void setIsHypothek(boolean isHypothek) {
        this.isHypothek = isHypothek;
    }
    
    public void addHypothek(){
        owner.setBalance(owner.getBalance()+hypothekAmount);
        isHypothek = true;
    }
    
    public void freeHypothek(){
        if(isHypothek==true){
            owner.setBalance(owner.getBalance()-(hypothekAmount+hypothekAmount*1/10));
            isHypothek=false;
        }
    }
    
    public int getHypothekAmount() {
        return hypothekAmount;
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
