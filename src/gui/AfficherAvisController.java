/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Avis;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tools.ant.taskdefs.Local;
import  tn.esprit.services.ServiceAvis;

/**
 * FXML Controller class
 *
 * @author arafet
 */
public class AfficherAvisController implements Initializable {

    @FXML
    private TableView<Avis> AvisTv;
    @FXML
    private TableColumn<Avis, Integer> id_avisTv;
    @FXML
    private TableColumn<Avis, Integer> id_utilisateurTv;
    @FXML
    private TableColumn<Avis, String> text_avisTv;
    @FXML
    private TableColumn<Avis, String> date_avisTv;
    @FXML
    private TableColumn<Avis, Float> rate_avisTv;
    @FXML
    private Button exportExcelBtn;
    @FXML
    private Button home;
    @FXML
    private Button evc;
    @FXML
    private Button ev;
    @FXML
    private Button front1;
    @FXML
    private Button front2;
    @FXML
    private Button ticket;
    @FXML
    private Button returnbtn;
    @FXML
    private Label eventNameLabel;
    @FXML
    private TextField tfa;
    @FXML
     private void handleImageViewClick(MouseEvent event) {
       
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
                headerRow.createCell(0).setCellValue("Id_avis");
                headerRow.createCell(1).setCellValue("Id_utilisateur");
                headerRow.createCell(2).setCellValue("Text_avis");
                headerRow.createCell(3).setCellValue("Date_avis");
                headerRow.createCell(4).setCellValue("getRate_avis");
               
       
       
         // écrire les données de TableView dans la feuille de calcul
                ObservableList<Avis> avis = AvisTv.getItems();
                for (int i = 0; i < avis.size(); i++) {
                    Avis a= avis.get(i);
                    Row row = sheet.createRow(i + 1);
                    row.createCell(0).setCellValue(a.getId_avis());
                    row.createCell(1).setCellValue(a.getId_utilisateur());
                    row.createCell(2).setCellValue(a.getText_avis());
                    row.createCell(3).setCellValue(a.getDate_avis());
                    row.createCell(4).setCellValue(a.getRate_avis());
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
    private Label welcomeLb;
    // instance database Service 
    ServiceAvis ps = new ServiceAvis();
    @FXML
    private TableColumn<Avis, Button> delete;
    @FXML
    private TableColumn<Avis, Button> modifier;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Avis> Avis;
        
        try {
            Avis = ps.recuperer();
            ObservableList<Avis> olp = FXCollections.observableArrayList(Avis);
            AvisTv.setItems(olp);
            id_avisTv.setCellValueFactory(new PropertyValueFactory("id_avis"));
            id_utilisateurTv.setCellValueFactory(new PropertyValueFactory("id_utilisateur"));
            text_avisTv.setCellValueFactory(new PropertyValueFactory("text_avis"));
            date_avisTv.setCellValueFactory(new PropertyValueFactory("date_avis"));
            rate_avisTv.setCellValueFactory(new PropertyValueFactory("rate_avis"));
            this.delete();
            this.modifier();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherAvisController.class.getName()).log(Level.SEVERE, null, ex);
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
                                if (ps.supprimer(AvisTv.getItems().get(getIndex()))) {
                                    AvisTv.getItems().remove(getIndex());
                                    AvisTv.refresh();

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
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierAvis.fxml"));
                                Parent root = loader.load();
                                gui.ModifierAvisController modifier = loader.getController();
                                Avis c = AvisTv.getItems().get(getIndex());

                                modifier.setAvis(c);
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
        welcomeLb.setText("Welcomme " + Message);

    }

    @FXML
    private void gb(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("menur3.fxml"));
                        try {
                            Parent root = loader.load();
                            tfa.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
    }
    }


