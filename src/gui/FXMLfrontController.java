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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import tn.esprit.entities.Shop;
import tn.esprit.services.ShopService;
import tn.esprit.tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLfrontController implements Initializable {

    @FXML
    private Button sshop;
    @FXML
    private Button pproducts;
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
    private Button home;
    @FXML
    private TextField searchfield;
    
     private Connection cnx=MaConnexion.getInstance().getCnx();
    
    @FXML
    private TableColumn<Shop, String> like;
    @FXML
    private TableColumn<Shop, String> dislike;
    @FXML
    private TableColumn<Shop, String> likebutton;
    @FXML
    private TableColumn<Shop, String> img;

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
    user.setCellValueFactory(new PropertyValueFactory<>("user_s"));
    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    description.setCellValueFactory(new PropertyValueFactory<>("description"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    date.setCellValueFactory(new PropertyValueFactory<>("date"));
    img.setCellValueFactory(new PropertyValueFactory<>("img"));
       img.setCellFactory(column -> {
    return new TableCell<Shop, String>() {
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
    like.setCellValueFactory(new PropertyValueFactory<>("like"));
    dislike.setCellValueFactory(new PropertyValueFactory<>("dislike"));
    
  
           Callback<TableColumn<Shop, String>, TableCell<Shop, String>> cellFactory = (TableColumn<Shop, String> param) -> {
    final TableCell<Shop, String> cell = new TableCell<Shop, String>() {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
                setText(null);
            } else {
                Button likeButton = new Button("Like");
                likeButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-cursor: hand;");
                likeButton.setOnAction((ActionEvent event) -> {
                    // Increases the "like" count and refreshes the table
                    Shop shop = getTableView().getItems().get(getIndex());
                    shop.setLike(shop.getLike() + 1);
                    sp.updatel(shop);
                    getTableView().refresh();
                });
                Button dislikeButton = new Button("Dislike");
                dislikeButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white; -fx-cursor: hand;");
                dislikeButton.setOnAction((ActionEvent event) -> {
                    // Decreases the "dislike" count and refreshes the table
                    Shop shop = getTableView().getItems().get(getIndex());
                    shop.setDislike(shop.getDislike() + 1);
                    sp.updatel(shop);
                    getTableView().refresh();
                });
                HBox buttonsContainer = new HBox(likeButton, dislikeButton);
                buttonsContainer.setStyle("-fx-alignment:center");
                setGraphic(buttonsContainer);
                setText(null);
            }
        }
    };
    return cell;
};
likebutton.setCellFactory(cellFactory);



/**********************************************************************************************************/

    shopView.setItems(data);
}

        private void refreshTable() {
            data.clear();
            List<Shop> c = sp.afficher();
            data.addAll(c);
        }
    

     public ObservableList<Shop> afficherListeShop() {
    ShopService shopService = new ShopService();
    ObservableList<Shop> shopsList = FXCollections.observableArrayList(shopService.afficher());
    return shopsList;
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
            alert.setContentText("Please enter a shop name to search for.");
            alert.showAndWait();
            return;
        }
        // Initialize SQL query to search for a shop by its name
        String query = "SELECT id, name, description, email, user_s, date, img FROM Shop WHERE name LIKE ?";
        PreparedStatement pst = cnx.prepareStatement(query);
        pst.setString(1, "%" + searchQuery + "%");
        // Execute SQL query and get the result
        ResultSet rs = pst.executeQuery();

        // Check if the result is empty
        if (!rs.next()) {
            // Show an error message if there are no results
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Shop not found");
            alert.setContentText("Cannot find a shop with the name \"" + searchQuery + "\".");
            alert.showAndWait();
            return;
        }
        // Create a new Shop object from the SQL query results
        Shop shop = new Shop(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getString("email"),
            rs.getString("user_s"),
            rs.getDate("date"),
            rs.getString("img")
        );
        // Get the list of items in the TableView
        ObservableList<Shop> items = shopView.getItems();

        // Clear all items from the list
        items.clear();

        // Add the Shop object to the TableView items list
        items.add(shop);

        // Refresh the TableView
        shopView.refresh();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void X(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLfront.fxml"));
                        try {
                            Parent root = loader.load();
                            searchfield.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
        
    }

}
