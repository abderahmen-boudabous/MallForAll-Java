/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author arafet
 */
public class Menur3Controller implements Initializable {

    @FXML
    private TextField tf;
    @FXML
    private Button home;
    @FXML
    private Button evc;
    @FXML
    private Button ev;
    @FXML
    private Button front1;
    @FXML
    private Button front2;
    @FXML
    private Button ticket;
    @FXML
    private ImageView homeImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        home.setOnAction(e -> {
        try {
            Parent afficherEvent = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(afficherEvent);
            Stage stage = (Stage) home.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading page " + ex.getMessage());
        }
    });
        // TODO
    }    

    @FXML
    private void gtav(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAvis.fxml"));
                        try {
                            Parent root = loader.load();
                            tf.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }

    @FXML
    private void gtar(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
                        try {
                            Parent root = loader.load();
                            tf.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }

    @FXML
    private void gts(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("StatisticsAdmin.fxml"));
                        try {
                            Parent root = loader.load();
                            tf.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }

    @FXML
    private void gtn(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Notification.fxml"));
                        try {
                            Parent root = loader.load();
                            tf.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }

    @FXML
    private void playSound(ActionEvent event) {
    }
    
}
