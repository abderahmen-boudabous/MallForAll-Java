/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import tn.esprit.entities.Product;
import tn.esprit.entities.Shop;
import tn.esprit.services.ProductService;
import tn.esprit.services.ShopService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AffichherproductController implements Initializable {

    @FXML
    private TableColumn<Product, String> id;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, String> type;
    @FXML
    private TableColumn<Product, String> stock;
    @FXML
    private TableColumn<Product, String> price;
    @FXML
    private TableColumn<Product, String> shop;
    
    @FXML
    private TableView<Product> productView;

    ProductService pp = new ProductService();

    Product product = null;
    ObservableList<Product> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Product, String> update;
    @FXML
    private TableColumn<Product, String> deletep;
    @FXML
    private TextField tete;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load();
        
    }    
    public void load() {
        refreshTable();

        // Sets the cell value factories for each column in the table
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        shop.setCellValueFactory(cellData -> {
            ObjectProperty<String> nameProperty = new SimpleObjectProperty<>();
            Shop shop = cellData.getValue().getShop();
            if (shop != null) {
                nameProperty.set(shop.getName());
            }
            return nameProperty;
        });
        
        
        Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFactory = (TableColumn<Product, String> param) -> {
    // make cell containing buttons
    final TableCell<Product, String> cell = new TableCell<Product, String>() {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
                setText(null);
            } else {
                Button deleteButton = new Button("Delete");
                deleteButton.setStyle("-fx-background-color: #ff1744; -fx-text-fill: white; -fx-cursor: hand;");
                deleteButton.setOnAction((ActionEvent event) -> {
                    // Deletes the selected product and refreshes the table
                    product = productView.getSelectionModel().getSelectedItem();
                    System.out.println(product);
                    pp.supprimer(product);
                    refreshTable();
                });
                HBox manageBtn = new HBox(deleteButton);
                manageBtn.setStyle("-fx-alignment:center");
  
                setGraphic(manageBtn);
                setText(null);
            }
        }
        
    };
    return cell;
};

        
        deletep.setCellFactory(cellFactory);
        
        
        
        Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFactory2 = (TableColumn<Product, String> param) -> {
    // make cell containing buttons
    final TableCell<Product, String> cell = new TableCell<Product, String>() {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
                setText(null);
            } else {
                Button updateButton = new Button("update");
               updateButton.setStyle("-fx-background-color: #2196f3; -fx-text-fill: white; -fx-cursor: hand;");

                updateButton.setOnAction((ActionEvent event) -> {
                    // Deletes the selected product and refreshes the table
                    product = productView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateproduct.fxml"));

                            try {
                                Parent root = loader.load();

                                UpdateproductController controller = loader.getController();
                                Shop shop = product.getShop();
                                
                                controller.setTfname(product.getName());
                                controller.setTftype(product.getType());
                                controller.setTfstock(product.getStock());
                                controller.setTfprice(product.getPrice());
                                controller.setProduct(product);
                                controller.setCbshop(shop);
                                productView.getScene().setRoot(root);
                                System.out.println(shop);
                            } catch (IOException ex) {

                                System.out.println(ex.getMessage());
                            }
                });
                HBox manageBtn = new HBox(updateButton);
                manageBtn.setStyle("-fx-alignment:center");
  
                setGraphic(manageBtn);
                setText(null);
            }
        }
        
    };
    return cell;
};

        
        update.setCellFactory(cellFactory2);
        productView.setItems(data);
}
     
     
     
    
    private void refreshTable() {

        data.clear();

        List<Product> products = pp.afficherr();
        data.addAll(products);
        productView.setItems(data);

    }

    @FXML
    private void addp(ActionEvent event) {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajouterproduct.fxml"));
                        try {
                            Parent root = loader.load();
                            tete.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }
    
    
}
