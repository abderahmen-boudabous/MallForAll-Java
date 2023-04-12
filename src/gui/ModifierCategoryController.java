package gui;

import static java.awt.PageAttributes.MediaType.C;
import javafx.scene.control.TextField;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tn.esprit.entities.Category;
import tn.esprit.services.CategoryEventService;





public class ModifierCategoryController implements Initializable {
    
    
     Category c = new Category() ;
     CategoryEventService hj = new CategoryEventService();
    
     @FXML
    private Button Annuler;

    @FXML
    private Button Modifier_Category;

    @FXML
    private TextField description;

    @FXML
    private TextField titre;

    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        titre.setText(c.getTitre());
        description.setText(c.getDescription());
    } 
    
    
     @FXML
    void Annuler(ActionEvent event) {

    }
    
    
    
  void getCategory(Category C) {
    c.setTitre(C.getTitre());
    c.setDescription(C.getDescription());

}

        

        @FXML
    
    private void Modifier_Category(ActionEvent event) {

       
        try {
        c.setTitre(titre.getText());
        c.setDescription(description.getText());
        hj.update(c);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCategory.fxml"));
        Parent view_2 = loader.load();
        Scene scene = new Scene(view_2);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(ModifierCategoryController.class.getName()).log(Level.SEVERE, null, ex);
    }


    }
    
    



    
}