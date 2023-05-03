/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
public class UpdateproductController implements Initializable {

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
    
    private Product product;

    
    ShopService shopService = new ShopService();
    ProductService productService = new ProductService();
    @FXML
    private Button home;
    private Button sshop;
    private Button pproducts;
    @FXML
    private Button evc;
    @FXML
    private Button ev;
    @FXML
    private Button front1;
    @FXML
    private Button front2;
    @FXML
    private Label eventNameLabel;
        
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
        evc.setOnAction(e -> {
        try {
            Parent afficherShop = FXMLLoader.load(getClass().getResource("ListEvent.fxml"));
            Scene scene = new Scene(afficherShop);
            Stage stage = (Stage) evc.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading affichershop.fxml: " + ex.getMessage());
        }
    });
        ev.setOnAction(e -> {
        try {
            Parent afficherShop = FXMLLoader.load(getClass().getResource("ListCategory.fxml"));
            Scene scene = new Scene(afficherShop);
            Stage stage = (Stage) ev.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading affichershop.fxml: " + ex.getMessage());
        }
    });
        front1.setOnAction(e -> {
        try {
            Parent afficherShop = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Scene scene = new Scene(afficherShop);
            Stage stage = (Stage) front1.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading affichershop.fxml: " + ex.getMessage());
        }
    });
    front2.setOnAction(e -> {
        try {
            Parent afficherShop = FXMLLoader.load(getClass().getResource("FXMLp.fxml"));
            Scene scene = new Scene(afficherShop);
            Stage stage = (Stage) front2.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading affichershop.fxml: " + ex.getMessage());
        }
    });
    home.setOnAction(e -> {
        try {
            Parent afficherShop = FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene scene = new Scene(afficherShop);
            Stage stage = (Stage) home.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading affichershop.fxml: " + ex.getMessage());
        }
    });
    }    

    @FXML
    private void updateproduct(ActionEvent event) {
        
        System.out.println(product);
        product.setName(tfname.getText());
        product.setPrice(Float.parseFloat(tfprice.getText()));
        product.setStock(Integer.parseInt(tfstock.getText()));
        product.setType(tftype.getText());
        product.setShop(cbshop.getValue());
        System.out.println(product);
        
           ProductService productService = new ProductService();
            productService.update(product); 
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("product Modifier");
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

    public void setTfname(String tfname) {
         this.tfname.setText(tfname);
    }

    public void setCbshop(Shop shop) {
        cbshop.setValue(shop);
    }

    public void setTfprice(float tfprice) {
        this.tfprice.setText(String.valueOf(tfprice));
    }

    public void setTftype(String tftype) {
        this.tftype.setText(tftype);
    }

    public void setTfstock(Integer tfstock) {
       this.tfstock.setText(String.valueOf(tfstock));
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @FXML
    private void retourproduct(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLp.fxml"));
                        try {
                            Parent root = loader.load();
                            tfname.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }
    
}
