/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Commande;
import Services.CommandeService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Bettaibi Walid
 */
public class AjoutcommandeController implements Initializable {

    @FXML
    private TextField nomTF;
    @FXML
    private TextField genreTF;
    @FXML
    private TextField prixTF;
    @FXML
    private TextField tel;
    @FXML
    private TextField prod;
    @FXML
    private TextField id;
    @FXML
    private Rating notetf;

    /**
     * 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterProduit(ActionEvent event) throws SQLException {
        
        if ( nomTF.getText().isEmpty() || genreTF.getText().isEmpty() || prixTF.getText().isEmpty() || tel.getText().isEmpty() || prod.getText().isEmpty() )
        {

			Alert al = new Alert(Alert.AlertType.INFORMATION);
			al.setTitle("Controle de saisie");
			al.setHeaderText("Erreur de saisie !");
			al.setContentText("Les données sont vides !");
			al.show();
        }
         CommandeService ps = new CommandeService();
        Commande p = new Commande();
        p.setNom(nomTF.getText());
        p.setAdresse(genreTF.getText());
        p.setQuantite(Integer.valueOf(prixTF.getText()));
        p.setTelephone(Integer.valueOf(tel.getText()));
        p.setProduct_id(Integer.valueOf(prod.getText()));
        p.setNote((int) notetf.getRating()); 
        ps.ajouter(p);
        
    }

    
}
