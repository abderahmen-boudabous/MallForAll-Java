/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.entities.CategorieF;
import tn.esprit.entities.Fournisseur;
import tn.esprit.services.CategorieService;
import tn.esprit.services.FournisseurService;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class AjouterFournisseurController implements Initializable {

    @FXML
    private AnchorPane btnAjouter;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfTel;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfSiteWeb;
    @FXML
    private TextField tfImage;
    @FXML
    private ChoiceBox<String> boxCategorie;
    @FXML
    private Button AjouterF;
    @FXML
    private Button btCategorie;
    @FXML
    private Button btFournisseur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> testList = new ArrayList<String>();
        CategorieService cs = new CategorieService();
        for (CategorieF c : cs.afficher()) {
            testList.add(c.getLibelle());
        }
        boxCategorie.getItems().addAll(testList);
    }

    @FXML
    private void AjouterFournisseur(ActionEvent event) {
        String nom = tfNom.getText();
        int tel = Integer.parseInt(tfTel.getText());
        //int tel = 0;
        String address = tfAddress.getText();
        String email = tfEmail.getText();
        String website = tfSiteWeb.getText();
        String img = tfImage.getText();
        CategorieF category = new CategorieF(boxCategorie.getValue());
        if (tfNom.getText().length() == 0 || tfTel.getText().length() == 0 || tfEmail.getText().length() == 0 || tfAddress.getText().length() == 0 || tfSiteWeb.getText().length() == 0 || tfImage.getText().length() == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajouter Fournisseur");
            alert.setHeaderText(null);
            alert.setContentText("il faut remplire tous les informations");
            alert.showAndWait();

        } else {

            Fournisseur f = new Fournisseur(100, nom, tel, address, email, website, img, category);
            FournisseurService cf = new FournisseurService();
            cf.ajouter(f);
        }

    }

    @FXML
    private void RedirectionCategorie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCategorie.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void RedirectionFournisseur(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherFournisseur.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }
}
