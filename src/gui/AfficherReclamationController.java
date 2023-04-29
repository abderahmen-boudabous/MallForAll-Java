/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Avis;
import entities.Reclamations;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author arafet
 */
public class AfficherReclamationController implements Initializable {

    @FXML
    private TableView<Reclamations> ReclamationsTv;
    @FXML
    private TableColumn<Reclamations, Integer> id_reclamationTv;
    @FXML
    private TableColumn<Reclamations, Integer> id_utilisateurTv;
    @FXML
    private TableColumn<Reclamations, String> text_reclamationTv;
    @FXML
    private TableColumn<Reclamations, String> etat_reclamationTv;
    @FXML
    private TableColumn<Reclamations, String> type_reclamationTv;
    @FXML
    private TableColumn<Reclamations, String> date_reponseTv;
    @FXML
    private TableColumn<Reclamations, String> text_reponseTv;
    @FXML
       private TableColumn<Reclamations, String> date_reclamationTv;
    @FXML
        private TableColumn<Reclamations, Button> modifier;
    
    
   
    @FXML
         private Label welcomeLb;
     ServiceReclamation ps = new ServiceReclamation();
   

    @FXML
    private TableColumn<Reclamations, Button> delete;
    @FXML
    private Button exportExcelBtn;
    @FXML
    private Button btnDisplayStats;
  
    
     @FXML
     
     
     private void handleImageViewClick(MouseEvent event)
     {
       
         try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Enregistrer le fichier Excel");
           fileChooser.setInitialFileName(".xlsx");
            File file = fileChooser.showSaveDialog(null);
           
            if (file != null) {
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet =  workbook.createSheet("Avis");
               
                // écrire les en-têtes de colonne
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Id_Reclamation");
                headerRow.createCell(1).setCellValue("Id_utulisateur");
                headerRow.createCell(2).setCellValue("Text_Reclamation");
                headerRow.createCell(3).setCellValue("Date_Reclamation");
                headerRow.createCell(4).setCellValue("Date_Reponse");
                  headerRow.createCell(5).setCellValue("Text_Reponse");
                    headerRow.createCell(6).setCellValue("type_Reclamation");
                      headerRow.createCell(7).setCellValue("Etat_Reponse");
               
       
       
         // écrire les données de TableView dans la feuille de calcul
                ObservableList<Reclamations> reclamations = ReclamationsTv.getItems();
                for (int i = 0; i < reclamations.size(); i++) {
                    Reclamations a= reclamations.get(i);
                    Row row = sheet.createRow(i + 1);
                    row.createCell(0).setCellValue(a.getId_reclamation());
                    row.createCell(1).setCellValue(a.getId_utilisateur());
                    row.createCell(2).setCellValue(a.getText_reclamation());
                    row.createCell(3).setCellValue(a.getDate_reponse());
                    row.createCell(4).setCellValue(a.getDate_reclamation());
                     row.createCell(5).setCellValue(a.getText_reponse());
                      row.createCell(6).setCellValue(a.getType_reclamation());
                       row.createCell(7).setCellValue(a.getEtat_reclamation());
                }
               
                FileOutputStream fos = new FileOutputStream(file);
                workbook.write(fos);
                workbook.close();
                fos.close();
               
       
       
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Enregistrement réussi");
                alert.setHeaderText("Le fichier Excel a été enregistré avec succès.");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }
    
    @Override
    
    @SuppressWarnings("empty-statement")
    public void initialize(URL url, ResourceBundle rb){
        

        try {
            List<Reclamations> Reclamations;
            Reclamations = ps.recuperer();
            ObservableList<Reclamations> olp = FXCollections.observableArrayList(Reclamations);
                   ReclamationsTv.setItems(olp);
            id_reclamationTv.setCellValueFactory(new PropertyValueFactory("id_reclamation"));
            id_utilisateurTv.setCellValueFactory(new PropertyValueFactory("id_utilisateur"));
            text_reclamationTv.setCellValueFactory(new PropertyValueFactory("text_reclamation"));
            etat_reclamationTv.setCellValueFactory(new PropertyValueFactory("etat_reclamation"));
            type_reclamationTv.setCellValueFactory(new PropertyValueFactory("type_reclamation"));
            date_reponseTv.setCellValueFactory(new PropertyValueFactory("date_reponse"));
            date_reclamationTv.setCellValueFactory(new PropertyValueFactory("date_reclamation"));
            text_reponseTv.setCellValueFactory(new PropertyValueFactory("text_reponse"));
            this.delete();
            this.modifier();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
            
        }
        
    }

    private void delete() {

        delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            try {
                                if (ps.supprimer(ReclamationsTv.getItems().get(getIndex()))) {
                                    ReclamationsTv.getItems().remove(getIndex());
                                    ReclamationsTv.refresh();

                                }
                            } catch (SQLException ex) {
                                System.out.println("erreor:" + ex.getMessage());

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }
    private void modifier() {
            modifier.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("modifier");
                        b.setOnAction((event) -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReclamation.fxml"));
                                Parent root = loader.load();
                                gui.ModifierReclamationController modifier = loader.getController();
                                Reclamations c = ReclamationsTv.getItems().get(getIndex());
                                modifier.setReclamations(c);
                                Scene scene = b.getScene();
                                scene.setRoot(root);
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherAvisController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }
    
public void setData(String Message) {
        
    }


   
    }

