/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import static sun.font.FontManagerNativeLibrary.load;
import tn.esprit.entities.Product;
import tn.esprit.entities.Shop;
import tn.esprit.services.ProductService;
import tn.esprit.tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLpController implements Initializable {

    @FXML
    private Button sshop;
    @FXML
    private Button pproducts;
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
    @FXML
    private Button home;
    @FXML
    private TableColumn<Product, String> photo;
    @FXML
    private TextField searchfield;
    private Connection cnx=MaConnexion.getInstance().getCnx();
    @FXML
    private TableColumn<Product, String> sale;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sshop.setOnAction(e -> {
        try {
            Parent afficherShop = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Scene scene = new Scene(afficherShop);
            Stage stage = (Stage) sshop.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading affichershop.fxml: " + ex.getMessage());
        }
    });
        pproducts.setOnAction(e -> {
        try {
            Parent afficherShop = FXMLLoader.load(getClass().getResource("FXMLp.fxml"));
            Scene scene = new Scene(afficherShop);
            Stage stage = (Stage) pproducts.getScene().getWindow();
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
    load();
    }    
    
    public void load() {
        refreshTable();

        // Sets the cell value factories for each column in the table
        //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        price.setCellValueFactory(cellData -> {
    Float priceValue = cellData.getValue().getPrice();
    String priceString = (priceValue == null) ? "" : String.format("%.2f DT", priceValue);
    return new SimpleStringProperty(priceString);
});

stock.setCellValueFactory(cellData -> {
    Integer stockValue = cellData.getValue().getStock();
    String stockString = (stockValue == null || stockValue == 0) ? "Out of stock" : stockValue.toString();
    return new SimpleStringProperty(stockString);
});

        
        
        shop.setCellValueFactory(cellData -> {
            ObjectProperty<String> nameProperty = new SimpleObjectProperty<>();
            Shop shop = cellData.getValue().getShop();
            if (shop != null) {
                nameProperty.set(shop.getName());
            }
            return nameProperty;
        });
        
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
       photo.setCellFactory(column -> {
    return new TableCell<Product, String>() {
        private final ImageView imageView = new ImageView();
        
        
            {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            imageView.setFitWidth(100); // set the desired width
            imageView.setFitHeight(100); // set the desired height
        }
        
        
        @Override
        protected void updateItem(String imagePath, boolean empty) {
            super.updateItem(imagePath, empty);
            
            if (empty || imagePath == null) {
                setGraphic(null);
            } else {
                File imageFile = new File(imagePath);
                javafx.scene.image.Image image = new javafx.scene.image.Image(imageFile.toURI().toString());
                imageView.setImage(image);
                setGraphic(imageView);
            }
        }
    };
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
        
        Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFactory3 = (TableColumn<Product, String> param) -> {
    final TableCell<Product, String> cell = new TableCell<Product, String>() {

        private float originalPrice;

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
                setText(null);
            } else {
                HBox buttonBox = new HBox();
                buttonBox.setAlignment(Pos.CENTER);

                ChoiceBox<String> choiceBox = new ChoiceBox<>();
                choiceBox.getItems().addAll("10%", "15%", "20%", "25%", "30%", "35%", "40%", "45%", "50%");
                choiceBox.setValue("10%");

                Button applyButton = new Button("Sale");
                applyButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-cursor: hand;");
                applyButton.setOnAction((ActionEvent event) -> {
                    Product product = getTableView().getItems().get(getIndex());

                    // Save the original price
                    originalPrice = product.getPrice();

                    double discount = Double.parseDouble(choiceBox.getValue().replace("%", "")) / 100.0;
                    double newPrice = originalPrice * (1 - discount);
                    product.setPrice((float) newPrice);
                    pp.update(product);
                    refreshTable();
                });


                buttonBox.getChildren().addAll(choiceBox, applyButton);
                setGraphic(buttonBox);
                setText(null);
            }
        }
    };
    return cell;
};

sale.setCellFactory(cellFactory3);
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

    @FXML
private void search(ActionEvent event) {
try {
String searchQuery = searchfield.getText().trim();
if (searchQuery.isEmpty()) {
// Show an error message if the search field is empty
Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error");
alert.setHeaderText("Search field is empty");
alert.setContentText("Please enter a Product name to search for.");
alert.showAndWait();
return;
}
    if (cnx == null) {
        // Show an error message if the database connection is null
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Database connection is null");
        alert.setContentText("Cannot connect to the database.");
        alert.showAndWait();
        return;
    }

    // Initialize SQL query to search for a product by its name
    String query = "SELECT id, name, price, type, stock, shop_id, photo FROM Product WHERE name LIKE ?";
    try (PreparedStatement pst = cnx.prepareStatement(query)) {
        pst.setString(1, "%" + searchQuery + "%");

        // Execute SQL query and get the result
        try (ResultSet rs = pst.executeQuery()) {
            // Check if the result is empty
            if (!rs.next()) {
                // Show an error message if there are no results
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Product not found");
                alert.setContentText("Cannot find a Product with the name \"" + searchQuery + "\".");
                alert.showAndWait();
                return;
            }

            // Create a filtered list based on the original list of items
            FilteredList<Product> filteredList = new FilteredList<>(productView.getItems());

            // Set the filter predicate based on the search query
            filteredList.setPredicate(product ->
                    product.getName().toLowerCase().contains(searchQuery.toLowerCase()));

            // Set the filtered list as the items of the TableView
            productView.setItems(filteredList);
        }
    }

} catch (SQLException e) {
    e.printStackTrace();
}
}

    @FXML
    private void X(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLp.fxml"));
                        try {
                            Parent root = loader.load();
                            tete.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
        
    }

    @FXML
    private void datachart(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("graphdata.fxml"));
                        try {
                            Parent root = loader.load();
                            tete.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }
    
    
}
    

