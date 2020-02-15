package com.fichtepaulsen.polymony.Gamelogic;

import com.fichtepaulsen.polymony.Gamelogic.Cards.Card;
import com.fichtepaulsen.polymony.Gamelogic.Cards.MoneyCard;
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
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Color;

public class Game implements GameInterface{
    Player[] players;
    Field[] fields;
    Dice[] dices;
    Card[] cards;
    
    int activePlayerIndex;
    
    public Game(){
       
        /*
        cards = new Card[cardCount];
        
        
      try {
            cards = shuffle(readCardsJson(cardCount));
        } catch (Exception e) {
            e.printStackTrace();
        }
      */
      
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


    /* requires: -
    returns: results of dices being rolled
    */
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
                  activePlayer.incrementPrisonAttemptCounter();
                if(activePlayer.getPrisonAttemptCounter()==3){                  //When the player doesn't roll doublets for 3 rounds 
                  activePlayer.setIsInPrison(false);                            //he comes out of prison and moves
                  activePlayer.setPosition(newPos);
                  activePlayer.setPrisonAttemptCounter(0);
                }  
                activePlayerIndex=(activePlayerIndex+1)%players.length;
              }
              else{                                                             //If the player rolls doublets during one of his 3 attempts
                activePlayer.setIsInPrison(false);                              //he comes out of prison and moves by the amount he rolled  
                activePlayer.setPosition(newPos);                               //(no additional move after these doublets)
                activePlayerIndex=(activePlayerIndex+1)%players.length;
              }    
          }
          //Normal case:
          else{                                                     
            activePlayer.setPosition(newPos);                                   
            if (doublets == false){                                             //Normal roll(player moves, activePlayerIndex increments,            
              activePlayerIndex=(activePlayerIndex+1)%players.length;           //doubletsCounter resets)
              activePlayer.setDoubletsCounter(0);
            }
            else{                                                               //Doublet roll(doubletCounter increments, activePlayerIndex stays untouched)
              activePlayer.incrementDoubletsCounter();
                if (activePlayer.getDoubletsCounter()==3){                      //When doubletCounter reaches 3, the player will be automatically moved to 
                  activePlayer.setIsInPrison(true);                             //the prison field and activePlayerIndex increments
                  activePlayer.setDoubletsCounter(0);
                  activePlayer.setPrisonAttemptCounter(0);
                  activePlayer.setPosition(10);
                  activePlayerIndex=(activePlayerIndex+1)%players.length;
                }
            }
        }
        
        return results;
    }

    public static boolean isDoublets(int[] array){
        for(int i = 1; i < array.length; i++){
            if((array[0] != array[i])) 
            {
                return false;
            }
        }
        return true;
    }

    public Field[] readJson(int length) throws IOException, JSONException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //Array der später zurückgegeben wird-
        Field[] temp = new Field[length];

        //Öffne die fields.json Datei und schreibe den Inhalt in jsonString
        InputStream in = this.getClass().getResourceAsStream("/setup.json");
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
                    temp[i] = new StreetField((String)field.get("name"),(int)field.get("price"), getColor((int)field.get("color")));
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
    public Field getNthField(int n) {
        return fields[n];
    }
    /*
    requires: Color index from json file
    returns:  Color object for the corresponding index
    */
    public static Color getColor(int n){
        // return a
        switch (n){
            case 1:
                return Color.BROWN;
            case 2:
                return Color.LIGHTBLUE;
            case 3:
                return Color.PINK;
            case 4:
                return Color.ORANGE;
            case 5:
                return Color.RED;
            case 6:
                return Color.YELLOW;
            case 7:
                return Color.GREEN;
            case 8:
                return Color.BLUE;
            default:
                //should never happen, maybe throw an exception
                return Color.BLACK;

        }
    }

    Card[] readCardsJson(int length) throws IOException{
                //Array der später zurückgegeben wird-
        Card[] temp = new Card[length];

        //Öffne die fields.json Datei und schreibe den Inhalt in jsonString
        InputStream in = this.getClass().getResourceAsStream("/setup.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String jsonString = "";

        String line = null;
        while ( (line = reader.readLine()) != null) {
            jsonString +=line;
        }

        //lade den String als JSONObject
        JSONObject obj = new JSONObject(jsonString);

        //öffne den fields array aus dem JSONObject
        JSONArray jsonArray = obj.getJSONArray("cards");

        //iteriere durch alle Einträge
        for (int i = 0;i<jsonArray.length();i++){

            //lade das JSONObject am Index i
            JSONObject card = jsonArray.getJSONObject(i);
            //Hole den Typen bzw. den Klassenbezeichner des Feldes
            String cardClassName = (String) card.get("type");
            
            switch (cardClassName){
                case "MoneyCard": 
                      System.out.println("money card");
                      temp[i] = new MoneyCard((String) card.getString("text"),(int) card.get("value"));
                      break;
                default: temp[i] = null;
            }
        }
        return temp;
    }
    
    private static Card[] shuffle(Card[] array){
    
    Random rnd = ThreadLocalRandom.current();
    for (int i = array.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      Card a = array[index];
      array[index] = array[i];
      array[i] = a;
    }
    return array;
  }
    
    public Player[] getPlayers() {
        return players;
    }

    public int getActivePlayerIndex() {
        return activePlayerIndex;
    }
    
}
