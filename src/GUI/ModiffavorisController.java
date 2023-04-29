/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Favori;
import Services.FavoriService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Bettaibi Walid
 */
public class ModiffavorisController implements Initializable {

    @FXML
    private TextField nomTF;
    @FXML
    private TextField prod;
    @FXML
    private TextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterProduit(ActionEvent event) {
         if ( id.getText().isEmpty()  || prod.getText().isEmpty() )
        {

			Alert al = new Alert(Alert.AlertType.INFORMATION);
			al.setTitle("Controle de saisie");
			al.setHeaderText("Erreur de saisie !");
			al.setContentText("Les données sont vides !");
			al.show();
        }               FavoriService ps = new FavoriService();
        Favori p = new Favori();
        p.setProduct_id(Integer.valueOf(prod.getText()));
        ps.modifier(p,Integer.valueOf(id.getText()));
    }
    }
    

