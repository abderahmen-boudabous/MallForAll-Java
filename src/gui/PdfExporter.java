package gui;

import com.itextpdf.text.BaseColor;
import tn.esprit.entities.Category;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tn.esprit.entities.Category;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.text.StyleConstants.ColorConstants;
 import java.io.File;
import java.io.FileOutputStream;


public class PdfExporter {



public static void exportCategoryToPDF(Connection connection) {
    try {
        String fileName = "Category.pdf";
        String downloadPath = System.getProperty("user.home") + "/Downloads/";
        File file = new File(downloadPath + fileName);
        
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        document.add(new Paragraph("Category List"));
        
        String query = "SELECT * FROM category";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            String titre = resultSet.getString("titre"); 
            String description = resultSet.getString("description");

            document.add(new Paragraph(titre + " - " + description));
        }
        
        document.close();
        
        System.out.println("PDF saved to: " + file.getAbsolutePath());
        
    } catch (DocumentException | IOException | SQLException e) {
        e.printStackTrace();
    }
}

    
    
    
  
    
    
public static void exportEventsToPDF(Connection connection) throws DocumentException, SQLException, FileNotFoundException {
    try {
        
        String fileName = System.getProperty("user.home") + "/Downloads/Events.pdf";
        
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        
        // Set table header font and background color
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        headerFont.setSize(12);
        headerFont.setColor(BaseColor.WHITE);
        BaseColor headerBackgroundColor = new BaseColor(51, 102, 153);
        
        // Set cell font and background color
        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA);
        cellFont.setSize(10);
        BaseColor cellBackgroundColor = BaseColor.WHITE;
        
        // Create table with 6 columns
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        
        // Set column widths
        float[] columnWidths = {  2f, 1f, 1f, 2f };
        table.setWidths(columnWidths);
        
        // Add table header cells
      //  PdfPCell idHeaderCell = new PdfPCell(new Phrase("ID", headerFont));
       // idHeaderCell.setBackgroundColor(headerBackgroundColor);
      //  table.addCell(idHeaderCell);
        PdfPCell nomHeaderCell = new PdfPCell(new Phrase("Event Name", headerFont));
        nomHeaderCell.setBackgroundColor(headerBackgroundColor);
        table.addCell(nomHeaderCell);
        PdfPCell spotHeaderCell = new PdfPCell(new Phrase("Spot", headerFont));
        spotHeaderCell.setBackgroundColor(headerBackgroundColor);
        table.addCell(spotHeaderCell);
        PdfPCell durationHeaderCell = new PdfPCell(new Phrase("Duration", headerFont));
        durationHeaderCell.setBackgroundColor(headerBackgroundColor);
        table.addCell(durationHeaderCell);
        PdfPCell dateHeaderCell = new PdfPCell(new Phrase("Date", headerFont));
        dateHeaderCell.setBackgroundColor(headerBackgroundColor);
        table.addCell(dateHeaderCell);
       // PdfPCell categoryHeaderCell = new PdfPCell(new Phrase("Catégorie", headerFont));
       // categoryHeaderCell.setBackgroundColor(headerBackgroundColor);
       // table.addCell(categoryHeaderCell);
        
        // Retrieve events data from database
        String query = "SELECT e.nom, e.spot, e.duration, e.date, c.titre "
                + "FROM event e JOIN category c ON e.category_id = c.id";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        
        // Add data cells to table
        while (resultSet.next()) {
            //int id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            int spot = resultSet.getInt("spot");
            String duration = resultSet.getString("duration");
            LocalDate date = resultSet.getDate("date").toLocalDate();
            String category = resultSet.getString("titre");
            
           // PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(id), cellFont));
           // idCell.setBackgroundColor(cellBackgroundColor);
           // table.addCell(idCell);

            PdfPCell nomCell = new PdfPCell(new Phrase(nom, cellFont));
            nomCell.setBackgroundColor(cellBackgroundColor);
            table.addCell(nomCell);

            PdfPCell spotCell = new PdfPCell(new Phrase(String.valueOf(spot), cellFont));
            spotCell.setBackgroundColor(cellBackgroundColor);
            table.addCell(spotCell);

            PdfPCell durationCell = new PdfPCell(new Phrase(duration, cellFont));
            durationCell.setBackgroundColor(cellBackgroundColor);
            table.addCell(durationCell);

            PdfPCell dateCell = new PdfPCell(new Phrase(date.toString(), cellFont));
            dateCell.setBackgroundColor(cellBackgroundColor);
            table.addCell(dateCell);

           // PdfPCell categoryCell = new PdfPCell(new Phrase(category.getTitre(), cellFont));
           // categoryCell.setBackgroundColor(cellBackgroundColor);
           // table.addCell(categoryCell);
            }

            document.add(table);

            // Add footer
            Paragraph footer = new Paragraph("Events report generated by MallForAll", FontFactory.getFont(FontFactory.HELVETICA, 30));
            footer.setAlignment(Element.ALIGN_RIGHT);
            document.add(footer);

            
            document.close();

            } catch (DocumentException | FileNotFoundException | SQLException e) {
                e.printStackTrace();
              }
  }








}