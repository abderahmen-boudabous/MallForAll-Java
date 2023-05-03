package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;
import tn.esprit.entities.Participant;
import tn.esprit.services.ParticipantService;






public class ParticipantsTableController implements Initializable {

    @FXML
    private TableView<Participant> participantTable;

    @FXML
    private TableColumn<Participant, String> sellerNameColumn;

    @FXML
    private TableColumn<Participant, Integer> eventIdColumn;

    private ParticipantService participantService;

    public ParticipantsTableController() {
        try {
            participantService = new ParticipantService();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        sellerNameColumn.setCellValueFactory(new PropertyValueFactory<>("sellerName"));
        eventIdColumn.setCellValueFactory(new PropertyValueFactory<>("event"));
        List<Participant> participants = participantService.getAllParticipants();
        ObservableList<Participant> participantList = FXCollections.observableArrayList(participants);
        participantTable.setItems(participantList);
    }
}
