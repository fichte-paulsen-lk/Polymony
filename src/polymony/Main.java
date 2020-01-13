package polymony;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        
        primaryStage.setTitle("PolyMony");
        Button btn = new Button();
        btn.setText("Say 'OnNewGame'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               polyMonyDrawer.onNewGame.handle();        
            }
        });

        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 200, 300));
        primaryStage.show();
    }
}
