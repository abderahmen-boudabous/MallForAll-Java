package gui;





import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entities.Participant;
import tn.esprit.services.ParticipantService;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import tn.esprit.entities.Event;
import java.util.List;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Pair;
import tn.esprit.entities.Shop;
import tn.esprit.services.EventService;
import tn.esprit.services.ShopService;
import tn.esprit.tools.MaConnexion;






public class ParticipantController implements Initializable {

    @FXML
    private ComboBox<Event> eventComboBox;

    @FXML
    private TextField sellerNameField;

    @FXML
    private Button okButton;
    
    
    @FXML
    private BarChart<?, ?> barChart;
    
    @FXML
    private Button chartButton;
    
    @FXML
    private TableView<Pair<String, String>> participantsTable;

    @FXML
    private TableColumn<Pair<String, String>, String> sellerNameColumn;

    @FXML
    private TableColumn<Pair<String, String>, String> eventNameColumn;

    private ParticipantService participantService;

    

    
    private Connection connect; 
    private PreparedStatement prepare;  
    private ResultSet result;
    
    Connection cnx;
    String sql="";

    public ParticipantController() throws SQLException {
        cnx=MaConnexion.getInstance().getCnx();
        
        this.participantService = new ParticipantService();
    } 
    
    
 /*  
public void chart() throws SQLException{
    // Retrieving data from database
    String chartSql = "SELECT event_id, COUNT(DISTINCT seller_name) AS seller_count FROM participants GROUP BY event_id ORDER BY event_id ASC";
    prepare = cnx.prepareStatement(chartSql);
    result = prepare.executeQuery();

    // Populating chart data
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    while (result.next()) {
        int eventId = result.getInt("event_id");
        int sellerCount = result.getInt("seller_count");
        series.getData().add(new XYChart.Data<>(String.valueOf(eventId), sellerCount));
    }

    // Setting chart properties
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Number of Sellers");
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("Event ID");
    BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
    chart.getData().add(series);

    // Displaying chart in a new window
    Stage stage = new Stage();
    Scene scene = new Scene(chart, 800, 600);
    stage.setScene(scene);
    stage.show();
}  */
    
    public void chart() throws SQLException {
    // Retrieving data from database
    String chartSql = "SELECT e.nom AS event_name, COUNT(DISTINCT p.seller_name) AS seller_count FROM participants p JOIN event e ON p.event_id = e.id GROUP BY event_name ORDER BY event_name ASC";
    prepare = cnx.prepareStatement(chartSql);
    result = prepare.executeQuery();

    // Populating chart data
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    while (result.next()) {
        String eventName = result.getString("event_name");
        int sellerCount = result.getInt("seller_count");
        series.getData().add(new XYChart.Data<>(eventName, sellerCount));
    }

    // Setting chart properties
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Number of Sellers");
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("Event Name");
    BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
    chart.getData().add(series);

    // Displaying chart in a new window
    Stage stage = new Stage();
    Scene scene = new Scene(chart, 800, 600);
    stage.setScene(scene);
    stage.show();
}

    




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ParticipantService participantService = null;
        try {
            participantService = new ParticipantService();
            List<Event> eventList = participantService.getAllEventNames();
            eventComboBox.getItems().addAll(eventList);
        } catch (SQLException e) {
            System.out.println("Error while loading events: " + e.getMessage());
        }
        
       
        
    
        
        
    }


    

    @FXML
    void handleChartButton(ActionEvent event) throws SQLException {
        chart();
    }
    
    /*
   @FXML
    public void addParticipant() throws SQLException {
        // Get the selected event from the combo box
        Event event = eventComboBox.getValue();

        // Get the seller name from the text field
        String sellerName = sellerNameField.getText();

        // Create a Participant object with the selected event and seller name
        Participant participant = new Participant(event, sellerName);

        // Add the participant to the database
        ParticipantService participantService = new ParticipantService();
        //participantService.addParticipantToEvent(participant);
        participantService.addParticipantToEvent("name", participant);

        // Show a confirmation message
        String message = "You have participated in " + event.getNom() + ". You will be notified when the event is live . " ;
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Participation Confirmed");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

        // Close the window
        Stage stage = (Stage) eventComboBox.getScene().getWindow();
        stage.close();
    } 
 */
    
    
    @FXML
public void addParticipant() throws SQLException {
    // Get the selected event from the combo box
    Event event = eventComboBox.getValue();

    // Get the seller name from the text field
    String sellerName = sellerNameField.getText();

    // Check if the seller name exists in the Shop table
    boolean sellerExists = false;
    ShopService shopService = new ShopService();
    List<Shop> shops = shopService.findByName(sellerName);
    if (!shops.isEmpty()) {
        sellerExists = true;
    }

    if (sellerExists) {
        // Create a Participant object with the selected event and seller name
        Participant participant = new Participant(event, sellerName);

        // Add the participant to the database
        ParticipantService participantService = new ParticipantService();
        participantService.addParticipantToEvent(participant);

        // Show a confirmation message
        String message = "You have participated in " + event.getNom() + ". You will be notified when the event is live.";
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Participation Confirmed");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

        // Close the window
        Stage stage = (Stage) eventComboBox.getScene().getWindow();
        stage.close();
    } else {
        // Show an error message
        String message = "The Shop name entered does not exist .";
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

    
    


    

    
    
    
}
