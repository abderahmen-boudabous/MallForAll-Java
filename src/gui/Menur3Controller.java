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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author arafet
 */
public class Menur3Controller implements Initializable {

    @FXML
    private TextField tf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
}
