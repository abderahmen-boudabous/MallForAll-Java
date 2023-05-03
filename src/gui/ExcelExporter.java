package gui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import entities.Avis;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExporter {
    
    public static void export(List<Avis> avisList, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Avis");
        
        // Créer une ligne pour l'en-tête
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID Avis");
        headerRow.createCell(1).setCellValue("ID Utilisateur");
        headerRow.createCell(2).setCellValue("Texte Avis");
        headerRow.createCell(3).setCellValue("Date Avis");
        headerRow.createCell(4).setCellValue("Note Avis");
        
        // Ajouter les données de la table
        int rowNum = 1;
        for (Avis avis : avisList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(avis.getId_avis());
            row.createCell(1).setCellValue(avis.getId_utilisateur());
            row.createCell(2).setCellValue(avis.getText_avis());
            row.createCell(3).setCellValue(avis.getDate_avis());
            row.createCell(4).setCellValue(avis.getRate_avis());
        }
        
        // Ajuster la largeur des colonnes
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        
        // Enregistrer le fichier Excel
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }
        
        System.out.println("Le fichier Excel a été créé avec succès !");
    }
    
}