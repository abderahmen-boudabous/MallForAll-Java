/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class FXMLpfrontController implements Initializable {

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
    
    private TextField tete;
    @FXML
    private Button home;
    @FXML
    private TableColumn<Product, String> photo;
    @FXML
    private TextField searchfield;
    private Connection cnx=MaConnexion.getInstance().getCnx();
    



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        sshop.setOnAction(e -> {
        try {
            Parent afficherShop = FXMLLoader.load(getClass().getResource("FXMLfront.fxml"));
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
            Parent afficherShop = FXMLLoader.load(getClass().getResource("FXMLpfront.fxml"));
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
            Parent afficherShop = FXMLLoader.load(getClass().getResource("homefront.fxml"));
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

    productView.setItems(data);
}
private void refreshTable() {

        data.clear();

        List<Product> products = pp.afficherr();
        data.addAll(products);
        productView.setItems(data);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLpfront.fxml"));
                        try {
                            Parent root = loader.load();
                            searchfield.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
        
    }

    private void sortProducts(String option) {
    switch (option) {
        case "Price":
            // Sort products by price (ascending order)
            data.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
            break;
        case "Stock":
            // Sort products by stock (descending order)
            data.sort((p1, p2) -> Integer.compare(p2.getStock(), p1.getStock()));
            break;
        case "Name":
            // Sort products by name (ascending order)
            data.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
            break;
        default:
            // Do nothing if an invalid option is selected
            break;
    }
}

    
    @FXML
private void sortbyprice(ActionEvent event) {
    sortProducts("Price");
}

@FXML
private void sortbyname(ActionEvent event) {
    sortProducts("Name");
}


}
