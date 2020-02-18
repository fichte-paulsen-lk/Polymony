package com.fichtepaulsen.polymony.DrawerController;

import com.fichtepaulsen.polymony.DrawerEvents.OnRoll;
import com.fichtepaulsen.polymony.Gamelogic.Fields.Field;
import com.fichtepaulsen.polymony.Gamelogic.Fields.StreetField;
import com.fichtepaulsen.polymony.PolyMonyDrawer;
import com.fichtepaulsen.polymony.Settings;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.image.*;
import javafx.scene.shape.Circle;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Group;
import javafx.scene.layout.Priority;

public class GamefieldController implements Initializable {
    
    //static here because logic concerns implementation of this class
    //requires: index of the player that we want the shape of
    //returns : node for display of the index-th player
    public static Node getPlayerNode(GridPane gp, int index) {
        //TODO fix NPE here
        if (gp == null) {
            System.out.println("GridPane is NULL");
        }
        //returns the index + (number of fields) - th child of the GridPane
        return gp.getChildren().get(index + 4*Settings.getInstance().rowLength);
    }
    
    @FXML
    private GridPane gp;
    
    @FXML
    private Label diceResult1;
    
    @FXML
    private Label diceResult2;
    
    @FXML
    private Button nextButton;
    
    @FXML
    private Button rollDice;
    
   //the height of a rectangle may be equal to the witdth of the field and viceversa, due to rotation
    private double defaultFieldHeight = 50.0;
    private double defaultFieldWidth = 100.0;

    //height and width of cornerfield are equal to the value of defaultFieldWidth 
    //if you change the defaultFieldWidth you should also resize the cornerFieldLength
    private double cornerFieldLength = 100.0;
    
    private Field[] gameFields = null;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Settings.getInstance().diceResult1 = this.diceResult1;
        Settings.getInstance().diceResult2 = this.diceResult2;
        Settings.getInstance().gameGridPane = this.gp;
        Settings.getInstance().rollDice = this.rollDice;
        Settings.getInstance().nextButton = this.nextButton;
        
        gameFields = Settings.getInstance().gameInteface.getAllFields();
        
        //creates corner square (c4) (bottom right)
        setupCorner(10, 10); 
        
        //creates corner square (c3) (bottom left)
        setupCorner(0, 10);

        //Every rectangle is added to a GridPane and is equal to one field
        //creates corner square (c1) (top left)
        setupCorner(0, 0);
        
        //creates corner square (c2) (top right)
        setupCorner(10, 0);
        
        //creates upper horizontal fields (LEFT)
        setupRow(0, -1, 20, true, false);
        
        //creates left vertical fields (UP)
        setupRow(-1, 0, 20, false, true);
        
        //creates lower horizontal fields (RIGHT)
        setupRow(10, -1, 30, false, false);

        //creates right vertical fields (DOWN)
        setupRow(-1, 10, 10, true, true);  
        
        //a probably temporary solution to add all players' shapes at 
        //the start of the game
        for (int i = 0; i < Settings.getInstance().numberOfPlayers; i++) {
            //add a circle with radius 8
            Circle c = new Circle(Settings.getInstance().playerRadius);
           
            gp.add(c, 0, 0);
            
            //all margins are defined from the top left
            GridPane.setValignment(c, VPos.TOP);
            GridPane.setHalignment(c, HPos.LEFT);
          
        }
        
        //PolyMonyDrawer.getInstance().onRoll.drawPlayerAt(2);
        //Node player = GamefieldController.getPlayerNode(gp, 2);
        //GridPane.setConstraints(player, 3, 0);
        //GridPane.setValignment(player, VPos.TOP);
        //GridPane.setMargin(player, new Insets(10, 0, 0, 0.5));
       
    }
    
    private void setupRow(int x, int y, int factor, boolean subtract, boolean horizontal) {
        for(int i = 1; i<=9; i++){
            Color c = Color.WHITE;
            
            int fac = subtract ? (factor - i) : (factor + i);
            if (gameFields[fac] instanceof StreetField) {
                c = ((StreetField)gameFields[fac]).getColor();
            }
            
            Rectangle rec = new Rectangle();
            rec.setHeight(horizontal ? defaultFieldWidth : defaultFieldHeight);
            rec.setWidth(horizontal ? defaultFieldHeight : defaultFieldWidth);
            rec.setStrokeWidth(1.0);
            rec.setStrokeType(StrokeType.OUTSIDE);
            rec.setFill(c);
            HBox hbox = new HBox();
            
            /*
            hbox.setStyle("-fx-background-color: " + this.toRGBCode(c) + ";" +
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 1 2 1 2;" +
                      "-fx-border-color: black;");     
            */       
                   
            hbox.getChildren().add(rec);
                        
            gp.add(hbox, ((x==-1) ? i : x), ((y==-1) ? i : y));
        }  
    }
    
    private void setupCorner(int x, int y) {
        Rectangle c = new Rectangle(cornerFieldLength, cornerFieldLength);
        c.setStrokeWidth(1.0);
        c.setStrokeType(StrokeType.OUTSIDE);
        c.setStroke(Color.BLACK);
        c.setFill(Color.WHITE);
        gp.add(c, x, y);
    }
    
    private String toRGBCode( Color color )
    {
        return String.format( "#%02X%02X%02X",
            (int)( color.getRed() * 255 ),
            (int)( color.getGreen() * 255 ),
            (int)( color.getBlue() * 255 ) );
    }
    
    public void rollDice(Event e) {
        PolyMonyDrawer.getInstance().onRoll.handle(); 
    }
     
    public void onMenuGameQuit(Event e) {
        
    }
    
    public void onMenuHelpAbout(Event e) {
        System.out.println("Version: " + Settings.getInstance().ApplicationVersion);
    }
    
    public void onNextTurn(Event e) {
        PolyMonyDrawer.getInstance().onNextTurn.handle(); 
    }
}