/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Gui.Forget_PasswordController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class VerifCodeController implements Initializable {
    @FXML
    private TextField tfCode;
    @FXML
    private Button BtnConfirmerCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void BtnConfirmerCode(ActionEvent event) throws IOException {
        if (Integer.parseInt(tfCode.getText()) == Forget_PasswordController.code)
        {
              // Charger la page de mise à jour d'utilisateur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Reset_Password.fxml"));
        Parent root = loader.load();

        // Afficher la page de mise à jour d'utilisateur
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        else 
        {
            Alert A = new Alert(Alert.AlertType.WARNING);
            A.setContentText("Code erroné ! ");
            A.show();
            
        }
    }

    @FXML
    private void btnAnnulerCode(ActionEvent event) {
       // Charger la page de mise à jour d'utilisateur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Forget_Password.fxml"));
        Parent root = loader.load();

        // Afficher la page de mise à jour d'utilisateur
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
