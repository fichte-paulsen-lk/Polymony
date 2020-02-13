package com.fichtepaulsen.polymony.Gamelogic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fichtepaulsen.polymony.Gamelogic.Fields.Field;
import com.fichtepaulsen.polymony.Gamelogic.Dice.Dice;
import com.fichtepaulsen.polymony.Gamelogic.Dice.NormalDice;
import com.fichtepaulsen.polymony.Gamelogic.Fields.ActionField;
import com.fichtepaulsen.polymony.Gamelogic.Fields.Prison;
import com.fichtepaulsen.polymony.Gamelogic.Fields.StartField;
import com.fichtepaulsen.polymony.Gamelogic.Fields.StreetField;
import com.fichtepaulsen.polymony.Gamelogic.Fields.TaxField;
import com.fichtepaulsen.polymony.Gamelogic.Fields.TrafficField;
import com.fichtepaulsen.polymony.Gamelogic.Player.HumanPlayer;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import javafx.scene.paint.Color;

public class Game implements GameInterface{
    Player[] players;
    Field[] fields;
    Dice[] dices;
    
    int activePlayerIndex;
    public Game(){

    }   
    //test method to test gamelogic without graphics
    public static void main(String[] args) {
        //constructor with 3 players and 40 fields
        //Game g1 = new Game(3,40,2);

    }
    
    /*
    requires: integer number of players. 
    does: initializes players,fields and dice to start the game.
    */
    public void startGame(int playerCount){
        // create 40 fields in a fieldArray.    
        try {
            fields = readJson(40);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create playerArray with given playerCount.
        this.players = new Player[playerCount];
        // fill playerArray with human players.
        for(int i = 0; i < playerCount; i++){
            this.players[i] = new HumanPlayer(0, 1500, i);
        }
        activePlayerIndex = 0;
        
        //erstelle Felder Array mit angegebener Felderanzahl
        //this.fields = new Field[40];
        //Fülle den Felder Array mit Felder
        //for (int i = 0;i < fields.length; i++){
            //this.fields[i] = null;
        //}
        
        //fields[0] = new StartField();
        //fields[1] = new StreetField("Straße",1, Color.MEDIUMBLUE);
        //fields[2] = null;
        
        // create diceArray with 2 dices.
        this.dices = new Dice[2];
        //fills array with 2 normal dices.
        for (int i = 0; i < dices.length; i++){
            this.dices[i] = new NormalDice();
        }
    }
    
    
    public int[] rollDices(){
        //Returns an array of roll results
        int [] results = new int[dices.length];
        for (int i = 0;i<dices.length;i++){
            results[i] = dices[i].roll();
        }
        //System.out.println("n0 = "+results[0]+ " n1 = "+results[1]);

        
        //Calculates the sum of roll results
        int gesamtZahl = 0;
        for (int value : results){
            gesamtZahl+=value;
        }
        //System.out.println("GesamtZahl = "+gesamtZahl);
       
        //TODO: Spiellogik ausführen
        Player activePlayer = players[activePlayerIndex];
        boolean doublets =  isDoublets(results);                                //Tests for doublets
        int newPos = (activePlayer.getPosition()  + gesamtZahl) % 40;           //Calculates next position after rolling the dices
        //Case where the player is in prison:
            if(activePlayer.getIsInPrison()==true){   
                if (doublets == false){                           
                    if(activePlayer.getPrisonAttemptCounter()==3){              //When the player doesn't roll doublets for 3 rounds 
                        activePlayer.setOutOfPrison();                      //he comes out of prison and moves
                        activePlayer.setPosition(newPos);
                        activePlayer.setPrisonAttemptCounter(0);
                    }
                    else{
                        activePlayer.incrementPrisonAttemptCounter();
                    }
                    activePlayerIndex=(activePlayerIndex+1)%players.length;
                }
                else{                                                           //If the player rolls doublets during one of his 3 attempts
                    activePlayer.setOutOfPrison();                          //he comes out of prison and moves by the amount he rolled  
                    activePlayer.setPosition(newPos);                           //(no additional move after these doublets)
                }    
            }
            //Normal case:
            else{                                                     
                activePlayer.setPosition(newPos);                                   
                if (doublets == false){                                         //Normal roll(player moves, activePlayerIndex increments,            
                    activePlayerIndex=(activePlayerIndex+1)%players.length;     //doubletsCounter resets)
                    activePlayer.setDoubletsCounter(0);
                }
                else{                                                           //Doublet roll(doubletCounter increments, activePlayerIndex stays untouched)
                    activePlayer.incrementDoubletsCounter();
                    if (activePlayer.getDoubletsCounter()==3){                  //When doubletCounter reaches 3, the player will be automatically moved to 
                        activePlayer.setInPrison();                       //the prison field and activePlayerIndex increments
                        activePlayer.setDoubletsCounter(0);
                        activePlayer.setPrisonAttemptCounter(0);
                        activePlayerIndex=(activePlayerIndex+1)%players.length;
                    }
                }
            }
        
        return results;
    }
    
    public static boolean isDoublets(int[] array){
        for(int i = 1; i < array.length; i++){
            if((array[0] != array[i])) 
            return false;
        }
        return true;
    }

    public Field[] readJson(int length) throws IOException, JSONException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //Array der später zurückgegeben wird-
        Field[] temp = new Field[length];

        //Öffne die fields.json Datei und schreibegetResourceAsStream den Inhalt in jsonString
        InputStream in = this.getClass().getResourceAsStream("fields.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String jsonString = "";

        String line = null;
        while ( (line = reader.readLine()) != null) {
            jsonString +=line;
        }

        //lade den String als JSONObject
        JSONObject obj = new JSONObject(jsonString);

        //öffne den fields array aus dem JSONObject
        JSONArray jsonArray = obj.getJSONArray("fields");

        //iteriere durch alle Einträge
        for (int i = 0; i < jsonArray.length(); i++){

            //lade das JSONObject am Index i
            JSONObject field = jsonArray.getJSONObject(i);
            //Hole den Typen bzw. den Klassenbezeichner des Feldes
            String fieldClassName = (String) field.get("type");

            //Je nachdem welche Klasse es ist wird der Konstruktor mit den jeweils gewünschten Werten aufgerufen und das Objekt in temp an Stelle des Indizes i geschrieben
            switch (fieldClassName){
                case "StartField":
                    temp[i] = new StartField();
                    break;
                case "StreetField":
                    temp[i] = new StreetField((String)field.get("name"),(int)field.get("price"), null);
                    break;
                case "ActionField":
                    temp[i] = new ActionField();
                    break;
                case "TaxField":
                    temp[i] = new TaxField((int)field.get("tax"),(String) field.get("name"),i);
                    break;
                case "TrafficField":
                    temp[i] = new TrafficField();
                    break;
                case "Prison":
                    temp[i] = new Prison();
                    break;
            }

        }



        return temp;
    }
    
    @Override
    /*
    requires:
    returns: player object from the active player.
             to get the player index: getIndex().
             to get the player position: getPosition().
    */
    public Player getCurrentPlayer() {
        return players[activePlayerIndex];
    }

    @Override
    /*
    requires: index from a player 
    returns:  player object from players at the given index
    */ 
    public Player getNthPlayer(int index) {
        return players[index];
    }

    @Override
    /*
    requires: 
    returns: player array with all players
    */
    public Player[] getAllPlayers() {
       return players; 
    }

    @Override
    /*
    requires: 
    returns: dice array with all dices
    */
    public Dice[] getAllDice() {
        return dices;
    }

    @Override
    /*
    requires: 
    returns: field array with all fields
    */
    public Field[] getAllFields() {
        return fields;
    }

    @Override
    /*
    requires: index from a field 
    returns:  field object from fields at the given index
    */ 
    public Field getNthField(int index) {
        return fields[index];
    }

}