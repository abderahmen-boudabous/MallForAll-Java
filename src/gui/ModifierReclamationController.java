/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Avis;
import entities.Reclamations;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ServiceAvis;
import services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author arafet
 */
public class ModifierReclamationController implements Initializable {

    @FXML
    private TextField Etat_reclamation;
    @FXML
    private TextArea Text_reponse;
    @FXML
    private Button modifierB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        modifierB.setOnAction(event -> {

            System.out.println("test");
            try {
                ServiceReclamation ps = new ServiceReclamation();
                Reclamations r = new Reclamations(Text_reponse.getText(), Etat_reclamation.getText(), this.getId_reclamation());

                ps.modifier(r);
                System.out.println("test2");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
                Parent root = loader.load();

                Scene scene = modifierB.getScene();
                scene.setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ModifierAvisController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    public static int id_reclamation;

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    void setReclamations(Reclamations c) {
        this.setId_reclamation(c.getId_reclamation());
        Text_reponse.setText(c.getText_reclamation());
        Etat_reclamation.setText(c.getType_reclamation());
    }

}
