package gui;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import tn.esprit.entities.Rating;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import tn.esprit.services.RatingService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import tn.esprit.entities.Event;
import tn.esprit.entities.Rating;
import tn.esprit.services.RatingService;


public class RatingController implements Initializable{
    
    @FXML
    private ComboBox<Event> eventComboBox;
    
    @FXML
    private Slider ratingSlider;
    
    @FXML
    private Label averageRatingLabel;
    
  

@Override
    public void initialize(URL url, ResourceBundle rb) {
    RatingService ratingService;
    List<Event> eventNames;
        try {
            ratingService = new RatingService();
            eventNames = ratingService.getAllEventNames();
   
    
     eventComboBox.getItems().addAll(eventNames);

    // Set the default value of the eventComboBox to the first event in the list
    if (!eventComboBox.getItems().isEmpty()) {
        eventComboBox.getSelectionModel().selectFirst();
    }
    
    // Set the ratingSlider to snap to integer values only
    ratingSlider.setSnapToTicks(true);
    ratingSlider.setMajorTickUnit(1);
    ratingSlider.setMinorTickCount(0);
    ratingSlider.setShowTickMarks(true);
    ratingSlider.setShowTickLabels(true);
    
    // Set the default value of the ratingSlider to 1
    ratingSlider.setValue(1);
        } catch (SQLException ex) {
            Logger.getLogger(RatingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    
   }
    
    
    @FXML
private void rateEvent() throws SQLException {
    // Get the selected event from the combo box
    Event event = eventComboBox.getValue();

    // Get the selected rating from the slider
    int ratingValue = (int) ratingSlider.getValue();

    // Create a Rating object with the selected event and rating value
    Rating rating = new Rating(event, ratingValue);
   
    // Add the rating to the database
    RatingService ratingService = new RatingService();
    ratingService.addRating(rating);

    // Calculate and display the average rating for the event
    calculateAverageRating(event);
}
    
    private void calculateAverageRating(Event event) throws SQLException {
        // Get the average rating for the event from the database
         RatingService ratingService;
        ratingService = new RatingService();
        double averageRating = ratingService.getAverageRating(event);
        
        // Display the average rating on the label
        averageRatingLabel.setText(String.format("%.1f", averageRating));
    }

}
