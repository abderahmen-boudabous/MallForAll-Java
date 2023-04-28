/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.AnchorPane;
import tn.esprit.tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GraphdataController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private BarChart<?, ?> BarChart;
    
    private Connection connect;
    private Connection cnx=MaConnexion.getInstance().getCnx();
    private PreparedStatement prepare;
    private ResultSet result;
    

    /**
     * Initializes the controller class.
     */
    
    public Connection connectDB() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mffa", "root", "");
    System.out.println("Connection established!!");
    return con;
}

    
    
    public void chart() throws ClassNotFoundException, SQLException{
        String chartSql ="SELECT date, SUM(total) FROM income GROUP BY date ASC LIMIT 8 ";
        
        connect =connectDB();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
