package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entities.Category;
import tn.esprit.entities.Event;
import tn.esprit.services.EventService;

/**
 * FXML Controller class
 *
 * @author
 */
public class ListEventController implements Initializable {

    @FXML
    private ListView<Event> afficheE;

    @FXML
    private Button AjouterE;

    @FXML
    private Button ModifierE;

    @FXML
    private Button SupprimerE;

    @FXML
    private Button Retour;

    @FXML
    private TextField NomE;

    @FXML
    private TextField DescriptionE;

    @FXML
    private DatePicker DateE;

    @FXML
    private TextField AdresseE;

    @FXML
    private ListView<Event> eventE;

@Override
public void initialize(URL url, ResourceBundle rb) {
    try {
        // TODO
        EventService es = new EventService();
        ObservableList<Event> events = FXCollections.observableArrayList(es.afficher());
        afficheE.setItems(events);
        System.out.println("afficheE: " + afficheE);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    

    
    
    @FXML
    void Ajouter_Event(javafx.event.ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvent.fxml"));
            Parent view_2 = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    @FXML
    void Modifier_Event(javafx.event.ActionEvent event) {

        try {
            Event selectedEvent = afficheE.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEvent.fxml"));
            Parent view_2 = loader.load();
           ModifierEventController MD = loader.getController();
            MD.getEvent(selectedEvent);
            System.out.println(selectedEvent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
*/
    
    @FXML
    void Supprimer_Event(javafx.event.ActionEvent event) {
        EventService es = new EventService();
        int selectedId = afficheE.getSelectionModel().getSelectedItem().getId();
        es.supprimer(selectedId);
        initialize(null, null);
    }
    
    
    


}