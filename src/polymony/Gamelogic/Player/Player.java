package polymony.Gamelogic.Player;

public abstract class Player {
    public Player(){
        
    }
    private int position;
    
    private int balance;

    /**
     * Get the value of balance
     *
     * @return the value of balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Set the value of balance
     *
     * @param balance new value of balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }


    /**
     * Get the value of position
     *
     * @return the value of position
     */
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    
    
}
