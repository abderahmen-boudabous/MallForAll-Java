package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
<<<<<<< HEAD
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherFournisseur.fxml"));
=======
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventTableGridPane.fxml"));
>>>>>>> f0b84b16c6b3dac6c883d4f4a1ae6272f0c55c08
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
