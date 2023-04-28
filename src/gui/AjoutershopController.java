/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        showAlert("Erreur", "Le nom est vide !");
    } else if (tfdescription.getText().isEmpty()) {
        showAlert("Erreur", "Le description est vide !");
    } else if (tfuser.getText().isEmpty()) {
        showAlert("Erreur", "Le user name est vide !");
    } else if (tfemail.getText().isEmpty()) {
        showAlert("Erreur", "Le email est vide !");
    } else if (!tfemail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
        showAlert("Erreur", "Le format de l'email est invalide !");
    } else {
        Date date = new Date(System.currentTimeMillis());

        Shop shop = new Shop(tfname.getText(), tfdescription.getText(), tfemail.getText(), tfuser.getText(), date, filePath);
        ShopService shopService = new ShopService();
        shopService.ajouter(shop);

        showAlertWithAction("Success", "shop ajoutée", evt -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
            try {
                Parent root = loader.load();
                tfname.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
    }
}

    @FXML
    public void image(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Image File");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
    );
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        try {
            String destPath = "D:/xampp/htdocs/aa/" + selectedFile.getName();
            Files.copy(selectedFile.toPath(), new File(destPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File uploaded to " + destPath);
            filePath = destPath; // update the filePath variable with the selected file path
        } catch (IOException ex) {
            System.err.println("Error uploading file: " + ex.getMessage());
        }
    } else {
        System.out.println("No file selected.");
    }
}

private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
    alert.getButtonTypes().setAll(okButton);
    alert.showAndWait();
}

private void showAlertWithAction(String title, String message, EventHandler<ActionEvent> action) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(message);
    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
    alert.getButtonTypes().setAll(okButton);
    Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
    okBtn.setOnAction(action);
    alert.showAndWait();
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
    
}
    
    

