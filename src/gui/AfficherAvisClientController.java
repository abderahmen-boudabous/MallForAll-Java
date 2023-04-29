/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Avis;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import services.ServiceAvis;


/**
 * FXML Controller class
 *
 * @author arafet
 */
public class AfficherAvisClientController implements Initializable {

    @FXML
    private HBox scrollAfficherAvis;
    
    private List<Avis> lAvis;
    ServiceAvis sa=new ServiceAvis();

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lAvis= sa.recuperer();
            for(int i=0;i<lAvis.size();i++){
                FXMLLoader fxmlLoader= new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cadre.fxml"));
                HBox cardBox = fxmlLoader.load();
                CadreController cadreController= fxmlLoader.getController();
                cadreController.setData(lAvis.get(i));
                scrollAfficherAvis.getChildren().add(cardBox);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherAvisClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherAvisClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
