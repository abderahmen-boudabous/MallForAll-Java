package gui;

import java.awt.Color;
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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import tn.esprit.entities.Product;
import tn.esprit.entities.Shop;
import tn.esprit.tools.MaConnexion;

public class GraphdataSController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private BarChart<String, Integer> BarChart;
    
    private Connection cnx = MaConnexion.getInstance().getCnx();
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private TextField tete;
    
    Shop shop = new Shop(); // or retrieve an existing shop object from your data source


    
    public void chart() {
    String DB_URL = MaConnexion.getInstance().getUrl();
    String DB_USERNAME = MaConnexion.getInstance().getUser();
    String DB_PASSWORD = MaConnexion.getInstance().getPwd();

    String chartSql =   "SELECT name, SUM(`like`) AS likes, SUM(`dislike`) AS dislikes\n" +
                        "FROM shop\n" +
                        "GROUP BY name;";


    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(chartSql);
         ResultSet rs = stmt.executeQuery()) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        while (rs.next()) {
            String name = rs.getString("name");
            int likes = rs.getInt("likes");
            int dislikes = rs.getInt("dislikes");

            dataset.addValue(likes, "Like", name);
            dataset.addValue(dislikes, "Dislike", name);
        }

        JFreeChart chart = ChartFactory.createBarChart("Likes and Dislikes", "Shop", "Count", dataset, PlotOrientation.VERTICAL, true, true, false);
        
        // Set the paint for the "Like" and "Dislike" series
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesPaint(0, Color.BLUE); // Like
        plot.getRenderer().setSeriesPaint(1, Color.RED);  // Dislike
        plot.setRangeGridlinePaint(Color.BLACK);

        ChartFrame frame = new ChartFrame("Shop Ratings", chart);
        frame.setVisible(true);
        frame.setSize(800, 500);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
                        try {
                            Parent root = loader.load();
                            tete.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }
}
