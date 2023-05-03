package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static org.omg.CORBA.AnySeqHelper.id;
import tn.esprit.entities.Category;
import tn.esprit.services.CategoryEventService;

public class ModifierCategoryController implements Initializable {

    
    @FXML
    private TextField titre;
    @FXML
    private TextField description;

    private Category category;
    @FXML
    private Button Modifier_Category;
    @FXML
    private Button Annuler;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setCategory(Category category) {
        this.category = category;
        titre.setText(category.getTitre());
        description.setText(category.getDescription());
    }
/*
    @FXML
    private void Modifier_Category(ActionEvent event) {
        category.setTitre(titre.getText());
        category.setDescription(description.getText());

        CategoryEventService categoryEventService = new CategoryEventService();
        categoryEventService.update(category);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Category modified");
        ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
        Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
        okBtn.setOnAction(evt -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCategory.fxml"));
            try {
                Parent root = loader.load();
                titre.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        alert.showAndWait();
    }

    
@FXML
void Modifier_Category(ActionEvent event) {
    if (titre.getText().isEmpty() || description.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        Category cat = new Category();
        cat.setId(category.getId()); // Use the category object's id instead of the undefined id variable
        cat.setTitre(titre.getText());
        cat.setDescription(description.getText());
        // Create an instance of CategoryEventService and call the update method on it
        CategoryEventService categoryEventService = new CategoryEventService();
        categoryEventService.update(cat);
        JOptionPane.showMessageDialog(null, "Category modified successfully");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
*/


    
    
    
    @FXML
    private void Annuler(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCategory.fxml"));
        try {
            Parent root = loader.load();
            titre.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
