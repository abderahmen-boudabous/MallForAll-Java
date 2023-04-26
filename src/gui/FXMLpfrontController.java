/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLpfrontController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private Button sshop;
    @FXML
    private Button pproducts;
    @FXML
    private TextField tete;
    @FXML
    private TableView<?> productView;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> stock;
    @FXML
    private TableColumn<?, ?> price;
    @FXML
    private TableColumn<?, ?> shop;
    @FXML
    private TableColumn<?, ?> photo;
    @FXML
    private TextField searchfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addp(ActionEvent event) {
    }

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void X(ActionEvent event) {
    }
    
}
