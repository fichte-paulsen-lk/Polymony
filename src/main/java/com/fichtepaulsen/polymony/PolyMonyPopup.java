package com.fichtepaulsen.polymony;

import java.util.function.Consumer;
import java.util.function.Function;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PolyMonyPopup {
    public static Stage stage;
    
    public static void show(Consumer<Boolean> onButton) {
        final Popup popup = new Popup();
        
        Button show = new Button("Test");

        onButton.accept(Boolean.FALSE);


        Button hide = new Button("Test2");

        
        popup.getContent().addAll(new Circle(25, 25, 50, Color.GREEN), hide, show);
        popup.show(stage);
    }
   
//    private Stage build() {
//        isDisplayed = true;
//        final Popup popup = new Popup();
//        popup.getContent().addAll(new Circle(25, 25, 50, Color.GREEN));
//
//        Button show = new Button(this.leftButtonText);
//        show.setOnAction((ActionEvent event) -> {
//            popup.show(primaryStage);
//        });
//
//        Button hide = new Button(this.rightButtonText);
//        hide.setOnAction((ActionEvent event) -> {
//            popup.hide();
//        });
//
//        HBox layout = new HBox(10);
//        layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
//        layout.getChildren().addAll(show, hide);
//        primaryStage.setScene(new Scene(layout));
//        return primaryStage;
//    }
    
}
