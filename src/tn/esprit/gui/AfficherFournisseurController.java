/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class AfficherFournisseurController implements Initializable {

    @FXML
    private TableView<Fournisseur> categoriesTable;
    @FXML
    private TableColumn<Fournisseur, Integer> idColumn;
    @FXML
    private TableColumn<Fournisseur, String> NomColumn;
    @FXML
    private TableColumn<Fournisseur, Integer> TelColumn;
    @FXML
    private TableColumn<Fournisseur, String> AddressColumn;
    @FXML
    private TableColumn<Fournisseur, String> EmailColumn;
    @FXML
    private TableColumn<Fournisseur, String> CategorieColumn;
    @FXML
    private TableColumn<Fournisseur, String> SiteWebColumn;
    @FXML
    private TableColumn<Fournisseur, String>  ImgColumn;
    @FXML
    private TableColumn<Fournisseur, Void> actionColumn;
    @FXML
    private Button btAjoutF;
    @FXML
    private Button btCategorie;
    @FXML
    private Button btFournisseur;
    @FXML
    private Button btModifier;
    @FXML
    private AnchorPane rootPane;
    static Integer id;
    static String nom;
    static Integer tel;
    static String email;
    static String address;
    static String siteweb;
    static CategorieF categorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FournisseurService cs = new FournisseurService();
        ArrayList<Fournisseur> f = (ArrayList<Fournisseur>) cs.afficher();
        // Récupérer depuis une source de données
        ObservableList<Fournisseur> fournisseurs = FXCollections.observableArrayList(f);

        // Ajouter  à la table
        categoriesTable.setItems(fournisseurs);

        // Configurer les colonnes de la table
        // idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        TelColumn.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        CategorieColumn.setCellValueFactory(cellData -> {
            ObjectProperty<String> nameProperty = new SimpleObjectProperty<>();
            CategorieF c = cellData.getValue().getCategorieId();
            if (c != null) {
                nameProperty.set(c.getLibelle());
            }
            return nameProperty;
        });
          SiteWebColumn.setCellValueFactory(new PropertyValueFactory<>("website"));
        ImgColumn.setCellValueFactory(new PropertyValueFactory<>("img"));

        actionColumn.setCellFactory(param -> new TableCell<Fournisseur, Void>() {
            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setOnAction(event -> {
                    Fournisseur f = (Fournisseur) getTableRow().getItem();
                    // Supprimer de la base de données

                    cs.supprimer(f);
                    // Supprimer de la table
                    getTableView().getItems().remove(f);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }

        });
    }

    @FXML
    private void RedirectionAjouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AJouterFournisseur.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        TableView<Fournisseur> t = categoriesTable;
        Fournisseur f = t.getSelectionModel().getSelectedItem(); // use getSelectedItem() to get the selected item, not getSelectedItems()
        id = f.getId();
        nom = f.getNom();
        tel = f.getTel();
        email = f.getEmail();
        address = f.getAddress();
        siteweb = f.getWebsite();
        categorie = f.getCategorieId();

     
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateFournisseur.fxml")); 
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
