/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author arafet
 */
public class MenurController implements Initializable {

    @FXML
    private TextField tf;

    /**
     * Initializes the controller class
     *      * 

     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gta(MouseEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("menur2.fxml"));
                        try {
                            Parent root = loader.load();
                            tf.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }

    @FXML
    private void gtc(MouseEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("menur3.fxml"));
                        try {
                            Parent root = loader.load();
                            tf.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }

    @FXML
    private void gtt(MouseEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("time.fxml"));
                        try {
                            Parent root = loader.load();
                            tf.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }
    
}
