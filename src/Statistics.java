import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import service.UserService;
import utis.Maconnexion;


public class Statistics implements Initializable {

    @FXML
    private TabPane admintabpane;
    @FXML
    private Tab home;
    @FXML
    private PieChart chart;
    @FXML
    private Label labelstat;
    @FXML
    private Tab flous;
    @FXML
    private Label labelstat2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Establish a connection to the database
        Connection conn = Maconnexion.getInstance().getCnx();

        // Call the countUsersByRole method to get the data
        Map<String, Integer> stats = countUsersByRole(conn);

        // Display the data on the pie chart
        showStats(stats);
    }

    /**
     * Returns a map of the number of users for each role
     */
    public Map<String, Integer> countUsersByRole(Connection conn) {
        Map<String, Integer> result = new HashMap<>();
        try {
            String requete = "SELECT roles, COUNT(*) AS count FROM user GROUP BY roles";
            PreparedStatement stmt = conn.prepareStatement(requete);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String role = rs.getString("roles");
                int count = rs.getInt("count");
                result.put(role, count);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Displays the data on the pie chart
     */
    public void showStats(Map<String, Integer> stats) {
        // Clear any previous data from the chart
        chart.getData().clear();

        // Add the data to the chart
        for (String role : stats.keySet()) {
            PieChart.Data data = new PieChart.Data(role, stats.get(role));
            chart.getData().add(data);
        }

        // Display the total number of users
        int total = stats.values().stream().mapToInt(Integer::intValue).sum();
        labelstat.setText("Total Users: " + total);
    }
}
