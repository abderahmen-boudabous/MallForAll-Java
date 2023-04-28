/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import moodels.User;
import moodels.UserHolder;

/**
 * FXML Controller class
 *
 * @author user
 */
public class adminprofileController implements Initializable {

    @FXML
    private Label emailLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label adresseLabel;
    @FXML
    private Label codePostalLabel;
    @FXML
    private Label villeLabel;
    @FXML
    private Label telephoneLabel;
    @FXML
    private Button modifierButton;
    @FXML
    private ImageView img;
    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         User currentUser = UserHolder.getInstance().getUser();
                String imagePath = "C:\\xampp\\htdocs\\img\\" + adminprofileController.currentUser.getImage();

       
           
            
            // Set the user's information in the labels
            emailLabel.setText(currentUser.getEmail());
            nomLabel.setText(currentUser.getNom());
            prenomLabel.setText(currentUser.getPrenom());
            adresseLabel.setText(currentUser.getAdresse());
            codePostalLabel.setText(currentUser.getCodepostale());
            villeLabel.setText(currentUser.getVille());
            telephoneLabel.setText(currentUser.getNumtel());
            
             try {
            imageView.setImage(new Image(new FileInputStream(imagePath)));
        } catch (FileNotFoundException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
          
            
        
         } 
         
        // TODO
      
    
    

    @FXML
    private void modifier(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("../gui/Updateadmin.fxml"));
     
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        }    
    

