/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Avis;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import org.controlsfx.control.Rating;
import  tn.esprit.tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author arafet
 */
public class CadreController implements Initializable {

    @FXML
    private VBox box_avis;
    @FXML
    private Rating rate_avis;
    @FXML
    private Label text_avis;
    @FXML
    private ImageView image_user;
    @FXML
    private Label nom_utilisateur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private final String[] colors = {"#bc9f1e", "#BDB2FE", "#bc9f1e", "#FF5056"};

    public void setData(Avis a) throws SQLException {
        Connection cnx = MaConnexion.getInstance().getCnx();
        String s = "select nom_utilisateur,prenom_utilisateur from utilisateurs where id_utilisateur = " + a.getId_utilisateur();
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        String nom_user = null, prenom_user = null;
        while (rs.next()) {
            nom_user = rs.getString("nom_utilisateur");
            prenom_user = rs.getString("prenom_utilisateur");
        }
//    Image image = new Image(getClass().getResourceAsStream()):
//    bookinage, setimage (image)

        rate_avis.setRating(a.getRate_avis());
        nom_utilisateur.setText(nom_user + " " + prenom_user);
        text_avis.setText(a.getText_avis());
        box_avis.setStyle("-fx-background-color: " + colors[(int) (Math.random() * colors.length)] + ";"
                + " -fx-background-radius: 15;"
                + "-fx-effect: dropShadown(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0,10) ;");
    }
}
