/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import gui.NotificationController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author arafet
 */
public class Main extends Application {
    private static int id_user=1;

    public static int getId_user() {
        return id_user;
    }

    public static void setId_user(int id_user) {
        Main.id_user = id_user;
    }
    

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/menur.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Afficher Reclamation");
            primaryStage.show();
            
//            if(id_user==1){
//                Stage stage = new Stage();
//                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../gui/Notification.fxml"));
//                Parent root1 = loader1.load();
//                NotificationController controller = loader1.getController();
//                Scene scene1 = new Scene(root1);
//                stage.setScene(scene1);
//                stage.show();
//            }

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    /**
     * @param args the command line aarguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
