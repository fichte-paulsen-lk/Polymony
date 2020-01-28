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
    public Game(int playerCount,int fieldCount,int diceCount,int cardCount){
       
        
        cards = new Card[cardCount];
        
        
      try {
            fields = readFieldsJson(fieldCount);
            cards = shuffle(readCardsJson(cardCount));
        } catch (Exception e) {
            e.printStackTrace();
        }
      
      
      
      
        // erstelle Spieler Array mit angegebener Spieleranzahl
        this.players = new Player[playerCount];
        //Fülle den Spieler Array mit Spielern
        for(int i=0;i<playerCount;i++){
            this.players[i] = new HumanPlayer();
        }
        activePlayerIndex = 0;
        
        //teste Karte:
        cards[0].action(players[0]);
        
        //erstelle Felder Array mit angegebener Felderanzahl
        this.fields = new Field[fieldCount];
        //Fülle den Felder Array mit Felder
        for (int i=0;i<fieldCount;i++){
            this.fields[i] = null;
        }
        
        fields[0] = new StartField();
        fields[1] = new StreetField("Straße",1, Color.MEDIUMBLUE);
        fields[2] = null;
        
        
        // erstelle Felder Array mit angegebener Felderanzahl
        this.dices = new Dice[diceCount];
        //Fülle den Felder Array mit Felder
        for (int i=0;i<diceCount;i++){
            this.dices[i] = new NormalDice();
        }
        rollDices();
        
        
    }
    
    //Testmethode, um Spiellogik zu testen, ohne Verbindung zur Grafik
    public static void main(String[] args) {
        //rufe Konstruktor auf mit 3 Spielern und 40 Feldern
        Game g1 = new Game(3,40,2,3);
    }
    
    
    public int[] rollDices(){
        //Nehme alle Würfel, hole ihre Werte und gebe sie in einem Array zurück
        int [] results = new int[dices.length];
        for (int i = 0;i<dices.length;i++){
            results[i] = dices[i].roll();
        }
        //System.out.println("n0 = "+results[0]+ " n1 = "+results[1]);
        
        //Guck ob es ein Pasch ist
        
        boolean doublets =  isDoublets(results);

        
        //Gesamtzahl der Würfel holen
        int gesamtZahl = 0;
        for (int value : results){
            gesamtZahl+=value;
        }
        System.out.println("GesamtZahl = "+gesamtZahl);
       
        //TODO: Spiellogik ausführen
        Player activePlayer = players[activePlayerIndex];
        System.out.println(activePlayer.getPosition());
        int newPos = (activePlayer.getPosition()  + gesamtZahl) % fields.length;
        activePlayer.setPosition(newPos);

        System.out.println(activePlayer.getPosition());


        return results;
    }
    
    public static boolean isDoublets(int[] array)
    {
        for(int i = 1; i < array.length; i++)
        {
            if((array[0] != array[i])) return false;
        }
        return true;
    }

    Field[] readFieldsJson(int length) throws IOException, JSONException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {

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
        for (int i = 0;i<jsonArray.length();i++){

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