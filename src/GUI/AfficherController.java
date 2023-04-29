/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Commande;
import Services.CommandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Bettaibi Walid
 */
public class AfficherController implements Initializable {

    @FXML
    private ListView<Commande> lview;

    CommandeService ps = new CommandeService();
    @FXML
    private TextField find;
    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        // Associer la liste observable au ListView
        List<Commande> a = new ArrayList();
        try {
            a = ps.afficher();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lview.getItems().addAll(a);
        lview.setCellFactory(lv -> new ListCell<Commande>() {
    
            @Override
            public void updateItem(Commande avis, boolean empty) {
        super.updateItem(avis, empty) ;
        setText(empty ? null : "Nom :" +avis.getNom()+" Adresse :"+avis.getAdresse()+" Quantite :"+avis.getQuantite()+" Tel :" +avis.getTelephone()+" Produit :"+avis.getProduct_id());
    
    }
    });  
    }

    @FXML
    private void ajout(ActionEvent event) {
                       try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ajoutcommande.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/modifcommande.fxml"));
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
            Commande avis = lview.getSelectionModel().getSelectedItem();
            ps.supprimer(avis.getNom());
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

    @FXML
    private void chercher(ActionEvent event) {
        if (find.getText().isEmpty())
        {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
			al.setTitle("Controle de saisie");
			al.setHeaderText("Erreur de saisie !");
			al.setContentText("Les données sont vides !");
			al.show();
        }
               ps.RechercherCommande(find.getText());
                      List<Commande> a = new ArrayList();

               a =  ps.filtrerListe(lview.getItems(), find.getText());
               lview.getItems().clear();
              lview.getItems().addAll(a);
                
    }
    
    
}
