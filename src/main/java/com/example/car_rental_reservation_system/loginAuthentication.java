package com.example.car_rental_reservation_system;

import DatabaseFunction.MYSQLDATABASE;
import com.example.car_rental_reservation_system.CarSystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.concurrent.atomic.AtomicReference;

public class loginAuthentication {

    // This is the connection to the database without this, you cannot connect to the database
    private final String MYSQL_URL = MYSQLDATABASE.getDatabaseURL();
    private final String MYSQL_USERNAME = MYSQLDATABASE.getDatabaseUsername();
    private final String MYSQL_PASSWORD = MYSQLDATABASE.getDatabasePassword();
    private Stage CarStage;

    public void Login(ActionEvent event, String username, String password, TextField usernameField, PasswordField passwordField) {
        // Code for login then check the username and password if it is correct
        try (Connection con = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD)) {

            // This will find the username and password in the database then check if it is correct
            String sql = "SELECT username, password, salt FROM caruser WHERE username=?";

            // Then proceed to the statement to check the username and password
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {

                        // This will get the salt and hashed password from the database
                        Blob saltBlob = resultSet.getBlob("salt");
                        int saltLength = (int) saltBlob.length();
                        byte[] saltBytes = saltBlob.getBytes(1, saltLength);
                        String hashedPassword = resultSet.getString("password");

                        // This will check the password if it is correct
                        if (validatePassword(password, saltBytes, hashedPassword)) {

                            /* This will check the status of the user if it is admin or user
                            * also call the three methods search status, clear textfield and passwordfield*/

                            searchstatus( username,event);
                            usernameField.clear();
                            passwordField.clear();
                        } else {
                            showErrorAlert("Incorrect username or password");
                        }
                    } else {
                        showErrorAlert("Incorrect username or password");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean validatePassword(String enteredPassword, byte[] storedSalt, String storedHashedPassword) {
        try {
            // Hash the entered password with the stored salt and compare with stored hash
            String computedHashedPassword = hashPassword(enteredPassword, storedSalt);

            // Then it will return the compute hashed password
            return storedHashedPassword.equals(computedHashedPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String hashPassword(String pass, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // This will hash the password using PBKDF2 algorithm
        int iterations = 10000;
        int keyLength = 256;

          /* This will hash the password using PBKDF2 algorithma and used of this will read the interations and key length */
        KeySpec keySpec = new PBEKeySpec(pass.toCharArray(), salt, iterations, keyLength);

        try {
            // Then proceed here to get the Instance of the SecretKeyFactory assigned to PBKDF2WithHmacSHA256
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            // This will get the hashed password
            byte[] hashedPassword = factory.generateSecret(keySpec).getEncoded();

            // This will convert the hashed password to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedPassword) {
                hexString.append(String.format("%02x", b));
            }

            // Then it will return the hex string
            return hexString.toString();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw e;
        }
    }

    private void LoginSuccess(ActionEvent event, String username, String status) {

        // Display the login success message
        System.out.println("Login");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText("Login Success");
        alert.showAndWait();

        Node sourceNode = (Node) event.getSource();
        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
        currentStage.close();
        loadCarystem( username, status);

    }
    private void searchstatus(String username, ActionEvent event) throws SQLException {
         // Connect to the database to find the username status
        Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);

        // This will find the status of the user
        String sql = "SELECT status FROM caruser WHERE username=?";


        // Then proceed to the statement to find the status of the user
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String status = resultSet.getString("status");
        System.out.println(status);

      // This will call the login success method
        LoginSuccess(event, username, status);
    }
    public void InsertReport(String username){
        // This will insert the report to the database
        try (Connection con = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD)) {

            // This will insert the report to the database
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(ReportID) FROM reporttable");
            int highestId = 0;
            if (resultSet.next()) {
                highestId = resultSet.getInt(1);
            }

            // Increment the highest ID value by 1 to get the new ID value.
            int newId = highestId + 1;

            String sql = "INSERT INTO reporttable (ReportID, Username, ReportInfo, Date, Time) VALUES (?, ?, ?, ?, ?)";

            // Then proceed to the statement to insert the report
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, newId);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, "Login");
                preparedStatement.setString(4, java.time.LocalDate.now().toString());
                preparedStatement.setString(5, java.time.LocalTime.now().toString());

                int RowsAffected = preparedStatement.executeUpdate();
                if (RowsAffected > 0) {
                    System.out.println("Report has been inserted");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCarystem(String username, String status ) {
        try {
            InsertReport(username);
            // This will load the Car rental
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CarRentalSystemUi.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Image icon = new Image(getClass().getResourceAsStream("icons/CarLogo2.png"));

            CarStage = new Stage();
            CarStage.getIcons().add(icon);
            CarStage.setTitle("Car rental reservation system");
            CarStage.setScene(scene);
            CarStage.setResizable(false);

            // This will load the Car system
            System.out.println("The carstage value has been sent");
            CarSystemController carstage = fxmlLoader.getController();
            carstage.Setstage(CarStage);

            CarSystemController carSystemController = fxmlLoader.getController();
            carSystemController.setUsername_And_Status(username , status);

            // Set stage style before making it visible
            CarStage.initStyle(StageStyle.UNDECORATED);

            CarStage.centerOnScreen();
            CarStage.show();

            if (CarStage == null) {
                CarStage = new Stage();


                System.runFinalization();
            }

            AtomicReference<Double> xOffset = new AtomicReference<>((double) 0);
            AtomicReference<Double> yOffset = new AtomicReference<>((double) 0);

            // Set up mouse event handlers for dragging the stage around (continued)
            scene.setOnMousePressed(event -> {
                xOffset.set(event.getSceneX());
                yOffset.set(event.getSceneY());
            });

            // Set up mouse event handlers for dragging the stage around (continued)

            scene.setOnMouseDragged(event -> {
                CarStage.setX(event.getScreenX() - xOffset.get());
                CarStage.setY(event.getScreenY() - yOffset.get());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void showErrorAlert(String message) {
        // This an error message if the username or password is incorrect
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
