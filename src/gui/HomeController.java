package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;



public class HomeController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private Button evc;
    @FXML
    private Button ev;
    
    String path = "C:\\Users\\msi\\Desktop\\PidevJ3A40\\src\\image\\sound.mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    @FXML
    private Button front1;
    @FXML
    private Button front2;
    @FXML
    private ImageView homeImage;
    @FXML
    private Button ticket;
    @FXML
    private Button supplier;

    @FXML
    void playSound(ActionEvent event) {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        } else {
            mediaPlayer.play();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        

        
        
        evc.setOnAction(e -> {
        try {
            Parent afficherEvent = FXMLLoader.load(getClass().getResource("ListEvent.fxml"));
            Scene scene = new Scene(afficherEvent);
            Stage stage = (Stage) evc.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading page " + ex.getMessage());
        }
    });
        ticket.setOnAction(e -> {
        try {
            Parent afficherEvent = FXMLLoader.load(getClass().getResource("menur3.fxml"));
            Scene scene = new Scene(afficherEvent);
            Stage stage = (Stage) ticket.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading page " + ex.getMessage());
        }
    });
        ev.setOnAction(e -> {
        try {
            Parent afficherCategory = FXMLLoader.load(getClass().getResource("ListCategory.fxml"));
            Scene scene = new Scene(afficherCategory);
            Stage stage = (Stage) ev.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading page " + ex.getMessage());
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
            System.err.println("Error loading page " + ex.getMessage());
        }
    });
        front1.setOnAction(e -> {
    try {
        Parent afficherShop = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene scene = new Scene(afficherShop);
        Stage stage = (Stage) home.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.err.println("Error loading page " + ex.getMessage());
    }
    });
        front2.setOnAction(e -> {
        try {
             Parent root = FXMLLoader.load(getClass().getResource("FXMLp.fxml"));
             Scene scene = new Scene(root);
             Stage stage = (Stage) home.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading page " + ex.getMessage());
        }
    });


        
/*         front.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });  */

         
         

    } 
    
    
private void openRatingPage() {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("Rating.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Rate Events");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void redsupplier(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherFournisseur.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }




}