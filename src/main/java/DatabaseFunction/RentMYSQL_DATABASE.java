package DatabaseFunction;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx_table_functions.CarImage;
import javafx_table_functions.CarImage2;
import Imagecell_function.ImageUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class RentMYSQL_DATABASE {
    private String MYSQL_URL = MYSQLDATABASE.getDatabaseURL();
    private String MYSQL_USERNAME = MYSQLDATABASE.getDatabaseUsername();
    private String MYSQL_PASSWORD = MYSQLDATABASE.getDatabasePassword();

    public void AddCar(String carname, String plate, int price, File storeImage, TableView<CarImage> CarView, ObservableList<CarImage> CarList, TextField CarName, TextField CarPlate, TextField CarPrice, Label CarFile) {
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(CarID) FROM rentedcars");
            int highestId = 0;
            if (resultSet.next()) {
                highestId = resultSet.getInt(1);
            }

            // Increment the highest ID value by 1 to get the new ID value.
            int newId = highestId + 1;
            String selectSQL = "INSERT INTO rentedcars (CarID, Carname, CarPlateNum, CarPrice, CarImage, ImageType) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectSQL)) {
                selectStatement.setInt(1, newId);
                selectStatement.setString(2, carname);
                selectStatement.setString(3, plate);
                selectStatement.setInt(4, price);

                // Set the image data
                FileInputStream fis = new FileInputStream(storeImage);
                selectStatement.setBinaryStream(5, fis, (int) storeImage.length());

                // Set the image type
                selectStatement.setString(6, getFileExtension(storeImage.getName()));


                int rows = selectStatement.executeUpdate();
                if (rows > 0) {

                    // Insert sales information into salestable
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Car Added Successfully");
                    alert.showAndWait();
                    Image carImage = convertFileToImage(storeImage);

                    // Add the new car to the TableView for available cars
                    CarList.add(new CarImage(newId, carname, plate, price, carImage));
                    CarView.setItems(CarList);

                    // Clear the text fields
                    CarName.clear();
                    CarPlate.clear();
                    CarPrice.clear();
                    CarFile.setText("No File Chosen");

                    System.runFinalization();
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private Image convertFileToImage(File storeImage) {
        return new Image(storeImage.toURI().toString());
    }

    // Helper method to get the file extension
    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1);
        }
        return ""; // or handle accordingly if no extension found
    }


    public void MoveTheRentCarToPending(String carname, String personrented, String dateRented, int pay, String dateReturn,
                                        String cashiername, TextField PersonPay, Label personLabel, Label DateRLabel,
                                        Label DateRRLabel, Label CashierLabel, Label Paylabel, Label DateLabel,
                                        ObservableList<CarImage2> RentedCars, TableView<CarImage2> CarView2, TextField SearchCarName,
                                        TextField PersonRentedName, TextField DateRented, TextField DateReturn,  TextField CashierName) {
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD)) {
            // SQL query to select the record based on carname
            String selectQuery = "SELECT * FROM rentedcars WHERE Carname = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, carname);

                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    // Check if a record was found
                    if (resultSet.next()) {
                        // Retrieve data from the sourceTable
                        int carId = resultSet.getInt("CarID");
                        String carPlateNum = resultSet.getString("CarPlateNum");
                        int carPrice = resultSet.getInt("CarPrice");
                        Blob carImageBlob = resultSet.getBlob("CarImage");
                        Image carImage = ImageUtils.convertBlobToImage(carImageBlob);
                        String imageType = resultSet.getString("ImageType");

                        // SQL query to insert into targetTable
                        String insertQuery = "INSERT INTO availablecar (Carname, CarPlateNum, CarPrice, CarImage, ImageType, PersonRentName, DateRented, DateReturn) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                            insertStatement.setString(1, carname);
                            insertStatement.setString(2, carPlateNum);
                            insertStatement.setInt(3, carPrice);

                            // Set BLOB data for insert
                            if (carImageBlob != null) {
                                insertStatement.setBlob(4, carImageBlob);
                            } else {
                                insertStatement.setNull(4, java.sql.Types.BLOB);
                            }

                            insertStatement.setString(5, imageType);

                            insertStatement.setString(6, personrented);

                            insertStatement.setString(7, dateRented);

                            insertStatement.setString(8, dateReturn);

                            // Execute the insert statement
                            int rowsInserted = insertStatement.executeUpdate();

                            if (rowsInserted > 0) {
                                // Retrieve the auto-generated key (CarID)
                                try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                                    if (generatedKeys.next()) {
                                        int newId = generatedKeys.getInt(1);

                                        // SQL query to delete the record from sourceTable
                                        String deleteQuery = "DELETE FROM rentedcars WHERE CarID = ?";
                                        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                                            deleteStatement.setInt(1, carId);

                                            // Execute the delete statement
                                            int rowsDeleted = deleteStatement.executeUpdate();

                                            if (rowsDeleted > 0) {
                                                // Optionally, update UI elements or refresh tables after the operation
                                                // For example, if CarView2 is your TableView for targetTable:
                                                RentedCars.add(new CarImage2(newId, carname, carPlateNum, carPrice, carImage, imageType, dateRented, dateReturn));

                                                // Insert sales information into salestable
                                                String insertSalesQuery = "INSERT INTO salestable (SaleID, PersonName, Date, PayedStatus, Amount, CashierName) VALUES (?, ?, ?, ?, ?, ?)";
                                                try (PreparedStatement insertSalesStatement = connection.prepareStatement(insertSalesQuery)) {
                                                    int newSalesId = newId; // Assuming you want to use the new CarID as the SaleID
                                                    insertSalesStatement.setInt(1, newSalesId); // SaleID
                                                    insertSalesStatement.setString(2, personrented); // Person name
                                                    insertSalesStatement.setDate(3, new java.sql.Date(System.currentTimeMillis())); // Current date
                                                    insertSalesStatement.setString(4, "Rent a car"); // You may need to adjust this based on your logic
                                                    insertSalesStatement.setInt(5, pay); // receive payment amount
                                                    insertSalesStatement.setString(6, cashiername); // Cashier name

                                                    // Execute the insert statement for sales
                                                    int salesRowsInserted = insertSalesStatement.executeUpdate();

                                                    if (salesRowsInserted > 0) {

                                                        // Clear the text fields and labels
                                                        CarView2.setItems(RentedCars);
                                                        personLabel.setText(personrented);
                                                        DateRLabel.setText(dateRented);
                                                        DateRRLabel.setText(dateReturn);
                                                        CashierLabel.setText(cashiername);
                                                        Paylabel.setText(String.valueOf(pay));
                                                        DateLabel.setText(String.valueOf(new java.sql.Date(System.currentTimeMillis())));

                                                        // Insert sales information into salestable for person
                                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                        alert.setTitle("Success");
                                                        alert.setHeaderText(null);
                                                        alert.setContentText("Car Moved Successfully");
                                                        alert.showAndWait();

                                                        // Clear the text fields
                                                        PersonPay.clear();
                                                        SearchCarName.clear();
                                                        PersonRentedName.clear();
                                                        DateRented.clear();
                                                        DateReturn.clear();
                                                        CashierName.clear();

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        // Handle the case where the record is not found in sourceTable
                        System.out.println("Car not found for carname: " + carname);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void MovetheCarBacktoRent(String carname, TableView<CarImage> CarView1, ObservableList<CarImage> CarList, TableView<CarImage> CarView){
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD)) {
            // SQL query to select the record based on carname
            String selectQuery = "SELECT * FROM availablecar WHERE Carname = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, carname);

                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    // Check if a record was found
                    if (resultSet.next()) {
                        // Retrieve data from the sourceTable
                        int carId = resultSet.getInt("CarID");
                        String carPlateNum = resultSet.getString("CarPlateNum");
                        int carPrice = resultSet.getInt("CarPrice");
                        Blob carImageBlob = resultSet.getBlob("CarImage");
                        Image carImage = ImageUtils.convertBlobToImage(carImageBlob);
                        String imageType = resultSet.getString("ImageType");

                        // SQL query to insert into targetTable
                        Statement statement = connection.createStatement();
                        ResultSet resultSet1 = statement.executeQuery("SELECT MAX(CarID) FROM rentedcars");
                        int highestId = 0;
                        if (resultSet1.next()) {
                            highestId = resultSet1.getInt(1);
                        }
                        int newId = highestId + 1;
                        String insertQuery = "INSERT INTO rentedcars (CarID, Carname, CarPlateNum, CarPrice, CarImage, ImageType) VALUES (?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                            insertStatement.setInt(1, newId);
                            insertStatement.setString(2, carname);
                            insertStatement.setString(3, carPlateNum);
                            insertStatement.setInt(4, carPrice);

                            // Set BLOB data for insert
                            if (carImageBlob != null) {
                                insertStatement.setBlob(5, carImageBlob);
                            } else {
                                insertStatement.setNull(5, java.sql.Types.BLOB);
                            }

                            insertStatement.setString(6, imageType);



                            // Execute the insert statement
                            int rowsInserted = insertStatement.executeUpdate();

                            if (rowsInserted > 0) {
                                // SQL query to delete the record from sourceTable
                                String deleteQuery = "DELETE FROM availablecar WHERE CarID = ?";
                                try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                                    deleteStatement.setInt(1, carId);

                                    // Execute the delete statement
                                    int rowsDeleted = deleteStatement.executeUpdate();

                                    if (rowsDeleted > 0) {
                                        // Optionally, update UI elements or refresh tables after the operation
                                        // For example, if CarView2 is your TableView for targetTable:
                                        CarList.add(new CarImage(newId, carname, carPlateNum, carPrice, carImage));
                                        CarView1.setItems(CarList);
                                        CarView.setItems(CarList);
                                        CarView.refresh();
                                        CarView1.refresh();

                                        // Insert sales information into salestable
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Success");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Car Moved Successfully");


                                    }
                                }
                            }
                        }
                    } else {
                        // Handle the case where the record is not found in sourceTable
                        System.out.println("Car not found for carname: " + carname);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}