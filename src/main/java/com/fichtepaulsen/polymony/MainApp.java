package com.fichtepaulsen.polymony;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    PolyMonyDrawer polyMonyDrawer;
    
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        this.polyMonyDrawer = new PolyMonyDrawer(stage);
        VBox vbox = new VBox();
        
        stage.setTitle("PolyMony");
        Button btn = new Button();
        btn.setText("Neues Spiel starten");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               polyMonyDrawer.onNewGame.handle();        
            }
        });
        vbox.getChildren().add(btn);
        root.getChildren().add(vbox);

        stage.setScene(new Scene(root, 600, 600));
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
