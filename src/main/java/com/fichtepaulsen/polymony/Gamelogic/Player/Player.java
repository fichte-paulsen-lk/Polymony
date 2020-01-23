package com.fichtepaulsen.polymony.Gamelogic.Player;

public abstract class Player {
    public Player(){
        
    }
    private int position;
    private int utilitiesOwned;
    private int balance;
    private int trainstaitionOwned;
    private boolean incarcerated;

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
}
