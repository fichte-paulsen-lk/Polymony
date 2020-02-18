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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class GamefieldController implements Initializable {

    @FXML
    private GridPane gp;

    @FXML
    private Label diceResult1;

    @FXML
    private Label diceResult2;

    @FXML
    private Button rollDice;

    @FXML
    private GridPane cardGridPane;

    //the height of a rectangle may be equal to the witdth of the field and viceversa, due to rotation
    private double defaultFieldHeight = 50.0;
    private double defaultFieldWidth = 100.0;
    //height and width of cornerfield are equal to the value of defaultFieldWidth
    //if you change the defaultFieldWidth you should also resize the cornerFieldLength
    private double cornerFieldLength = 100.0;
   
    private Field[] gameFields = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //get vars from Singleton
        Settings.getInstance().diceResult1 = this.diceResult1;
        Settings.getInstance().diceResult2 = this.diceResult2;
        Settings.getInstance().gameGridPane = this.gp;
        Settings.getInstance().rollDice = this.rollDice;

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
    }

    private void setupRow(int x, int y, int factor, boolean subtract, boolean horizontal) {
        for(int i = 1; i<=9; i++) {
            Color c = Color.LIGHTGREEN;
            boolean isStreetField = false;

            int fac = subtract ? (factor - i) : (factor + i);
            //checks whether or not a field is a streetField and sets isStreetField accordingly
            //furthermore it determines the appropriate color for the streetField
            if (gameFields[fac] instanceof StreetField) {
                c = ((StreetField)gameFields[fac]).getColor();
                isStreetField = true;
            }
            //draws the base fields based on it's properties (StreetField, not StreetField etc.) 
            Rectangle rec = new Rectangle();
            rec.setHeight(horizontal ? (isStreetField ? defaultFieldWidth * 3/4 : defaultFieldWidth) : (defaultFieldHeight));
            rec.setWidth(horizontal ? (defaultFieldHeight) : (isStreetField ? defaultFieldWidth * 3/4 : defaultFieldWidth));
            rec.setStrokeWidth(1.0);
            rec.setStrokeType(StrokeType.INSIDE);
            rec.setStroke(Color.BLACK);
            rec.setFill(Color.LIGHTGREEN);
            
            Rectangle colorRec = new Rectangle();
            //StreetFields also receive there specific color, that is shown an upper rectangle
            if(isStreetField){
                colorRec.setHeight(horizontal ? defaultFieldWidth/4 : defaultFieldHeight);
                colorRec.setWidth(horizontal ? defaultFieldHeight : defaultFieldWidth/4);
                colorRec.setStrokeWidth(1.0);
                colorRec.setStrokeType(StrokeType.INSIDE);
                colorRec.setStroke(Color.BLACK);
                colorRec.setFill(c);
            }
            
            Pane box;
            //adds the base rectangle & topper one (for the streets) according to it's orientation 
            //decides if a HBox or Vbox is necessary
            if(!horizontal) {
                box = new HBox();
                if(factor == 20) {
                    box.getChildren().add(rec);
                    box.getChildren().add(colorRec);
                }
                else {
                box.getChildren().add(colorRec);
                box.getChildren().add(rec);
                }
            }
            else {
                box = new VBox();
                if(factor == 20) {
                    box.getChildren().add(rec);
                    box.getChildren().add(colorRec);

                }
                else {
                    box.getChildren().add(colorRec);
                    box.getChildren().add(rec);
                }
            }
            //OnMouseClick Event that shows details about Fields
            box.setOnMouseClicked( ( e ) ->
            {
              this.onCardClick(gameFields[fac]);
            } );

            gp.add(box, ((x==-1) ? i : x), ((y==-1) ? i : y));
        }
    }
    
    private void onCardClick(Field field) {
        if (field instanceof StreetField) {
            StreetField f = (StreetField)field;
            showStreetCard(f);
        }
    }
    //shows a detailed view of a streetCard with specific information
    private void showStreetCard(StreetField field){
        int price = field.getPrice();
        Color color = field.getColor();
        String name = field.getName();

        VBox vbox = new VBox();

        Rectangle rec = new Rectangle();
        rec.setHeight((defaultFieldWidth * 3) * 3/4);
        rec.setWidth((defaultFieldHeight * 3));
        rec.setStrokeWidth(2.0);
        rec.setStrokeType(StrokeType.INSIDE);
        rec.setStroke(Color.BLACK);
        rec.setFill(Color.LIGHTGREEN);

        Rectangle colorRec = new Rectangle();
        colorRec.setHeight((defaultFieldWidth * 3)/4);
        colorRec.setWidth((defaultFieldHeight * 3));
        colorRec.setStrokeWidth(2.0);
        colorRec.setStrokeType(StrokeType.INSIDE);
        colorRec.setStroke(Color.BLACK);
        colorRec.setFill(color);

        vbox.getChildren().addAll(colorRec, rec);
        
        Label streetName = new Label(name);
        Label streetPrice = new Label(price + "$");
        streetName.setStyle("-fx-font-weight: bold;");
        streetPrice.setStyle("-fx-font-weight: bold;");
        
        
        
        StackPane stack = new StackPane(vbox, streetName, streetPrice);
        
        stack.setAlignment(streetName, Pos.CENTER);
        stack.setAlignment(streetPrice, Pos.BOTTOM_CENTER);
        
        stack.setOnMouseClicked( ( e ) ->
            {
              this.onDetailsCard(stack);
            } );
        
        this.cardGridPane.getChildren().add(stack);
    }
    
    public void onDetailsCard(StackPane stack) {
        this.cardGridPane.getChildren().clear();
    }

    private void setupCorner(int x, int y) {
        Rectangle c = new Rectangle(cornerFieldLength, cornerFieldLength);
        c.setStrokeWidth(1.0);
        c.setStrokeType(StrokeType.INSIDE);
        c.setStroke(Color.BLACK);
        c.setFill(Color.LIGHTGREEN);
        gp.add(c, x, y);
    }

    public void rollDice(Event e) {
        if (Settings.isNextTurnButton) {
            PolyMonyDrawer.getInstance().onNextTurn.handle();
        } else {
            PolyMonyDrawer.getInstance().onRoll.handle(); 
        }
    }

    public void onMenuGameQuit(Event e) {

    }

    public void onMenuHelpAbout(Event e) {
        System.out.println("Version: " + Settings.getInstance().ApplicationVersion);
    }
}
