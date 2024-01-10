package com.example.car_rental_reservation_system;

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

    public String MYSQL_URL = "jdbc:mysql://localhost:3306/car_rental_resevation_db";
    public String MYSQL_USERNAME = "root";

    public String MYSQL_PASSWORD = "";
    private Stage CarStage;

    public void Login(ActionEvent event, String username, String password, TextField usernameField, PasswordField passwordField) {
        try (Connection con = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD)) {
            String sql = "SELECT username, password, salt FROM caruser WHERE username=?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Blob saltBlob = resultSet.getBlob("salt");
                        int saltLength = (int) saltBlob.length();
                        byte[] saltBytes = saltBlob.getBytes(1, saltLength);
                        String hashedPassword = resultSet.getString("password");

                        if (validatePassword(password, saltBytes, hashedPassword)) {
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
            return storedHashedPassword.equals(computedHashedPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String hashPassword(String pass, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 10000;
        int keyLength = 256;

        KeySpec keySpec = new PBEKeySpec(pass.toCharArray(), salt, iterations, keyLength);

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hashedPassword = factory.generateSecret(keySpec).getEncoded();

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedPassword) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw e;
        }
    }

    private void LoginSuccess(ActionEvent event, String username, String status) {

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
        Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
        String sql = "SELECT status FROM caruser WHERE username=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String status = resultSet.getString("status");
        System.out.println(status);


        LoginSuccess(event, username, status);
    }

    private void loadCarystem(String username, String status ) {
        try {
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
