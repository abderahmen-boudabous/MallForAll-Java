/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale.Category;
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
import javafx.scene.Scene;
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
public class AffichershopController implements Initializable {

    @FXML
    private TableColumn<Shop , String > id;
    @FXML
    private TableColumn<Shop , String> user;
    @FXML
    private TableColumn<Shop , String> description;
    @FXML
    private TableColumn<Shop , String> email;
    @FXML
    private TableColumn<Shop , String> date;

    ShopService sp = new ShopService();

    Shop shop = null;
    ObservableList<Shop> data = FXCollections.observableArrayList();
    @FXML
    private TableView<Shop> shopView;
    @FXML
    private TableColumn<Shop , String> name;
    @FXML
    private TableColumn<Shop, String> deletes;
    @FXML
    private TableColumn<Shop, String> update;
    @FXML
    private TextField tfadd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println(sp.afficher());
        load();
    }    
    
    
     public void load() {
        refreshTable();

        // Sets the cell value factories for each column in the table
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        user.setCellValueFactory(new PropertyValueFactory<>("user_s"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        
        
        Callback<TableColumn<Shop, String>, TableCell<Shop, String>> cellFactory = (TableColumn<Shop, String> param) -> {
    // make cell containing buttons
    final TableCell<Shop, String> cell = new TableCell<Shop, String>() {
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
                    shop = shopView.getSelectionModel().getSelectedItem();
                    System.out.println(shop);
                    sp.supprimer(shop);
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

        
        deletes.setCellFactory(cellFactory);
    
        
        Callback<TableColumn<Shop, String>, TableCell<Shop, String>> cellFactory2 = (TableColumn<Shop, String> param) -> {
    // make cell containing buttons
    final TableCell<Shop, String> cell = new TableCell<Shop, String>() {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
                setText(null);
            } else {
                Button updateButton = new Button("Modifier");
               updateButton.setStyle("-fx-background-color: #2196f3; -fx-text-fill: white; -fx-cursor: hand;");
                updateButton.setOnAction((ActionEvent event) -> {
                    // Deletes the selected product and refreshes the table
                    shop = shopView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateshop.fxml"));

                            try {
                                Parent root = loader.load();

                                UpdateshopController controller = loader.getController();
                                controller.setTfname(shop.getName());
                                controller.setTfdescription(shop.getDescription());
                                controller.setTfemail(shop.getEmail());
                                controller.setTfuser(shop.getUser_s());
                                controller.setShop(shop);
                                shopView.getScene().setRoot(root);
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
        shopView.setItems(data);
}
     
     
    private void refreshTable() {

        data.clear();

        List<Shop> c = sp.afficher();
        data.addAll(c);
        shopView.setItems(data);

    }

    @FXML
    private void adds(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajoutershop.fxml"));
                        try {
                            Parent root = loader.load();
                            tfadd.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }
    
     


}
