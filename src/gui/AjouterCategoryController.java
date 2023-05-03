
package gui;

import javafx.scene.control.TextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.esprit.entities.Category;
import tn.esprit.services.CategoryEventService;
import tn.esprit.services.NewInterface;
import tn.esprit.services.CategoryEventService;


/**
 * FXML Controller class
 *
 * @author ZAKARIA
 */
public class AjouterCategoryController implements Initializable {
    


    
    @FXML
    private Button Ajouter_Category;

    @FXML
    private TextArea Description;

    @FXML
    private Button RetourE;

    @FXML
    private TextArea Titre;

    @FXML
    private ImageView imglogo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
@FXML
public void Ajouter_Category(ActionEvent event) throws SQLException {
    if (Titre.getText().length() == 0||Description.getText().length() ==0) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur de saisie !");
        alert.setContentText("Please remplir tous les champs"+ "");
        alert.show();
    } else if (!Titre.getText().matches("^[a-zA-Z]+$")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur de saisie !");
        alert.setContentText("Le titre ne doit pas contenir de nombres"+ "");
        alert.show();
    } else if (Description.getText().length() < 4) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur de saisie !");
        alert.setContentText("La description doit contenir au moins 4 lettres"+ "");
        alert.show();
    } else {
        try {
            Category C= new Category();
            C.setTitre(Titre.getText());
            C.setDescription(Description.getText());

            NewInterface Ct = new CategoryEventService();
            Ct.ajouter(C);

            FXMLLoader loader= new FXMLLoader(getClass().getResource("ListCategory.fxml"));
            Parent view_2=loader.load();

            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


    @FXML
    private void RetourE(ActionEvent event) {

        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("ListCategory.fxml"));
            Parent view_2=loader.load();

            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(AjouterCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
}