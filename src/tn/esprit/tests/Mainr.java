/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import gui.NotificationController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author arafet
 */
public class Mainr extends Application {
    private static int id_user=1;

    public static int getId_user() {
        return id_user;
    }

    public static void setId_user(int id_user) {
        Mainr.id_user = id_user;
    }
    

    @Override
   public void start(Stage primaryStage) {
    try {
        FXMLLoader menurLoader = new FXMLLoader(getClass().getResource("/gui/menur.fxml"));
        Parent menurRoot = menurLoader.load();

        Scene menurScene = new Scene(menurRoot);

        Stage menurStage = new Stage();
        menurStage.setScene(menurScene);
        menurStage.setTitle("Menur");
        menurStage.show();


        FXMLLoader notificationLoader = new FXMLLoader(getClass().getResource("/gui/Notification.fxml"));
        Parent notificationRoot = notificationLoader.load();

        Scene notificationScene = new Scene(notificationRoot);

        Stage notificationStage = new Stage();
        notificationStage.setScene(notificationScene);
        notificationStage.setTitle("Notification");
        notificationStage.show();

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
