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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjouterEventController implements Initializable {

    @FXML
    private TextField nom;

    @FXML
    private TextField spot;

    @FXML
    private TextArea duration;

    @FXML
    private DatePicker date;

    @FXML
    private TextField category;

    @FXML
    private ImageView imglogo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO
    }

    @FXML
    public void Ajouter_Event(ActionEvent event) {
        if (nom.getText().isEmpty() || spot.getText().isEmpty() || duration.getText().isEmpty() || date.getValue() == null || category.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.show();
        } else {
            try {
                Event E = new Event();
                E.setNom(nom.getText());
                E.setSpot(Integer.parseInt(spot.getText()));
                E.setDuration(duration.getText());
//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//String dateString = date.getValue().format(formatter);
//E.setDate(dateString);

//E.setCategory(category.getText());


                NewInterface eventService = new EventService();

                eventService.ajouter(E);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListEvent.fxml"));
                Parent view_2 = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(view_2);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void RetourE(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListEvent.fxml"));
            Parent view_2 = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(AjouterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
