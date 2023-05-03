package gui;

import entities.Notification;
import entities.Rec;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import  tn.esprit.services.ServiceReclamation;

public class NotificationController implements Initializable {

    @FXML
    private ListView<Notification> notificationList;
    
    ServiceReclamation sr=new ServiceReclamation();

    private ObservableList<Notification> notifications = FXCollections.observableArrayList();
    @FXML
    private Button dismiss;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Add some sample notifications
            List<Rec> rec= sr.recuperer();
            for(Rec r: rec){
                notifications.add(new Notification(r.getSujet(),r.getContenu(), r.getDate()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set up the ListView
        notificationList.setItems(notifications);
        notificationList.setCellFactory(param -> new NotificationCell());
    }

  @FXML
private void dismissb(ActionEvent event) {
    // Get a reference to the stage
    Stage stage = (Stage) dismiss.getScene().getWindow();

    // Close the stage
    stage.close();
}


    // Inner class for displaying the notifications in the ListView
    private class NotificationCell extends javafx.scene.control.ListCell<Notification> {
        @Override
        protected void updateItem(Notification item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
            } else {
                setText(item.getTitle() + ": " + item.getMessage());
            }
        }
    }
}
