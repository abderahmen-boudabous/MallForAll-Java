package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void updateshop(ActionEvent event) {
        String name = tfname.getText();
        String description = tfdescription.getText();
        String email = tfemail.getText();
        String user_s = tfuser.getText();

        if (name.isEmpty() || description.isEmpty() || email.isEmpty() || user_s.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty fields");
            alert.setContentText("Please fill in all the fields.");
            alert.showAndWait();
            return;
        }

        if (!isValidEmail(email)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid email format");
            alert.setContentText("Please enter a valid email address.");
            alert.showAndWait();
            return;
        }

        shop.setName(name);
        shop.setDescription(description);
        shop.setEmail(email);
        shop.setUser_s(user_s);

        ShopService shopService = new ShopService();
        shopService.update(shop);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Shop updated");
        ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
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

    private boolean isValidEmail(String email) {
        // Email regex pattern
        Pattern pattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @FXML
    private void retourshop(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichershop.fxml"));
                        try {
                            Parent root = loader.load();
                            tfname.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }
}
