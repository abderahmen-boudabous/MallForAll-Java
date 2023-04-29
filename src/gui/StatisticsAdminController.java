/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Reclamations;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author Theto
 */
public class StatisticsAdminController implements Initializable {

    ServiceReclamation rs = new ServiceReclamation();
  
    @FXML
    private BarChart<String, Number> StatsChart;
    @FXML
    
    private NumberAxis numberAxis;
    @FXML
    private CategoryAxis categoryAxis;
    @FXML
    private ImageView GoBackBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayStatistics();
    }    
    


    public void displayStatistics() {
    try {
        // Récupérer toutes les réclamations de la base de données
        List<Reclamations> reclamations = rs.recuperer();

        // Regrouper les réclamations par type
        Map<String, Long> reclamationsParType_reclamation = reclamations.stream()
                .collect(Collectors.groupingBy(Reclamations::getType_reclamation, Collectors.counting()));

        // conversion reclamationsParType map a une list de objects PieChart.Data
        List<XYChart.Series<String, Number>> barChartData = new ArrayList<>();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Nombre de réclamations");

        // Ajouter les éléments de données au BarChart
        for (Map.Entry<String, Long> entry : reclamationsParType_reclamation.entrySet()) {
            String type = entry.getKey();
            Long count = entry.getValue();
            series.getData().add(new XYChart.Data<>(type, count));
        }
        barChartData.add(series);

        // Définissez les éléments de données dans le BarChart
        StatsChart.setData(FXCollections.observableArrayList(barChartData));

        // Configurer l'animation pour les données barchart
        StatsChart.getData().forEach(data ->
                data.getData().forEach(item -> {
                    item.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                        ScaleTransition st = new ScaleTransition(Duration.millis(200), item.getNode());
                        st.setToX(1.1);
                        st.setToY(1.1);
                        st.play();
                    });

                    item.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                        ScaleTransition st = new ScaleTransition(Duration.millis(200), item.getNode());
                        st.setToX(1.0);
                        st.setToY(1.0);
                        st.play();
                    });

                    item.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        // Obtenir la valeur actuelle de l'élément data
                        Number currentValue = item.getYValue();

                        // Afficher un message avec la valeur de data
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Statistique Reclamation(s)");
                        alert.setHeaderText(item.getXValue());
                        alert.setContentText("Nombre de réclamations : " + currentValue);
                        alert.showAndWait();
                    });
                }));

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
      @FXML
    private void GoBk(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
            Parent root = loader.load();

            // Set the root of the current scene to the new FXML file
            GoBackBtn.getScene().setRoot(root);

}
    }