package gui;

import com.itextpdf.text.DocumentException;
import static java.awt.SystemColor.text;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import tn.esprit.services.ParticipantService;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import tn.esprit.tools.MaConnexion;


/**
 * FXML Controller class
 *
 * @author
 */
public class FrontController implements Initializable {

        @FXML
    private ComboBox<Event> eventComboBox;

    @FXML
    private TextField shopNameField;
    @FXML
    private Label messageLabel;
    
    private ParticipantService participantService;
    
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
    
    @FXML
    private Label eventNameLabel;
    
    public void setEventName(String eventName) {
        eventNameLabel.setText(eventName);
    }
    
    @FXML
    private ListView<Event> afficheE;


    @FXML
    private Button SupprimerE;


    private ListView<Event> eventE;
    @FXML
    private Button ajouterBut;
    @FXML
    private Button sortButton;
    
    private List<Event> eventList;
    

@Override
public void initialize(URL url, ResourceBundle rb) {
    

    
    
    
    
     evc.setOnAction(e -> {
        try {
            Parent afficherEvent = FXMLLoader.load(getClass().getResource("Front.fxml"));
            Scene scene = new Scene(afficherEvent);
            Stage stage = (Stage) evc.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading page " + ex.getMessage());
        }
    });

        home.setOnAction(e -> {
        try {
            Parent afficherShop = FXMLLoader.load(getClass().getResource("homeF.fxml"));
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
        Parent afficherShop = FXMLLoader.load(getClass().getResource("FXMLfront.fxml"));
        Scene scene = new Scene(afficherShop);
        Stage stage = (Stage) home.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.err.println("Error loading page " + ex.getMessage());
    }
    });
        front2.setOnAction(e -> {
        try {
             Parent root = FXMLLoader.load(getClass().getResource("FXMLpfront.fxml"));
             Scene scene = new Scene(root);
             Stage stage = (Stage) home.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading page " + ex.getMessage());
        }
    });

    
    
    try {
        // TODO
        EventService es = new EventService();
        ObservableList<Event> events = FXCollections.observableArrayList(es.afficher());
        afficheE.setItems(events);
        displayEvents(); // call the displayEvents method here
        System.out.println("afficheE: " + afficheE);
    } catch (Exception e) {
        e.printStackTrace();
    }  

}

        
public void displayEvents() throws SQLException {
    EventService es = new EventService();
    List<Event> eventList = es.afficher();
    LocalDate currentDate = LocalDate.now();
    for (Event e : eventList) {
        if (e.getDate().equals(currentDate)) {
            eventNameLabel.setText(e.getNom());
            break;
        }
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

    
@FXML
void Supprimer_Event(ActionEvent event) throws SQLException {
    // get the selected category
    Event selectedid = afficheE.getSelectionModel().getSelectedItem();

    // delete the selected category from the database
    EventService eventService = new EventService();
    eventService.supprimer(selectedid);

    // remove the selected category from the list view
    afficheE.getItems().remove(selectedid);
} 



@FXML
private void handleSortButtonAction(ActionEvent event) {
    ObservableList<Event> events = afficheE.getItems();
    events.sort(Comparator.comparing(Event::getDate));
    afficheE.setItems(events);
}



@FXML
private void exportToPDF(ActionEvent event) throws SQLException, DocumentException, FileNotFoundException {
    Connection connection = MaConnexion.getInstance().getCnx();
    PdfExporter.exportEventsToPDF(connection);
}


/*
 @FXML
void reduceSpotCount(ActionEvent event) throws SQLException {
    // Get the selected event from the table view
    Event selectedEvent = afficheE.getSelectionModel().getSelectedItem();

    // Subtract 1 from the event's spot count
    int newSpotCount = selectedEvent.getSpot() - 1;

    // Update the event in the database
  //  EventService eventService = new EventService();
   // eventService.updateSpotCount(selectedEvent.getId(), newSpotCount);

    // Update the event in the table view
    selectedEvent.setSpot(newSpotCount);
    afficheE.refresh();
}*/


@FXML
void handleParticipateBtnClick(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Participant.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
}




}