package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class EventController implements Initializable {

    @FXML
    private GridPane gridPane;

    private Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mall", "root", "");

            // Retrieve events from the database
            String query = "SELECT * FROM event";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Add events to the GridPane
            int row = 0;
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String spot = resultSet.getString("spot");
                int duration = resultSet.getInt("duration");
               // String dateString = resultSet.getString("date");
              //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
              //  String formattedDate = formatter.format(java.time.LocalDateTime.parse(dateString, formatter));

                Label nomLabel = new Label(nom);
                Label spotLabel = new Label(spot);
                Label durationLabel = new Label(Integer.toString(duration));
            //    Label dateLabel = new Label(formattedDate);

                // Add labels to the GridPane
                gridPane.add(nomLabel, 0, row);
                gridPane.add(spotLabel, 1, row);
                gridPane.add(durationLabel, 2, row);
            //   gridPane.add(dateLabel, 3, row);

                // Add some padding to the labels
                GridPane.setMargin(nomLabel, new Insets(10));
                GridPane.setMargin(spotLabel, new Insets(10));
                GridPane.setMargin(durationLabel, new Insets(10));
            //    GridPane.setMargin(dateLabel, new Insets(10));

                row++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
