package com.fichtepaulsen.polymony.DrawerController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class GamefieldController implements Initializable {
    
    @FXML
    private GridPane gp;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        //Every rectangle is added to a GridPane and is equal to one field
        //creates corner square (c1) (top left)
        Rectangle c1 = new Rectangle(50.0, 50.0);
        c1.setStrokeWidth(1.0);
        c1.setStrokeType(StrokeType.OUTSIDE);
        c1.setStroke(Color.BLACK);
        c1.setFill(Color.WHITE);
        gp.add(c1, 0, 0);
        
        //creates corner square (c2) (top right)
        Rectangle c2 = new Rectangle(50.0, 50.0);
        c2.setStrokeWidth(1.0);
        c2.setStrokeType(StrokeType.OUTSIDE);
        c2.setStroke(Color.BLACK);
        c2.setFill(Color.WHITE);
        gp.add(c2, 10, 0);
        
        //creates corner square (c3) (bottom left)
        Rectangle c3 = new Rectangle(50.0, 50.0);
        c3.setStrokeWidth(1.0);
        c3.setStrokeType(StrokeType.OUTSIDE);
        c3.setStroke(Color.BLACK);
        c3.setFill(Color.WHITE);
        gp.add(c3, 0, 10);
        
        //creates corner square (c4) (bottom right)
        Rectangle c4 = new Rectangle(50.0, 50.0);
        c4.setStrokeWidth(1.0);
        c4.setStrokeType(StrokeType.OUTSIDE);
        c4.setStroke(Color.BLACK);
        c4.setFill(Color.WHITE);
        gp.add(c4, 10, 10);
        
        //creates upper horizontal fields
        for(int i = 1; i<=9; i++) {
                Rectangle rec = new Rectangle();
                rec.setHeight(25.0);
                rec.setWidth(50.0);
                rec.setStrokeWidth(1.0);
                rec.setStrokeType(StrokeType.OUTSIDE);
                rec.setStroke(Color.BLACK);
                rec.setFill(Color.WHITE);
          
                gp.add(rec, 0, i);
        }
        
        //creates left vertical fields
        for(int i = 1; i<=9; i++){
            Rectangle rec = new Rectangle();
            rec.setHeight(50.0);
            rec.setWidth(25.0);
            rec.setStrokeWidth(1.0);
            rec.setStrokeType(StrokeType.OUTSIDE);
            rec.setStroke(Color.BLACK);
            rec.setFill(Color.WHITE);
            gp.add(rec, i, 0);
        }
        
        //creates lower horizontal fields
        for(int i = 1; i<=9; i++) {
            Rectangle rec = new Rectangle();
            rec.setHeight(25.0);
            rec.setWidth(50.0);
            rec.setStrokeWidth(1.0);
            rec.setStrokeType(StrokeType.OUTSIDE);
            rec.setStroke(Color.BLACK);
            rec.setFill(Color.WHITE);
            gp.add(rec, 10, i);
        }
        
        //creates right vertical fields
        for(int i = 1; i<=9; i++){
            Rectangle rec = new Rectangle();
            rec.setHeight(50.0);
            rec.setWidth(25.0);
            rec.setStrokeWidth(1.0);
            rec.setStrokeType(StrokeType.OUTSIDE);
            rec.setStroke(Color.BLACK);
            rec.setFill(Color.WHITE);
            gp.add(rec, i, 10);
        }   
    }   
}