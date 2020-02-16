package com.fichtepaulsen.polymony.DrawerController;

import com.fichtepaulsen.polymony.Gamelogic.Fields.Field;
import com.fichtepaulsen.polymony.Gamelogic.Fields.StreetField;
import com.fichtepaulsen.polymony.PolyMonyDrawer;
import com.fichtepaulsen.polymony.Settings;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.image.*;

public class GamefieldController implements Initializable {
    
    @FXML
    private GridPane gp;
    
    @FXML
    private Label diceResult1;
    
    @FXML
    private Label diceResult2;
    
    @FXML
    private Label currentplayer;
    
    @FXML
    private Button nextButton;
    
    @FXML
    private Button rollDice;
    
   //the height of a rectangle may be equal to the witdth of the field and viceversa, due to rotation
    private double defaultFieldHeight = 25.0;
    private double defaultFieldWidth = 50.0;
    //height and width of cornerfield are equal to the value of defaultFieldWidth 
    //if you change the defaultFieldWidth you should also resize the cornerFieldLength
    private double cornerFieldLength = 50.0;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Settings.getInstance().diceResult1 = this.diceResult1;
        Settings.getInstance().diceResult2 = this.diceResult2;
        Settings.getInstance().gameGridPane = this.gp;
        Settings.getInstance().playerLabel = this.currentplayer;
        Settings.getInstance().nextButton = this.nextButton;
        Settings.getInstance().rollDice = this.rollDice;
        
        Field[] gameFields = Settings.getInstance().gameInteface.getAllFields();

        //Every rectangle is added to a GridPane and is equal to one field
        //creates corner square (c1) (top left)
        Rectangle c1 = new Rectangle(cornerFieldLength, cornerFieldLength);
        c1.setStrokeWidth(1.0);
        c1.setStrokeType(StrokeType.OUTSIDE);
        c1.setStroke(Color.BLACK);
        c1.setFill(Color.WHITE);
        gp.add(c1, 0, 0);
        
        //creates corner square (c2) (top right)
        Rectangle c2 = new Rectangle(cornerFieldLength, cornerFieldLength);
        c2.setStrokeWidth(1.0);
        c2.setStrokeType(StrokeType.OUTSIDE);
        c2.setStroke(Color.BLACK);
        c2.setFill(Color.WHITE);
        gp.add(c2, 10, 0);
        
        //creates corner square (c3) (bottom left)
        Rectangle c3 = new Rectangle(cornerFieldLength, cornerFieldLength);
        c3.setStrokeWidth(1.0);
        c3.setStrokeType(StrokeType.OUTSIDE);
        c3.setStroke(Color.BLACK);
        c3.setFill(Color.WHITE);
        gp.add(c3, 0, 10);
        
        //creates corner square (c4) (bottom right)
        Rectangle c4 = new Rectangle(cornerFieldLength, cornerFieldLength);
        c4.setStrokeWidth(1.0);
        c4.setStrokeType(StrokeType.OUTSIDE);
        c4.setStroke(Color.BLACK);
        c4.setFill(Color.WHITE);
        gp.add(c4, 10, 10);
        
        //creates upper horizontal fields (LEFT)
        for(int i = 1; i<=9; i++) {
            
            Color c = Color.WHITE;
            
            if (gameFields[(20 - i)] instanceof StreetField) {
                c = ((StreetField)gameFields[(20 - i)]).getColor();
            }
            
            Rectangle rec = new Rectangle();
            rec.setHeight(defaultFieldHeight);
            rec.setWidth(defaultFieldWidth);
            rec.setStrokeWidth(1.0);
            rec.setStrokeType(StrokeType.OUTSIDE);
            rec.setStroke(Color.BLACK);
            rec.setFill(c);

            gp.add(rec, 0, i);
        }
        
        //creates left vertical fields (UP)
        for(int i = 1; i<=9; i++){
            Color c = Color.WHITE;
            
            if (gameFields[(20 + i)] instanceof StreetField) {
                c = ((StreetField)gameFields[(20 + i)]).getColor();
            }
            
            Rectangle rec = new Rectangle();
            rec.setHeight(defaultFieldWidth);
            rec.setWidth(defaultFieldHeight);
            rec.setStrokeWidth(1.0);
            rec.setStrokeType(StrokeType.OUTSIDE);
            rec.setStroke(Color.BLACK);
            rec.setFill(c);
            gp.add(rec, i, 0);
        }
        
        //creates lower horizontal fields (RIGHT)
        for(int i = 1; i<=9; i++) {
            Color c = Color.WHITE;
            
            if (gameFields[(30 + i)] instanceof StreetField) {
                c = ((StreetField)gameFields[(30 + i)]).getColor();
            }
            
            Rectangle rec = new Rectangle();
            rec.setHeight(defaultFieldHeight);
            rec.setWidth(defaultFieldWidth);
            rec.setStrokeWidth(1.0);
            rec.setStrokeType(StrokeType.OUTSIDE);
            rec.setStroke(Color.BLACK);
            rec.setFill(c);
            gp.add(rec, 10, i);
        }
        
        //creates right vertical fields (DOWN)
        for(int i = 1; i<=9; i++){
            Color c = Color.WHITE;
            
            if (gameFields[(10-i)] instanceof StreetField) {
                c = ((StreetField)gameFields[(10-i)]).getColor();
            }
            
            Rectangle rec = new Rectangle();
            rec.setHeight(defaultFieldWidth);
            rec.setWidth(defaultFieldHeight);
            rec.setStrokeWidth(1.0);
            rec.setStrokeType(StrokeType.OUTSIDE);
            rec.setStroke(Color.BLACK);
            rec.setFill(c);
            gp.add(rec, i, 10);
        }  
        
       
    }
    
    public void rollDice(Event e) {
        PolyMonyDrawer.getInstance().onRoll.handle(); 
    }
     
    public void onMenuGameQuit(Event e) {
        
    }
    
    public void onMenuHelpAbout(Event e) {
        System.out.println("Version: " + Settings.getInstance().ApplicationVersion);
    }
    
    public void onNextMove(Event e) {
        PolyMonyDrawer.getInstance().onNextTurn.handle(); 
    }
}