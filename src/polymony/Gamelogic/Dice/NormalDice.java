package polymony.Gamelogic.Dice;

public class NormalDice extends Dice{
    @Override
    public int roll() {
        int random=(int) (Math.random() *5)+1;
        return random;
    }
}
