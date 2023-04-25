/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import tn.esprit.entities.Product;
import tn.esprit.entities.Shop;
import tn.esprit.services.ProductService;
import tn.esprit.services.ShopService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterproductController implements Initializable {

    @FXML
    private TextField tfname;
    @FXML
    private ChoiceBox<Shop> cbshop;
    @FXML
    private TextField tfprice;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfstock;

    ShopService shopService = new ShopService();
    ProductService productService = new ProductService();
    @FXML
    private Button sshop;
    @FXML
    private Button pproducts;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Shop> shops = shopService.afficher();
        cbshop.setItems(FXCollections.observableArrayList(shops));
        cbshop.setConverter(new StringConverter<Shop>() {
            
            @Override
            public String toString(Shop shop) {
                return shop == null ? "" : shop.getName();
            }

            @Override
            public Shop fromString(String string) {
                return null;
            }
        });
    }    

    @FXML
    private void ajouterproduct(ActionEvent event) {
        if (tfname.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Le nom est vide !");
    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
    alert.getButtonTypes().setAll(okButton);
    alert.showAndWait(); 
} else if (tfprice.getText().isEmpty() || !tfprice.getText().matches("\\d+(\\.\\d+)?")) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Le prix doit être un nombre !");
    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
    alert.getButtonTypes().setAll(okButton);
    alert.showAndWait(); 
} else if (tftype.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Le nom est vide !");
    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
    alert.getButtonTypes().setAll(okButton);
    alert.showAndWait(); 
} else if (tfstock.getText().isEmpty() || !tfstock.getText().matches("\\d+")) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Le stock doit être un nombre entier !");
    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
    alert.getButtonTypes().setAll(okButton);
    alert.showAndWait(); 
} else {
    Shop shop = cbshop.getValue(); 
    float price = Float.parseFloat(tfprice.getText());
    Product product = new Product (tfname.getText(),price,tftype.getText(),Integer.parseInt(tfstock.getText()),shop);
    ProductService productService = new ProductService();
    productService.ajouter(product); 

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText("product ajoutée");
    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
    alert.getButtonTypes().setAll(okButton);
    Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
    okBtn.setOnAction(evt -> {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLp.fxml"));
        try {
            Parent root = loader.load();
            tfname.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    });
    alert.showAndWait();
}

        
        
        
    }

    @FXML
    private void returnproduct(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLp.fxml"));
                        try {
                            Parent root = loader.load();
                            tfname.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }
    
}
