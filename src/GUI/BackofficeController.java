
package Gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import moodels.SelectedUser;
import moodels.User;
import service.UserService;

/**
 * FXML Controller class    
 *
 * @author user
 */


   public class BackofficeController implements Initializable {

    @FXML
    private TextField searchBoxUserss;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> adressColumn;
    @FXML
    private TableColumn<User, String> idColumn;
    @FXML
    private TableColumn<User, String> nomColumn;
    @FXML
    private TableColumn<User, String> prenomColumn;
    @FXML
    private TableColumn<User, String> codepostaleColumn;
    @FXML
    private TableColumn<User, String> villeColumn;
    @FXML
    private TableColumn<User, String> nomboutiqueColumn;
    @FXML
    private TableColumn<User, String> imageColumn;
    @FXML
    private TableColumn<User, String> numtelColumn;
    
    private ObservableList<User> usersList;
    private UserService su;
    @FXML
    private Button modifier;
    @FXML
    private Button profileadmin;
    @FXML
    private Button recherchenom;
    @FXML
    private Button statistique;
    
    
     @FXML 
    void LogoutButton(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("../gui/Login.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        su = new UserService();

        usersList = FXCollections.observableArrayList(su.readAll());

        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adressColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        codepostaleColumn.setCellValueFactory(new PropertyValueFactory<>("codepostale"));
        villeColumn.setCellValueFactory(new PropertyValueFactory<>("ville"));
        nomboutiqueColumn.setCellValueFactory(new PropertyValueFactory<>("nom_boutique"));
        
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
       
        numtelColumn.setCellValueFactory(new PropertyValueFactory<>("numtel"));

        usersTable.setItems(usersList);
        
         searchBoxUserss.textProperty().addListener((observable, oldValue, newValue) -> {
        String searchTerm = newValue.trim();
        if (searchTerm.isEmpty()) {
            // Si la zone de recherche est vide, afficher tous les utilisateurs
            usersList.clear();
            usersList.addAll(su.readAll());
        } else {
            // Rechercher les utilisateurs dont le nom contient le terme de recherche
            usersList.clear();
            usersList.addAll(su.searchByName(searchTerm));
        }
    });
    }




    

    @FXML
void deleteUsers(ActionEvent event) throws SQLException, IOException  {
   su = new UserService();
    User selectedUser = usersTable.getSelectionModel().getSelectedItem(); 
    if (selectedUser == null) { // Vérifier si aucun utilisateur n'est sélectionné
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("No user selected");
        alert.setContentText("Please select a user to delete!");
        alert.showAndWait();
    } else {
        su.delete(selectedUser.getIduser()); // Supprimer l'utilisateur sélectionné en utilisant la méthode delete() de la classe serviceUsers
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Backoffice.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
}
@FXML
private void updateUsers(ActionEvent event) throws IOException {
    SelectedUser.selectedUser = usersTable.getSelectionModel().getSelectedItem(); 
    if (SelectedUser.selectedUser == null) { // Vérifier si aucun utilisateur n'est sélectionné
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Aucun utilisateur sélectionné");
        alert.setContentText("Veuillez sélectionner un utilisateur à modifier !");
        alert.showAndWait();
    } else {
        // Charger la page de mise à jour d'utilisateur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/UpdateUser.fxml"));
        Parent root = loader.load();

        // Afficher la page de mise à jour d'utilisateur
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}




    @FXML
    private void profileadmin(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("../gui/adminprofile.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    @FXML
private void searchByName(ActionEvent event) {
    String searchTerm = searchBoxUserss.getText().trim();
    if (searchTerm.isEmpty()) {
        // Si la zone de recherche est vide, afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Zone de recherche vide");
        alert.setContentText("Veuillez saisir un nom à rechercher !");
        alert.showAndWait();
    } else {
        // Rechercher les utilisateurs dont le nom contient le terme de recherche
        usersList.clear();
        usersList.addAll(su.searchByName(searchTerm));
    }
}

    @FXML
    private void stat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Stat.fxml"));
Statistics controller = new Statistics();
loader.setController(controller);
Parent root = loader.load();
    }

    }

    
