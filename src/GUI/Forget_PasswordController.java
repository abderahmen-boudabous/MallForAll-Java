/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
import moodels.EmailSender;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class Forget_PasswordController implements Initializable {
    
     public static int code;
    public static String EmailReset ; 
    @FXML
    private TextField tfMailO;
    
    
    public static int generateVerificationCode() {
        // Générer un code de vérification aléatoire à 6 chiffres
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
    @FXML
    private Button BtnCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void BtnCode(ActionEvent event) throws IOException {
        code = generateVerificationCode();
        Alert A = new Alert(Alert.AlertType.WARNING);
        UserService su = new UserService();

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        boolean verifMail = tfMailO.getText().matches(emailRegex);

        if (!tfMailO.getText().equals("") && verifMail) {
            if (su.ChercherMail(tfMailO.getText()) == 1) {
                EmailReset = tfMailO.getText();
                EmailSender.sendEmail("mansour.mohamedaziz@esprit.tn", "waiajrrognajbeze", tfMailO.getText(), "Verification code", "Votre code est : " + code);

                 // Charger la page de mise à jour d'utilisateur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/VerifCode.fxml"));
        Parent root = loader.load();

        // Afficher la page de mise à jour d'utilisateur
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

            } else {
                A.setContentText("pas de compte lié avec cette adresse ! ");
                A.show();
            }
        } else {
            A.setContentText("Veuillez saisir une adresse mail valide ! ");
            A.show();
        }
    }
    
    @FXML
    private void btnAnnulerForgot(ActionEvent event) throws IOException {
        // Charger la page de mise à jour d'utilisateur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/login.fxml"));
        Parent root = loader.load();

        // Afficher la page de mise à jour d'utilisateur
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
