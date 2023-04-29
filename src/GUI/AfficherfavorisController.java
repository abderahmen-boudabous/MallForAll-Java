/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;



import Entity.Favori;
import Services.FavoriService;
import java.net.URL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Bettaibi Walid
 */
public class AfficherfavorisController implements Initializable {

    @FXML
    private ListView<?> lview;
        FavoriService ps = new FavoriService();

    @FXML
    private TextField find;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Associer la liste observable au ListView
        List<Favori> a = new ArrayList();
        try {
            a = ps.afficher();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherfavorisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    

    }

    @FXML
    private void ajout(ActionEvent event) {
                               try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ajoutfav.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
           // stage.close();
//             stage.getScene().getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
       //      stage.getScene().getRoot().setId("primary");
             
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modif(ActionEvent event) {
                               try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/modiffav.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
           // stage.close();
             // Ajouter un style personnalisé pour le bouton
//            stage.getScene().getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
         //   stage.getScene().getRoot().setId("primary");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supp(ActionEvent event) throws SQLException {
                if(lview.getSelectionModel().getSelectedItem()!=null){
            Favori avis = (Favori) lview.getSelectionModel().getSelectedItem();
            ps.supprimer(avis.getId());
           // b = avis.getId_Reclamation();
           // info.setText("Reclamation supprimée");
            // Ajouter un style personnalisé pour le bouton
//            lview.getScene().getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        lview.getScene().getRoot().setId("primary");
             lview.getItems().clear();
             System.out.println("supp");
             lview.getItems().addAll();
             
        }else{
           
        }
    }
}

    
 
    
    

