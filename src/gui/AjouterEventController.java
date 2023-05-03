package gui;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.esprit.entities.Event;
import tn.esprit.services.CategoryEventService;
import tn.esprit.services.NewInterface;
import tn.esprit.services.EventService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import tn.esprit.entities.Category;

public class AjouterEventController implements Initializable {

    private TextField nom;

    private TextField spot;

    private TextArea duration;

    private DatePicker date;

    private TextField category;

    @FXML
    private ImageView imglogo;
    @FXML
    private TextArea Nom;
    @FXML
    private TextField Spot;
    @FXML
    private TextArea Duration;
    @FXML
    private DatePicker Date;
    @FXML
    private Button Ajouter_Event;
    @FXML
    private ChoiceBox<Category> cbcategory;
    
   // CategoryEventService categoryeventService = new CategoryEventService();
    // EventService eventService = new EventService();
    @FXML
    private Button RetourE;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        
        
      //  List<Category> categorys = categoryeventService.listerCategories();

      //  cbcategory.setItems(FXCollections.observableArrayList(categorys));
        cbcategory.setConverter(new StringConverter<Category>() {
            
            
            
            @Override
            public String toString(Category category) {
                return category == null ? "" : category.getTitre();
            }

            @Override
            public Category fromString(String string) {
                return null;
            }
                 });
    }

    @FXML
    public void Ajouter_Event(ActionEvent event) throws SQLException {

        if (Nom.getText().isEmpty()) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText(null);

            aler.setContentText("Le nom est vide !");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait(); 
        }
        else if (Spot.getText().isEmpty()) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText(null);

            aler.setContentText("Le nom est vide !");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait(); 
        }
        else if (Duration.getText().isEmpty()) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText(null);

            aler.setContentText("Le nom est vide !");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait(); 
        }
            else if (Date.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);


            alert.setContentText("Le nom est vide !");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait(); 
        }
        
        else {
            Category category = cbcategory.getValue(); 
            int spot = Integer.parseInt(Spot.getText());
            Event newEvent = new Event(Nom.getText(), spot, Duration.getText(),Date.getValue() ,category);
            EventService eventService = new EventService();
            eventService.ajouter(newEvent); 
        }
        
;


            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("event ajouter");
                    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(okButton);
                    Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
                    okBtn.setOnAction(evt -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichherproduct.fxml"));
                        try {
                            Parent root = loader.load();
                            Nom.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    });
                    alert.showAndWait();
        }


    
    
        
    @FXML
    private void RetourE(ActionEvent event) {

        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("ListEvent.fxml"));
            Parent view_2=loader.load();

            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(AjouterCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 

    
    
    
        
    }
        
        
    







