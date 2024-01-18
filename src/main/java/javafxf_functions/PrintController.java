package javafxf_functions;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx_table_functions.Report;
import javafx_table_functions.SalesTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
                // Make sure it has the correct extension
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
                document.add(new Paragraph("Person Rented: " + personrented, contentFont));
                document.add(new Paragraph("Pay: " + pay + " Pesos", contentFont));
                document.add(new Paragraph("Date: " + date, contentFont));
                document.add(new Paragraph("Date Rented: " + dateRented, contentFont));
                document.add(new Paragraph("Date Return: " + dateReturn, contentFont));
                document.add(new Paragraph("Cashier Name: " + cashiername, contentFont));

                document.close();
                System.runFinalization();
            } else {
                System.out.println("File selection cancelled.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printReports_Excel(TableView<SalesTable> SalesView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Excel File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            String filePath = file.getAbsolutePath();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sales Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            ObservableList<TableColumn<SalesTable, ?>> columns = SalesView.getColumns();
            for (int i = 0; i < columns.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns.get(i).getText());
            }

            // Populate data rows
            ObservableList<SalesTable> data = SalesView.getItems();
            for (int i = 0; i < data.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                for (int j = 0; j < columns.size(); j++) {
                    Cell cell = dataRow.createCell(j);
                    setCellValue(cell, getCellValue(data.get(i), columns.get(j)));
                }
            }

            // Write to Excel file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Close the workbook to release resources
            try {
                workbook.close();
                System.runFinalization();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setCellValue(Cell cell, Object value) {
        if (value == null) {
            // Handle null values
            cell.setCellValue("");
        } else if (value instanceof String) {
            String dateString = (String) value;

            // Check if the string is a valid date
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateString);
                cell.setCellValue(date);
            } catch (ParseException e) {
                // If the string is not a valid date, set it as a string without parsing
                cell.setCellValue(dateString);
            }
        } else if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            // Handle other data types or customize as needed
            cell.setCellValue(value.toString());
        }
    }

    private static Object getCellValue(SalesTable sales, TableColumn<SalesTable, ?> column) {
        // Replace this with the actual method or property to get cell value based on TableColumn
        if (column.getText().equals("SalesID")) {
            return sales.getSalesID();
        } else if (column.getText().equals("Person Name")) {
            return sales.getPersonName();  // Use the getter for the property value
        } else if (column.getText().equals("Date")) {
            // Convert the string date to a Date object
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.parse(sales.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
                return null;  // Handle parsing error
            }
        } else if (column.getText().equals("Amount")) {
            return sales.getPayAmount();  // Use the getter for the property value
        } else if (column.getText().equals("CashierName")) {
            return sales.getCashierName();  // Use the getter for the property value
        } else {
            return null;
        }
    }

    public void printReportsTable_Excel(TableView<Report> ReportView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Excel File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            String filePath = file.getAbsolutePath();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Report Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            ObservableList<TableColumn<Report, ?>> columns = ReportView.getColumns();
            for (int i = 0; i < columns.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns.get(i).getText());
            }

            // Populate data rows
            ObservableList<Report> data = ReportView.getItems();
            for (int i = 0; i < data.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                for (int j = 0; j < columns.size(); j++) {
                    Cell cell = dataRow.createCell(j);
                    setCellValueReport(cell, getCellValueReport(data.get(i), columns.get(j)));
                }
            }

            // Write to Excel file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Close the workbook to release resources
            try {
                workbook.close();
                System.runFinalization();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setCellValueReport(Cell cell, Object value) {
        if (value == null) {
            // Handle null values
            cell.setCellValue("");
        } else if (value instanceof String) {
            String dateString = (String) value;

            // Check if the string is a valid date
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateString);
                cell.setCellValue(date);
            } catch (ParseException e) {
                // If the string is not a valid date, set it as a string without parsing
                cell.setCellValue(dateString);
            }
        } else if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            // Handle other data types or customize as needed
            cell.setCellValue(value.toString());
        }
    }

    private static Object getCellValueReport(Report report, TableColumn<Report, ?> column) {
        // Replace this with the actual method or property to get cell value based on TableColumn
        if (column.getText().equals("ReportID")) {
            return report.getReportID(); // Use the getter for the property value
        } else if (column.getText().equals("Username")) {
            return report.getReportName(); // Use the getter for the property value
        }
        else if (column.getText().equals("ReportInfo")) {
            return report.getReportInfo(); // Use the getter for the property value
        }else if (column.getText().equals("Date")) {
            // Convert the string date to a Date object
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.parse(report.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
                return null;  // Handle parsing error
            }
        } else if (column.getText().equals("Time")) {
            return report.getTime();  // Use the getter for the property value
        } else {
            return null; // Handle unknown column text
        }
    }

}
