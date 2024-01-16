package javafxf_functions;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.stage.FileChooser;
import com.itextpdf.text.pdf.BaseFont;

import java.io.File;
import java.io.FileOutputStream;

public class PrintController {

    public void PrintCheck(String personrented, int pay, String date, String dateRented, String dateReturn, String cashiername) {
        try {
            FileChooser fileChooser = new FileChooser();

            // Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(extFilter);

            // Show save file dialog
          // You might need to pass the current stage reference to open the dialog
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(file));

                document.open();

                // Create a font for the title and content
                Font titleFont = new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 18);
                Font contentFont = new Font(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 12);

                // Add company information
                // Add company information with custom font and adjusted position
                Paragraph companyName = new Paragraph("Company Name: Car Rental Reservation System", titleFont);
                companyName.setIndentationLeft(50); // Adjust X position
                companyName.setSpacingBefore(20); // Adjust Y position
                document.add(companyName);

                Paragraph emailAddress = new Paragraph("Email Address: medinajrfrouen@gmail.com", contentFont);
                emailAddress.setIndentationLeft(50); // Adjust X position
                emailAddress.setSpacingBefore(10); // Adjust Y position
                document.add(emailAddress);

                Paragraph companyAddress = new Paragraph("Address: Tinikling st, Matina Crossing Davao City.", contentFont);
                companyAddress.setIndentationLeft(50); // Adjust X position
                companyAddress.setSpacingBefore(10); // Adjust Y position
                document.add(companyAddress);

                // Add check information
                document.add(new Paragraph("\nCheck", titleFont));
                document.add(new Paragraph("Person Rented: " + personrented , contentFont));
                document.add(new Paragraph("Pay: " + pay + " Pesos", contentFont));
                document.add(new Paragraph("Date: " + date, contentFont));
                document.add(new Paragraph("Date Rented: " + dateRented, contentFont));
                document.add(new Paragraph("Date Return: " + dateReturn, contentFont));
                document.add(new Paragraph("Cashier Name: " + cashiername, contentFont));

                document.close();
            } else {
                System.out.println("File selection cancelled.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
