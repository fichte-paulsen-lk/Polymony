package com.fichtepaulsen.polymony;

import java.util.function.Consumer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PolyMonyPopup {
    public static Stage stage;
    
//        PolyMonyPopup.show 
//        (
//            "Do you really want to buy a house?",
//            (b) -> {
//                System.out.println(b);
//            }
//        );
    public static void show(String question, Consumer<Boolean> onButton) {
        final Stage dialog = new Stage();
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(stage);
        
        dialog.setOnCloseRequest(e -> onButton.accept(false));        

        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        
        HBox buttonHBox = new HBox(20);
        buttonHBox.setAlignment(Pos.CENTER);
        
        Button show = new Button("Yes");
        show.setOnAction(e -> {
            onButton.accept(true);
            dialog.close();
        });

        Button hide = new Button("No");
        hide.setOnAction(e -> {
            onButton.accept(false);
            dialog.close();
        });

        buttonHBox.getChildren().addAll(show, hide);
        dialogVbox.getChildren().addAll(new Text(question), buttonHBox);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }
}
