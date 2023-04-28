/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import moodels.SelectedUser;
import moodels.User;
import moodels.UserHolder;
import service.UserService;
import utis.Maconnexion;

/**
 * FXML Controller class
 *
 * @author user
 */
public class UpdateuserController implements Initializable {

      @FXML
    private TextField mail;
    @FXML
    private TextField nom;
    @FXML
    private TextField Adreese;
    @FXML
    private TextField prenom;
    @FXML
    private TextField codepostale;
    @FXML
    private TextField ville;
    @FXML
    private TextField nomBoutique;
    @FXML
    private Button updateprofile;
       User u =new User();
    UserService us =new UserService();
      User currentUser = UserHolder.getInstance().getUser();

    /**
     * Initializes the controller class.
     */
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 // récupérer l'utilisateur sélectionné à partir de la tableview

 
   User selectedUser = SelectedUser.selectedUser;
    mail.setText(selectedUser.getEmail());
    nom.setText(selectedUser.getNom());
    prenom.setText(selectedUser.getPrenom());
    Adreese.setText(selectedUser.getAdresse());
    codepostale.setText(selectedUser.getCodepostale());
    ville.setText(selectedUser.getVille());
    nomBoutique.setText(selectedUser.getNom_boutique());
}

    
@FXML
void modifier(ActionEvent event) throws SQLException, IOException {
    User selectedUser = SelectedUser.selectedUser;
    selectedUser.setEmail(mail.getText());
    selectedUser.setNom(nom.getText());
    selectedUser.setPrenom(prenom.getText());
    selectedUser.setAdresse(codepostale.getText());
    selectedUser.setVille(ville.getText());
    selectedUser.setNom_boutique(nomBoutique.getText());

    Connection conn = Maconnexion.getInstance().getCnx();
    String query = "UPDATE user SET email=?, nom=?, prenom=?, adresse=?, ville=?, nom_boutique=? WHERE id=?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, selectedUser.getEmail());
    statement.setString(2, selectedUser.getNom());
    statement.setString(3, selectedUser.getPrenom());
    statement.setString(4, selectedUser.getAdresse());
    statement.setString(5, selectedUser.getVille());
    statement.setString(6, selectedUser.getNom_boutique());
    statement.setInt(7, selectedUser.getIduser());
    System.out.println("Updating user with ID: " + selectedUser.getIduser());
    int rowsAffected = statement.executeUpdate();
    if (rowsAffected == 1) {
        System.out.println("User updated successfully!");
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Backoffice.fxml"));
        Parent root = loader.load();

        // Afficher la page Backoffice.fxml
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // Fermer la fenêtre actuelle
        ((Node)(event.getSource())).getScene().getWindow().hide();
    } else {
        System.out.println("Error updating user: invalid ID or no rows affected.");
    }

}}

