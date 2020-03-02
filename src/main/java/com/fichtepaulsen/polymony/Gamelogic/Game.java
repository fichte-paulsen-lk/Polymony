package com.fichtepaulsen.polymony.Gamelogic;

import com.fichtepaulsen.polymony.Gamelogic.Cards.Card;
import com.fichtepaulsen.polymony.Gamelogic.Cards.JumpCard;
import com.fichtepaulsen.polymony.Gamelogic.Cards.JumpToCard;
import com.fichtepaulsen.polymony.Gamelogic.Cards.MoneyCard;
import com.fichtepaulsen.polymony.Gamelogic.Cards.MoneyCardOtherPlayers;
import com.fichtepaulsen.polymony.Gamelogic.Cards.PrisonFreeCard;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fichtepaulsen.polymony.Gamelogic.Fields.Field;
import com.fichtepaulsen.polymony.Gamelogic.Dice.Dice;
import com.fichtepaulsen.polymony.Gamelogic.Dice.NormalDice;
import com.fichtepaulsen.polymony.Gamelogic.Fields.ActionField;
import com.fichtepaulsen.polymony.Gamelogic.Fields.GoToPrisonField;
import com.fichtepaulsen.polymony.Gamelogic.Fields.OwnableField;
import com.fichtepaulsen.polymony.Gamelogic.Fields.PrisonField;
import com.fichtepaulsen.polymony.Gamelogic.Fields.StartField;
import com.fichtepaulsen.polymony.Gamelogic.Fields.StreetField;
import com.fichtepaulsen.polymony.Gamelogic.Fields.TaxField;
import com.fichtepaulsen.polymony.Gamelogic.Fields.TrafficField;
import com.fichtepaulsen.polymony.Gamelogic.Fields.UtilityField;
import com.fichtepaulsen.polymony.Gamelogic.Player.HumanPlayer;
import com.fichtepaulsen.polymony.Gamelogic.Player.Player;
import com.fichtepaulsen.polymony.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;

public class Game implements GameInterface {

    private Player[] players;
    private Field[] fields;
    private Dice[] dices;
    private Card[] chanceCards;

    public Card[] getChanceCards() {
        return chanceCards;
    }

    public Card[] getCommunityCards() {
        return communityCards;
    }

    private Card[] communityCards;
    private boolean keepActivePlayer;

    private int activePlayerIndex;

    public Game() {

    }

    /*
    requires: integer number of players. 
    does: initializes players,fields and dice to start the game.
     */
    public void startGame(int playerCount) {
        // create 40 fields in a fieldArray.    
        try {
            fields = readJson();
        } catch (Exception e) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, e.getMessage());
        }

        // create playerArray with given playerCount.
        this.players = new Player[playerCount];
        // fill playerArray with human players.
        for (int i = 0; i < playerCount; i++) {
            this.players[i] = new HumanPlayer(0, 1500, i);
        }
        activePlayerIndex = 0;

        // create diceArray with 2 dices.
        this.dices = new Dice[2];
        //fills array with 2 normal dices.
        for (int i = 0; i < dices.length; i++) {
            this.dices[i] = new NormalDice();
        }

        //create community- and chanceCard arrays from JSON file
        try {
            this.communityCards = shuffle(readCommunityCardsJson());
            this.chanceCards = shuffle(readChanceCardsJson());

        } catch (IOException e) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, e.getMessage());
        }

    }

    /* requires: -
    returns: - (makes the next player active)
     */
    public void nextTurn() {
        if (!keepActivePlayer) {
            activePlayerIndex = (activePlayerIndex + 1) % players.length;
        }

    }

    /* requires: -
    returns: results of dices being rolled
     */
    @Override
    public int[] rollDices() {
        //Returns an array of roll results
        int[] results = new int[dices.length];
        for (int i = 0; i < dices.length; i++) {
            results[i] = dices[i].roll();
        }

        //Calculates the sum of roll results
        int gesamtZahl = 0;
        for (int value : results) {
            gesamtZahl += value;
        }

        Player activePlayer = players[activePlayerIndex];

        boolean doublets = isDoublets(results);                                //Tests for doublets
        int newPos = (activePlayer.getPosition() + gesamtZahl) % 40;           //Calculates next position after rolling the dices
        //Case where the player is in prison:
        if (activePlayer.getIsInPrison() == true) {
            if (doublets == false) {

                activePlayer.incrementPrisonAttemptCounter();
                if (activePlayer.getPrisonAttemptCounter() == 3) {                  //When the player doesn't roll doublets for 3 rounds 
                    activePlayer.setOutOfPrison();                            //he comes out of prison and moves
                    activePlayer.setPosition(newPos);
                    activePlayer.setPrisonAttemptCounter(0);
                }

            } else {                                                             //If the player rolls doublets during one of his 3 attempts
                activePlayer.setOutOfPrison();                              //he comes out of prison and moves by the amount he rolled  
                activePlayer.setPosition(newPos);                               //(no additional move after these doublets)
            }
        } //Normal case:
        else {
            activePlayer.setPosition(newPos);
            if (doublets == false) {                                             //Normal roll(player moves, activePlayerIndex increments,
                keepActivePlayer = false;
                //doubletsCounter resets)
                activePlayer.setDoubletsCounter(0);
            } else {                                                               //Doublet roll(doubletCounter increments, activePlayerIndex stays untouched)
                activePlayer.incrementDoubletsCounter();
                keepActivePlayer = true;
                if (activePlayer.getDoubletsCounter() == 3) {                      //When doubletCounter reaches 3, the player will be automatically moved to 
                    activePlayer.setInPrison();                                   //the prison field and activePlayerIndex increments
                    activePlayer.setDoubletsCounter(0);
                    activePlayer.setPrisonAttemptCounter(0);
                }
            }
        }

        return results;
    }

    public static boolean isDoublets(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if ((array[0] != array[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean isAbleToBuyOutOfPrison() {                                    //checks if the player is able to pay the bail
        Player activePlayer = players[activePlayerIndex];
        if (activePlayer.getIsInPrison() == true && activePlayer.getBalance() >= 50) {
            return true;
        } else {
            return false;
        }
    }

    public void prisonPayment() {                                                //pays the bail and frees the player
        Player activePlayer = players[activePlayerIndex];
        activePlayer.setOutOfPrison();
        activePlayer.setBalance(activePlayer.getBalance() - 50);
    }

    public Field[] readJson() throws IOException, JSONException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //Array der später zurückgegeben wird-
        //Öffne die fields.json Datei und schreibe den Inhalt in jsonString
        InputStream in = this.getClass().getResourceAsStream("/setup.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String jsonString = "";

        String line = null;
        while ((line = reader.readLine()) != null) {
            jsonString += line;
        }

        //lade den String als JSONObject
        JSONObject obj = new JSONObject(jsonString);

        //öffne den fields array aus dem JSONObject
        JSONArray jsonArray = obj.getJSONArray("fields");
        Field[] temp = new Field[jsonArray.length()];
        //iteriere durch alle Einträge
        for (int i = 0; i < jsonArray.length(); i++) {

            //lade das JSONObject am Index i
            JSONObject field = jsonArray.getJSONObject(i);

            //Hole den Typen bzw. den Klassenbezeichner des Feldes
            String fieldClassName = field.getString("type");

            //Je nachdem welche Klasse es ist wird der Konstruktor mit den jeweils gewünschten Werten aufgerufen und das Objekt in temp an Stelle des Indizes i geschrieben
            switch (fieldClassName) {
                case "StartField":
                    temp[i] = new StartField();
                    break;
                case "StreetField":
                    temp[i] = new StreetField((String) field.get("name"), (int) field.get("price"), getColor((int) field.get("color")));
                    break;
                case "ActionField":
                    try {
                        field.getBoolean("freeParking");
                        temp[i] = new ActionField(false, field.getBoolean("freeParking"));
                    } catch (JSONException e) {
                        temp[i] = new ActionField(field.getBoolean("community"), false);
                    }

                    break;
                case "TaxField":
                    temp[i] = new TaxField((int) field.get("tax"), (String) field.get("name"), i);
                    break;
                case "TrafficField":
                    temp[i] = new TrafficField();
                    break;
                case "Prison":
                    temp[i] = new PrisonField();
                    break;
                case "GoToPrisonField":
                    temp[i] = new GoToPrisonField();
                    break;
                case "Utility":
                    temp[i] = new UtilityField((String) field.get("name"), (int) field.get("price"));
                    break;
                default:
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "JSON Import does not work!");
                    break;
            }
        }

        return temp;
    }

    /*
        requires:
        returns: player object from the active player.
                 to get the player index: getIndex().
                 to get the player position: getPosition().
     */
    @Override
    public Player getCurrentPlayer() {
        return players[activePlayerIndex];
    }

    /*
        requires: index from a player 
        returns:  player object from players at the given index
     */
    @Override
    public Player getNthPlayer(int index) {
        return players[index];
    }

    /*
        requires: 
        returns: player array with all players
     */
    @Override
    public Player[] getAllPlayers() {
        return players;
    }

    /*
        requires: 
        returns: dice array with all dices
     */
    @Override
    public Dice[] getAllDice() {
        return dices;
    }

    /*
        requires: 
        returns: field array with all fields
     */
    @Override
    public Field[] getAllFields() {
        return fields;
    }

    /*
    requires: index from a field 
    returns:  field object from fields at the given index
     */
    @Override
    public Field getNthField(int n) {
        return fields[n];
    }

    /*
    requires: Color index from json file
    returns:  Color object for the corresponding index
     */
    public static Color getColor(int n) {
        // return a
        switch (n) {
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

    //reads the ChacneCard part from the JSON file
    public Card[] readChanceCardsJson() throws IOException {

        //Öffne die fields.json Datei und schreibe den Inhalt in jsonString
        InputStream in = this.getClass().getResourceAsStream("/setup.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String jsonString = "";

        String line = null;
        while ((line = reader.readLine()) != null) {
            jsonString += line;
        }

        //lade den String als JSONObject
        JSONObject obj = new JSONObject(jsonString);

        //öffne den fields array aus dem JSONObject
        JSONObject cardsObj = obj.getJSONObject("cards");

        JSONArray jsonArray = cardsObj.getJSONArray("chance");

        Card[] temp = new Card[jsonArray.length()];

        //iteriere durch alle Einträge
        for (int i = 0; i < jsonArray.length(); i++) {

            //lade das JSONObject am Index i
            JSONObject card = jsonArray.getJSONObject(i);
            //Hole den Typen bzw. den Klassenbezeichner des Feldes
            String cardClassName = (String) card.get("type");

            switch (cardClassName) {
                case "MoneyCard":
                    temp[i] = new MoneyCard((String) card.getString("text"), (int) card.get("value"), (boolean) card.get("community"));
                    break;
                case "JumpCard":
                    temp[i] = new JumpCard(card.getInt("value"), card.getString("text"), card.getBoolean("community"));
                    break;
                case "JumpToCard":
                    temp[i] = new JumpToCard(card.getInt("position"), card.getString("text"), card.getBoolean("community"));
                    break;
                case "MoneyCardOtherPlayers":
                    temp[i] = new MoneyCardOtherPlayers((String) card.getString("text"), (int) card.get("value"), (boolean) card.get("community"));
                    break;
                case "PrisonFreeCard":
                    temp[i] = new PrisonFreeCard((String) card.getString("text"), (boolean) card.get("community"));
                    break;
                default:
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Card JSON import not working!");
            }
        }
        return temp;
    }

    //reads the CommunityCard part from the JSON file
    public Card[] readCommunityCardsJson() throws IOException {

        //Öffne die fields.json Datei und schreibe den Inhalt in jsonString
        InputStream in = this.getClass().getResourceAsStream("/setup.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String jsonString = "";

        String line = null;
        while ((line = reader.readLine()) != null) {
            jsonString += line;
        }

        //lade den String als JSONObject
        JSONObject obj = new JSONObject(jsonString);

        //öffne den fields array aus dem JSONObject
        JSONObject cardsObj = obj.getJSONObject("cards");

        JSONArray jsonArray = cardsObj.getJSONArray("chance");

        Card[] temp = new Card[jsonArray.length()];

        //iteriere durch alle Einträge
        for (int i = 0; i < jsonArray.length(); i++) {

            //lade das JSONObject am Index i
            JSONObject card = jsonArray.getJSONObject(i);
            //Hole den Typen bzw. den Klassenbezeichner des Feldes
            String cardClassName = (String) card.get("type");

            switch (cardClassName) {
                case "MoneyCard":
                    temp[i] = new MoneyCard((String) card.getString("text"), (int) card.get("value"), (boolean) card.get("community"));
                    break;
                case "JumpCard":
                    temp[i] = new JumpCard(card.getInt("value"), card.getString("text"), card.getBoolean("community"));
                    break;
                default:
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Card JSON import not working!");
            }
        }
        return temp;
    }

    //shuffels the array with the cards
    //used for Community- and ChanceCards
    private Card[] shuffle(Card[] array) {
        Random rnd = ThreadLocalRandom.current();

        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Card a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
        return array;
    }

    @Override
    /*
    requires: 
    does: switches the activePlayerIndex to the next player
     */
    public void endTurn() {
        activePlayerIndex = (activePlayerIndex++) % players.length;
    }

    @Override
    /*
    requires: 
    does:  current player buys the ownableField he stands on
     */
    public void buyField() {
        Player activePlayer = getCurrentPlayer();
        OwnableField currentField = (OwnableField) fields[activePlayer.getPosition()];
        //if the player has enough money to buy the field
        if (activePlayer.getBalance() >= currentField.getPrice()) {
            //player becomes owner of the ownableField
            currentField.setOwner(activePlayer);
            //Player loses as much money as the price of the ownableField 
            activePlayer.setBalance(activePlayer.getBalance() - currentField.getPrice());
        }
    }

    public Player getActivePlayer() {
        return players[activePlayerIndex];
    }
}
