package gui;

import entities.Notification;
import entities.Reclamations;
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
import services.ServiceReclamation;

public class NotificationController implements Initializable {

    @FXML
    private ListView<Notification> notificationList;
    
    ServiceReclamation sr=new ServiceReclamation();

    private ObservableList<Notification> notifications = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Add some sample notifications
            List<Reclamations> rec= sr.recuperer();
            for(Reclamations r: rec){
                notifications.add(new Notification(r.getType_reclamation(),r.getText_reclamation(), r.getDate_reclamation()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set up the ListView
        notificationList.setItems(notifications);
        notificationList.setCellFactory(param -> new NotificationCell());
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
