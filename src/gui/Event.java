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
<<<<<<< HEAD
    FXMLLoader menurLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent menurRoot = menurLoader.load();

        Scene menurScene = new Scene(menurRoot);

        Stage menurStage = new Stage();
        menurStage.setScene(menurScene);
        menurStage.setTitle("Menu");
        menurStage.show();


        FXMLLoader notificationLoader = new FXMLLoader(getClass().getResource("/gui/Notification.fxml"));
        Parent notificationRoot = notificationLoader.load();

        Scene notificationScene = new Scene(notificationRoot);

        Stage notificationStage = new Stage();
        notificationStage.setScene(notificationScene);
        notificationStage.setTitle("Notification");
        notificationStage.show();
=======
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
         //  Parent root = FXMLLoader.load(getClass().getResource("Front.fxml")); 
        Scene scene = new Scene(root);

        primaryStage.setTitle("Categories");
        primaryStage.setScene(scene);
        primaryStage.show();
>>>>>>> f0b84b16c6b3dac6c883d4f4a1ae6272f0c55c08
    }

    public static void main(String[] args) {
        launch(args);
    }
}
