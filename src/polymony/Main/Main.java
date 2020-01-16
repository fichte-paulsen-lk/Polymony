package polymony.Main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    PolyMonyDrawer polyMonyDrawer;
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        this.polyMonyDrawer = new PolyMonyDrawer(root);
        VBox vbox = new VBox();
        
        primaryStage.setTitle("PolyMony");
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

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }
}
