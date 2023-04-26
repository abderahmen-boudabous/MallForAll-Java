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
import java.sql.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.entities.Shop;
import tn.esprit.services.ShopService;


/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutershopController implements Initializable {

    @FXML
    private TextField tfuser;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfemail;
    @FXML
    private Button sshop;
    @FXML
    private Button pproducts;
    @FXML
    private Button home;
    @FXML
    private ImageView imageview;
    
    private String filePath;

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
    }    

    @FXML
    private void ajoutershop(ActionEvent event) {
        
        if (tfname.getText().isEmpty()) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText(null);

            aler.setContentText("Le nom est vide !");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait(); 
        }
        else if (tfdescription.getText().isEmpty()) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText(null);

            aler.setContentText("Le description est vide !");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait();
        }
        else if (tfuser.getText().isEmpty()) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText(null);

            aler.setContentText("Le user name est vide !");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait();
        }
        else if (tfemail.getText().isEmpty()) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText(null);
            aler.setContentText("Le email est vide !");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait();
        } else if (!tfemail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            Alert aler = new Alert(Alert.AlertType.ERROR);
            aler.setTitle("Erreur");
            aler.setHeaderText(null);
            aler.setContentText("Le format de l'email est invalide !");
            ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            aler.getButtonTypes().setAll(okButton);
            aler.showAndWait();
        }
        
        else {
            Date date = new Date(System.currentTimeMillis());
            
            Shop shop = new Shop (tfname.getText(),tfdescription.getText(),tfemail.getText(),tfuser.getText(),date,filePath);
            ShopService shopService = new ShopService();
            shopService.ajouter(shop); 
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("shop ajoutée");
                    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(okButton);
                    Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
                    okBtn.setOnAction(evt -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
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
    private void Returnshop(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
                        try {
                            Parent root = loader.load();
                            tfname.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }

    @FXML
    private void image(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selection une image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (.jpg, *.png, *.gif)", ".jpg", ".jpeg", ".png", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        if (file == null) {
            // User didn't select a file
            return;
        }
        // Stockez le chemin d'accès du fichier sélectionné
        filePath = file.getAbsolutePath();

        // Affichez l'image sélectionnée dans l'ImageView
        javafx.scene.image.Image image = new javafx.scene.image.Image(file.toURI().toString());
        imageview.setImage(image);
    }
    
}
    
    

