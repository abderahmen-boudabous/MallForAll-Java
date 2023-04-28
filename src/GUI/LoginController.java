/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import moodels.User;
import moodels.UserHolder;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController implements Initializable {
    private int loginAttempts = 0; // variable pour stocker le nombre de tentatives de connexion échouées
    private boolean isLocked = false;
        private int passwordAttempts = 0;// variable pour vérifier si l'interface

    @FXML
    private Label errorLabel;
    @FXML
    private TextField emailTextField;
    private UserHolder holder = UserHolder.getInstance();
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;
    @FXML
    private Hyperlink forgotPasswordLink;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
private Scene scene;
private Parent parent;
private Stage stage;
 List<String> Role;
void switchSceneLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     void switchSceneLogin1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Backoffice.fxml"));
     
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
       void switchSceneLogin2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Vendeurprofile.fxml"));
     
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
  
@FXML
private void handleLoginAction(ActionEvent event) {
    if (emailTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("please complete all fields");
        alert.showAndWait();
    } else {
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        UserService service = new UserService();
        ArrayList<User> users = service.readAll();
        System.out.println(users);
        boolean loginSuccess = false;
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPasswoed().equals(password)) {
                loginSuccess = true;
                System.out.println(Arrays.deepToString(user.getRoles()));
                Role = Arrays.asList(user.getRoles());
                // Vérifier si l'utilisateur est un simple utilisateur ou un administrateur
                holder.setUser(user);
                if (Role.get(0).contains("ROLE_ADMIN")) {
                    try {
                        System.out.println("Salut admin !");
                        switchSceneLogin1(event);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (Role.get(0).contains("ROLE_VENDEUR")) {
                    try {
                        System.out.println("Salut vendeur !");
                        switchSceneLogin2(event);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        System.out.println("Salut utilisateur !");
                        switchSceneLogin(event);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        if (!loginSuccess) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("incorrect credentials");
            alert.showAndWait();
            
            // Bloquer l'interface si l'utilisateur saisit un mot de passe incorrect 3 fois
            passwordAttempts++;
            if (passwordAttempts == 3) {
                loginButton.setDisable(true);
                passwordTextField.setDisable(true);
                emailTextField.setDisable(true);
                 
                signupButton.setDisable(true);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            loginButton.setDisable(false);
                            passwordTextField.setDisable(false);
                            emailTextField.setDisable(false);
                            passwordAttempts = 0;
                        });
                    }
                }, 60000); // 1 minute en millisecondes
            }
        }
    }
}





    @FXML
    private void handleSignupAction(ActionEvent event) throws IOException {
        
                Parent root = FXMLLoader.load(getClass().getResource("../gui/Inscription.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       
        
    }



    @FXML
    private void handleForgotPasswordAction(ActionEvent event) throws IOException {
        Stage stage;
            Parent root = FXMLLoader.load(getClass().getResource("../gui/Forget_Password.fxml"));

              stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              scene = new Scene(root);
              stage.setScene(scene);
              stage.show();
    }
    }
 
    
    
    
    
    
    

    
    
    
    
