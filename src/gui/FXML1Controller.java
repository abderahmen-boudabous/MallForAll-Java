/*package gui;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import tn.esprit.entities.Product;
import tn.esprit.entities.Shop;
import tn.esprit.services.ProductService;

public class FXML1Controller implements Initializable {

    @FXML
    private GridPane gridpane;

    private ObservableList<Product> productList = FXCollections.observableArrayList();
    private ProductService productService = new ProductService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Get list of products from ProductService
        List<Product> products = productService.getAllProducts();
        // Convert to observable list
        productList.addAll(products);

        int rowIndex = 1;
        for (Product product : productList) {
            // Create labels for product data
            Label nameLabel = new Label(product.getName());
            Label typeLabel = new Label(product.getType());
            Label priceLabel = new Label(String.valueOf(product.getPrice()));
            Label stockLabel = new Label(String.valueOf(product.getStock()));
            Label shopLabel = new Label(product.getShop().getName());

            // Create ImageView for product photo
            ImageView photoView = new ImageView(new Image(new File(product.getPhoto()).toURI().toString()) {});
            photoView.setFitHeight(100);
            photoView.setFitWidth(100);

            // Add labels and ImageView to GridPane
            gridpane.add(nameLabel, 0, rowIndex);
            gridpane.add(typeLabel, 1, rowIndex);
            gridpane.add(priceLabel, 2, rowIndex);
            gridpane.add(stockLabel, 3, rowIndex);
            gridpane.add(shopLabel, 4, rowIndex);
            gridpane.add(photoView, 5, rowIndex);

            // Increment row index
            rowIndex++;
        }
    }

}
*/