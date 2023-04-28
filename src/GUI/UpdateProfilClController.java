/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import moodels.User;
import moodels.UserHolder;
import service.UserService;
import utis.Maconnexion;

/**
 * FXML Controller class
 *
 * @author user
 */
public class UpdateProfilClController implements Initializable {

    @FXML
    private TextField fxiduser;
    @FXML
    private TextField mail;
    @FXML
    private TextField tel;
    @FXML
    private TextField iduser;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField Ville;
    @FXML
    private TextField Adreese;
    @FXML
    private Button updateprofile;

    /**
     * Initializes the controller class.
     */
    User u =new User();
    UserService us =new UserService();
      User currentUser = UserHolder.getInstance().getUser();
  
       

  
  

   
   
  
 
//    // récupérer les valeurs des champs de texte
//    int iduser = Integer.parseInt(iduser.getText());
//    String email = mail.getText();
//    String nom = nom.getText();
//    String prenom = prenom.getText();
//    String codepostale = codepstale.getText();
//    String ville = ville.getText();
//    String numtel = tel.getText();
//    String adresse = adresse.getText();
//    
    // créer un objet User avec les valeurs récupérées
    //User user = new User(iduser, email, nom, prenom, codepostale, ville, numtel, adresse);
//
//    // mettre à jour l'utilisateur dans la base de données
//    UserService userService = new UserService();
//    userService.update(user);
//
//    // afficher un message de succès
//    Alert alert = new Alert(AlertType.INFORMATION);
//    alert.setTitle("Succès");
//    alert.setHeaderText(null);
//    alert.setContentText("Profil mis à jour avec succès !");
//    alert.showAndWait();
//}

       
  

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nom.setText(currentUser.getNom());
        mail.setText(currentUser.getEmail());
        prenom.setText(currentUser.getPrenom());
        Adreese.setText(currentUser.getAdresse());
        Ville.setText(currentUser.getVille());
        tel.setText(currentUser.getNumtel());
    }

    @FXML
    void modifier(ActionEvent event) throws SQLException {
        User u = UserHolder.getInstance().getUser();
        u.setEmail(mail.getText());
        u.setNom(nom.getText());
        u.setPrenom(prenom.getText());
        u.setAdresse(Adreese.getText());
        u.setNumtel(tel.getText());
        u.setVille(Ville.getText());
    Connection conn = Maconnexion.getInstance().getCnx();
        String query = "UPDATE user SET email=?, nom=?, prenom=?, adresse=?, ville=?, numtel=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, u.getEmail());
        statement.setString(2, u.getNom());
        statement.setString(3, u.getPrenom());
        statement.setString(4, u.getAdresse());
        statement.setString(5, u.getVille());
        statement.setString(6, u.getNumtel());
        statement.setInt(7, u.getIduser());
        System.out.println("Updating user with ID: " + u.getIduser());
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected == 1) {
            System.out.println("User updated successfully!");
        } else {
            System.out.println("Error updating user: invalid ID or no rows affected.");
        }
    }

}

            
           

    
    

