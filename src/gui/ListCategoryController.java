
package gui;


import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import static java.time.Clock.system;
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
import javafx.stage.Stage;
import tn.esprit.entities.Category;
import tn.esprit.services.CategoryEventService;
import tn.esprit.services.NewInterface;

/**
 * FXML Controller class
 *
 * @author ZAKARIA
 */
public class ListCategoryController implements Initializable {

    
    @FXML
    private ListView<Category> afficheL;

    @FXML
    private Button ajouterBut;

    @FXML
    private Button Retour;

    @FXML
    private Button SupprimerE;

    @FXML
    private Button ModifierE;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        // TODO
         CategoryEventService ps = new CategoryEventService();
         ObservableList<Category> categorys =FXCollections.observableArrayList(ps.afficher());
        afficheL.setItems(categorys);
    
    }  
    
    @FXML
    public void ajouter_category(ActionEvent event) {
        
        
          try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AjouterCategory.fxml"));
            Parent view_2=loader.load();

            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(ListCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
        

    
     @FXML
    private void Afficher_Category(ActionEvent event) {
     // CategoryCRUD ps = new CategoryCRUD();
       // ObservableList<Category> categorys =FXCollections.observableArrayList(ps.afficher());
       //afficheL.setItems(categorys);
    }
    
    @FXML
     private void Retour(ActionEvent event) {

       
    }
     
    @FXML
    private void Supprimer_Category(ActionEvent event) {
            NewInterface rt = new CategoryEventService();

    int selectedId= afficheL.getSelectionModel().getSelectedItem().getId();
        rt.supprimer(selectedId);
        Afficher_Category(event);
    } 


    

    
   
  @FXML
    void Modifier_Category(ActionEvent event) {
        
    
    try {
        Category selectedCategory=afficheL.getSelectionModel().getSelectedItem();

        FXMLLoader loader= new FXMLLoader(getClass().getResource("ModifierCategory.fxml"));
        Parent view_2=loader.load();
        //ModifierCategoryController MD=loader.getController();
       // MD.getCategory(selectedCategory);
        System.out.println(selectedCategory);
        //MD.c=selectedCategory; // initialiser la variable c avec la catégorie sélectionnée
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(ListCategoryController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    }