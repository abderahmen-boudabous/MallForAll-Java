package gui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.File;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class QRCodeGenerator {
    
    @FXML
    private TextArea qrCodeDataTextArea;
    
    @FXML
    private ImageView qrCodeImageView;
    
    @FXML
    private Label statusLabel;
    
    private String qrCodeData;
    
    private FileChooser fileChooser;
    
    public QRCodeGenerator() {
        fileChooser = new FileChooser();
    }
    
    @FXML
    void handleGenerateQRCodeButtonAction(ActionEvent event) {
        // Get the data to encode from the text area
        qrCodeData = qrCodeDataTextArea.getText().trim();
        
        if (qrCodeData.isEmpty()) {
            statusLabel.setText("Error: No data to encode");
            return;
        }
        
        // Generate the QR code image
       /* BufferedImage qrCodeImage = generateQRCodeImage(qrCodeData.split("\\n"));
        
        if (qrCodeImage != null) {
            // Convert the QR code image to a JavaFX Image
            Image fxImage = SwingFXUtils.toFXImage(qrCodeImage, null);
            
            // Display the QR code image in the ImageView
            qrCodeImageView.setImage(fxImage);
            
            statusLabel.setText("QR code generated successfully");
        } else {
            statusLabel.setText("Error: Failed to generate QR code");
        } */
    }
    
    @FXML
    void handleSaveQRCodeButtonAction(ActionEvent event) {
        if (qrCodeData == null || qrCodeData.isEmpty()) {
            statusLabel.setText("Error: No data to save");
            return;
        }
        
        // Show the file chooser dialog
        fileChooser.setTitle("Save QR Code");
        fileChooser.setInitialFileName("qrcode.png");
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image (*.png)", "*.png"));
        
        Stage stage = (Stage) qrCodeImageView.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        
        if (file != null) {
            try {
                // Convert the QR code image to a byte array
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
               // ImageIO.write(generateQRCodeImage(qrCodeData.split("\\n")), "png", baos);
                baos.flush();
                byte[] imageData = baos.toByteArray();
                baos.close();
                
                // Write the byte array to the selected file
                java.nio.file.Files.write(file.toPath(), imageData);
                
                statusLabel.setText("QR code saved successfully");
            } catch (Exception e) {
                statusLabel.setText("Error: Failed to save QR code");
                e.printStackTrace();
            }
        }
    }
/**
 * Generates a QR code image from the given data.
 *
 * @param data the data to encode in the QR code
 * @return the generated QR code image as a BufferedImage
 */
private BufferedImage generateQRCodeImage(String data) {
    // Generate the QR code matrix
    Map<EncodeHintType, Object> hints = new HashMap<>();
    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix;
    try {
        bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200, hints);
    } catch (WriterException e) {
        e.printStackTrace();
        return null;
    }

    // Convert the bit matrix to a BufferedImage
    int width = bitMatrix.getWidth();
    int height = bitMatrix.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
            image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
        }
    }

    return image;
}

/**
 * Clears the QR code data and image.
 */
public void clear() {
    qrCodeDataTextArea.setText("");
    qrCodeImageView.setImage(null);
    statusLabel.setText("");
}

}
