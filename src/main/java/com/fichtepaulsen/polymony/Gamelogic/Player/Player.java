package com.fichtepaulsen.polymony.Gamelogic.Player;

public abstract class Player {
    
    private int position;
    private int utilitiesOwned;
    private int balance;
    private int trainstaitionOwned;
    private boolean incarcerated;
    private int amountPrisonFreeCard; 
    private int playerIndex;
    private boolean isInPrison;
    private int prisonAttemptCounter;                                           //Counts doublet attempts in prison
    private int doubletsCounter;                                                

    public Player(int position, int balance, int index){
        this.position = position;
        this.balance = balance;
        this.playerIndex = index;
        this.isInPrison = false;
    }
    
    public int getAmountPrisonFreeCard() {
        return amountPrisonFreeCard;
    }

    public void setAmountPrisonFreeCard(int amountPrisonFreeCard) {
        this.amountPrisonFreeCard = amountPrisonFreeCard;
    }

    public boolean isIncarcerated() {
        return incarcerated;
    }

    public void setIncarcerated(boolean incarcerated) {
        this.incarcerated = incarcerated;
    }

    public int getUtilitiesOwned() {
        return utilitiesOwned;
    }

    public void setUtilitiesOwned(int utilitiesOwned) {
        this.utilitiesOwned = utilitiesOwned;
    }

    public int getTrainstaitionOwned() {
        return trainstaitionOwned;
    }

    public void setTrainstaitionOwned(int trainstaitionOwned) {
        this.trainstaitionOwned = trainstaitionOwned;
    }

    public int getBalance() {
        return balance;
    }
    
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    public int getIndex(){
        return playerIndex;
    }
    
    public void setIsInPrison(boolean p){
       this.isInPrison=p; 
    } 
    
    public boolean getIsInPrison(){
        return this.isInPrison;
    }
    
    public void incrementPrisonAttemptCounter(){
        prisonAttemptCounter++;
    }
    
    public int getPrisonAttemptCounter(){
        return prisonAttemptCounter;
    }
    
    public void setPrisonAttemptCounter(int ac){
        this.prisonAttemptCounter=ac; 
    }
    
    public void incrementDoubletsCounter(){
        doubletsCounter++;
    }
    
    public int getDoubletsCounter(){
        return doubletsCounter;
    }
    
    public void setDoubletsCounter(int dc){
        this.doubletsCounter=dc; 
    }
    
}
