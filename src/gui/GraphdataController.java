package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.Product;
import tn.esprit.tools.MaConnexion;

public class GraphdataController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private BarChart<String, Integer> BarChart;
    
    private Connection cnx = MaConnexion.getInstance().getCnx();
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private TextField tete;

    public void chart() {
        String chartSql = "SELECT name, stock FROM product";


        try {
            prepare = cnx.prepareStatement(chartSql);
            result = prepare.executeQuery();

            XYChart.Series<String, Integer> chartData = new XYChart.Series<>();
            while (result.next()) {
                String name = result.getString("name");
                int stock = result.getInt("stock");
                chartData.getData().add(new XYChart.Data<>(name, stock));
            }

            BarChart.getData().add(chartData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chart();
    }

    @FXML
    private void Back(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLp.fxml"));
                        try {
                            Parent root = loader.load();
                            tete.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }
}
