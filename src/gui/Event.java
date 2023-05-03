package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Date;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class Event extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
         //  Parent root = FXMLLoader.load(getClass().getResource("Front.fxml")); 
        Scene scene = new Scene(root);

        primaryStage.setTitle("Categories");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
