/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Avis;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ServiceAvis;

/**
 * FXML Controller class
 *
 * @author arafet
 */
public class ModifierAvisController implements Initializable {

    @FXML
    private TextField rate_avis;
    @FXML
    private TextArea texte_avis;
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
            ServiceAvis ps = new ServiceAvis();
            Avis a=new Avis(ModifierAvisController.getId_avis(), texte_avis.getText(),Float.parseFloat(rate_avis.getText()));
           ps.modifier(a);
            System.out.println("test2");
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAvis.fxml"));
            Parent root = loader.load();
            
            Scene scene = modifierB.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifierAvisController.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (SQLException ex) {
                Logger.getLogger(ModifierAvisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
        
        });
        
     
        
        
    } 
    private static int id_avis;

    public static int getId_avis() {
        return id_avis;
    }

    public static void setId_avis(int id_avis) {
        ModifierAvisController.id_avis = id_avis;
    }
    
    public void setAvis(Avis c) {
        ModifierAvisController.setId_avis(c.getId_avis());
           texte_avis.setText(c.getText_avis());
         rate_avis.setText(Float.toString(c.getRate_avis()));
        }
    
    
    
    
//    
//    private void modifierB(ActionEvent event) {
//        
//        System.out.println("test");
//        try {
//            circuitCrud ps = new circuitCrud();
//            
//           ps.modifier(Point_de_depart.getText(), Date_d.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), Date_F.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), Integer.parseInt(Nbr_Place.getText()), description.getText(), Integer.parseInt(Nbr_Jour.getText()) , description.getText(), Integer.parseInt(Id_Uti.getText()), Nom_Cir.getText(), Integer.parseInt(id_circuit.getText()));
//            System.out.println("test2");
//           FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_Circuit.fxml"));
//            Parent root = loader.load();
//            
//            Scene scene = modifier.getScene();
//            scene.setRoot(root);
//        } catch (IOException ex) {
//            Logger.getLogger(ModifiercircuitController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    
    
}
