/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author user
 */
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
        // TODO
    }    
    
}
