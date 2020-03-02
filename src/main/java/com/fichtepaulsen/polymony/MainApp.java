package com.fichtepaulsen.polymony;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Settings.createInstance();
        PolyMonyDrawer.createInstance(stage);

        Parent parentRoot = null;
        
        try {
            parentRoot = FXMLLoader.load(getClass().getResource("/fxml/MainMenue.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(parentRoot, Settings.getInstance().WindowWidth, Settings.getInstance().WindowHeight);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setScene(scene);
        stage.show();
        
        PolyMonyPopup.stage = stage;
        PolyMonyPopup.show((b) -> System.out.println("test"));
        
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
