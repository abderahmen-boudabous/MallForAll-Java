package gui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.Scene;

public class TimeController {

    @FXML
    private AnchorPane root;

    @FXML
    private Label timeLabel;

    private Timeline clockTimeline;

    public void initialize() {
        // Set up the timeline to update the time label every second
        clockTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        event -> updateTimeLabel()),
                new KeyFrame(Duration.seconds(1))
        );
        clockTimeline.setCycleCount(Animation.INDEFINITE);
        clockTimeline.play();
    }

    private void updateTimeLabel() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = now.format(formatter);
        timeLabel.setText(formattedTime);
    }
//Scene scene = new Scene(root, 800, 600);
//scene.getStylesheets().add("path/to/your/css/file.css");
}
