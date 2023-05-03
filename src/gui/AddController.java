package gui;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import gui.AutocompleteComboBox;
import gui.ListEventController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import tn.esprit.entities.Category;
import tn.esprit.entities.Event;
import tn.esprit.services.CategoryEventService;
import tn.esprit.tools.MaConnexion;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddController implements Initializable {

    @FXML
    private Text textimage;

    private ObservableList<Category> categoryList;

    @FXML
    private Button image;

    @FXML
    private ImageView imageView1;

    @FXML
    private JFXComboBox<Category> cmbIduser;

    @FXML
    private JFXTextField spot;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField duration;

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton cancel;

    @FXML
    void add(ActionEvent event) throws SQLException {
        CategoryEventService srec = new CategoryEventService();


        String desc_eve = spot.getText();
        if (desc_eve == null || desc_eve.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("La spot ne peut pas être vide");
            alert.showAndWait();
            return;
        }
        String durationv = duration.getText();
        if (durationv == null || durationv.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Le durationv ne peut pas être vide");
            alert.showAndWait();
            return;
        }
        String title = nom.getText();
        if (title == null || title.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Le nom ne peut pas être vide");
            alert.showAndWait();
            return;
        }
        Integer id_categorie= AutocompleteComboBox.getComboBoxValue(cmbIduser).getId();

/*
        Event event1=new Event(id_categorie,title,0,durationv);
        try {
            srec.createOne(event1);
        } catch (SQLException ex) {
            Logger.getLogger(AddController.class.getName()).log(Level.SEVERE, null, ex);
        } */
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ListEvent.fxml"));
            Parent root = loader.load();
            ListEventController controller = loader.getController();
            controller.initialize(null, null); // Call the initialize method of the controller to refresh the table view
            Scene scene = new Scene(root);
            Stage stage = (Stage) add.getScene().getWindow(); // Get the current stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @FXML
    void addimage(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeComboBox();
    }
    private void initializeComboBox() {
        loadComboBox();
        AutocompleteComboBox.autoCompleteComboBoxPlus(cmbIduser, (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.toString().equals(typedText));
    }

    private void loadComboBox() {
        ArrayList<Category> list = new ArrayList<>();
        try {
            String sql = "SELECT id, titre FROM category";
            PreparedStatement preparedStatement = MaConnexion.getInstance().getCnx().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("titre");
                list.add(new Category(id, description));
            }
        } catch (SQLException ex) {
            // Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        categoryList = FXCollections.observableArrayList(list);
        cmbIduser.setItems(categoryList);
    }

}
