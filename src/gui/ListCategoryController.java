
package gui;


import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import tn.esprit.tools.MaConnexion;






public class ListCategoryController implements Initializable {

    
    @FXML
    private ListView<Category> afficheL;

    @FXML
    private Button ajouterBut;


    @FXML
    private Button SupprimerE;

    @FXML
    private Button ModifierE;
    

    @FXML
    private Button home;
    @FXML
    private Button evc;
    @FXML
    private Button ev;
    @FXML
    private Button front1;
    @FXML
    private Button front2;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
                evc.setOnAction(e -> {
        try {
            Parent afficherEvent = FXMLLoader.load(getClass().getResource("ListEvent.fxml"));
            Scene scene = new Scene(afficherEvent);
            Stage stage = (Stage) evc.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading page " + ex.getMessage());
        }
    });
        ev.setOnAction(e -> {
        try {
            Parent afficherCategory = FXMLLoader.load(getClass().getResource("ListCategory.fxml"));
            Scene scene = new Scene(afficherCategory);
            Stage stage = (Stage) ev.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading page " + ex.getMessage());
        }
    });
        home.setOnAction(e -> {
        try {
            Parent afficherShop = FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene scene = new Scene(afficherShop);
            Stage stage = (Stage) home.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading page " + ex.getMessage());
        }
    });
         front1.setOnAction(e -> {
        try {
            Parent afficherShop = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Scene scene = new Scene(afficherShop);
            Stage stage = (Stage) front1.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading affichershop.fxml: " + ex.getMessage());
        }
    });
        front2.setOnAction(e -> {
        try {
            Parent afficherShop = FXMLLoader.load(getClass().getResource("FXMLp.fxml"));
            Scene scene = new Scene(afficherShop);
            Stage stage = (Stage) front2.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading affichershop.fxml: " + ex.getMessage());
        }
    });
        
        
        // TODO
         CategoryEventService ps = null;
        try {
            ps = new CategoryEventService();
        } catch (SQLException ex) {
            Logger.getLogger(ListCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
void Supprimer_Category(ActionEvent event) throws SQLException {
    // get the selected category
    Category selectedCategory = afficheL.getSelectionModel().getSelectedItem();

    // delete the selected category from the database
    CategoryEventService categoryEventService = new CategoryEventService();
    categoryEventService.supprimer(selectedCategory);

    // remove the selected category from the list view
    afficheL.getItems().remove(selectedCategory);
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
    
 
    

@FXML
private void exportToPDF(ActionEvent event) throws SQLException {
    // Call the exportCategoryToPDF method from PdfExporter class
     
    Connection connection = MaConnexion.getInstance().getCnx();
    PdfExporter.exportCategoryToPDF(connection);
}


    }