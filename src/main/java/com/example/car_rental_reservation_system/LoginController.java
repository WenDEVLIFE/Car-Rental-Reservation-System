package com.example.car_rental_reservation_system;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class LoginController {

    @FXML
    void LoginAction (javafx.event.ActionEvent event) {
        System.out.println("Login button pressed");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText("Login button pressed");
        alert.showAndWait();

    }
}