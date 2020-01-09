/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymony;

/**
 *
 * @author root
 */
public class Game implements GameInterface{
    Player[] players;
    int currentPlayer;
    public Game(int playerCount){
        // erstelle Spieler Array mit angegebener Spieleranzahl
        this.players = new Player[playerCount];
        //Fülle den Array mit Spielern
        for(int i=0;i<playerCount;i++){
            this.players[i] = new HumanPlayer();
        }
        currentPlayer = 0;
    }
    
    //Testmethode, um Spiellogik zu testen, ohne Verbindung zur Grafik
    public static void main(String[] args) {
        Game g1 = new Game(3);
    }
}