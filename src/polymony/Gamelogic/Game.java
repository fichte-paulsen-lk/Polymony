package polymony.Gamelogic;

import polymony.Gamelogic.Player.HumanPlayer;
import polymony.Gamelogic.Player.Player;

public class Game implements GameInterface{
    Player[] players;
    /*
    Field[] fields;
    Dice[] dices;
    */
    int activePlayer;
    public Game(int playerCount,int fieldCount,int diceCount){
        // erstelle Spieler Array mit angegebener Spieleranzahl
        this.players = new Player[playerCount];
        //Fülle den Spieler Array mit Spielern
        for(int i=0;i<playerCount;i++){
            this.players[i] = new HumanPlayer();
        }
        activePlayer = 0;
        
        /*
        // erstelle Felder Array mit angegebener Felderanzahl
        this.fields = new Field[fieldCount];
        //Fülle den Felder Array mit Felder
        for (int i=0;i<fieldCount;i++){
            this.fields[i] = new Field();
        }
        
        // erstelle Felder Array mit angegebener Felderanzahl
        this.dices = new Dice[diceCount];
        //Fülle den Felder Array mit Felder
        for (int i=0;i<diceCount;i++){
            this.dices[i] = new Dice();
        }
        */
        
    }
    
    //Testmethode, um Spiellogik zu testen, ohne Verbindung zur Grafik
    public static void main(String[] args) {
        //rufe Konstruktor auf mit 3 Spielern und 40 Feldern
        Game g1 = new Game(3,40,2);
    }
    
    /*
    public int[] rollDices(){
        for (Dice d : dices){
            d.rollDice();
        }
    }
    */
}