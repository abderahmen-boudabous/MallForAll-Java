/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Avis;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import  tn.esprit.services.ServiceAvis;
import org.controlsfx.control.Rating;
import  tn.esprit.tests.Mainr;

/**
 * FXML Controller class
 *
 * @author arafet
 */
public class AjouterAvisController implements Initializable {

    @FXML
    private TextArea texte_avis;
    @FXML
    private Button AjouterReclamationBtn;
    @FXML
    private Rating rating;
    @FXML
    private Label eventNameLabel;
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
@FXML
    private void AjouterAvis(ActionEvent event) {
        if(texte_avis.getText().isEmpty()){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("remplir tous les champs");
            alert.showAndWait();
        }else{
            try {
            Avis t  = new Avis((texte_avis.getText()), (float) rating.getRating(),Mainr.getId_user());
            ServiceAvis ps = new ServiceAvis();
            ps.ajouter(t);
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("informatin Message");
            alert.setHeaderText(null);
            alert.setContentText("ajouter avec success");
            alert.showAndWait();
            System.out.println(ps.recuperer());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    System.out.println(rating.getRating());
    }
   

} 