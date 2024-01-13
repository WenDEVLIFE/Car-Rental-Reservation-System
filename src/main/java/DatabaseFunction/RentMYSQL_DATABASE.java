package DatabaseFunction;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafxf_functions.CarImage;

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
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Car Added Successfully");
                    alert.showAndWait();
                    Image carImage = convertFileToImage(storeImage);
                    
                    CarList.add(new CarImage(newId, carname, plate, price, carImage));
                    CarView.setItems(CarList);

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
    public void MoveTheRentCarToPending(){
        // Code for adding user to database

    }


}
