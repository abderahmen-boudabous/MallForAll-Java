/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import tn.esprit.entities.Shop;
import tn.esprit.services.ShopService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class UpdateshopController implements Initializable {

    @FXML
    private TextField tfuser;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfemail;
    
    private Shop shop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateshop(ActionEvent event) {
        
        shop.setName(tfname.getText());
        shop.setDescription(tfdescription.getText());
        shop.setEmail(tfemail.getText());
        shop.setUser_s(tfuser.getText());
        
        
        ShopService shopService = new ShopService();
            shopService.update(shop); 
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("shop Modifier");
                    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(okButton);
                    Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
                    okBtn.setOnAction(evt -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichershop.fxml"));
                        try {
                            Parent root = loader.load();
                            tfname.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    });
                    alert.showAndWait();
        
    }

    public void setTfuser(String tfuser) {
        this.tfuser.setText(tfuser);
    }

    public void setTfname(String tfname) {
        this.tfname.setText(tfname);
    }

    public void setTfdescription(String tfdescription) {
        this.tfdescription.setText(tfdescription);
    }

    public void setTfemail(String tfemail) {
        this.tfemail.setText(tfemail);
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
    
}
