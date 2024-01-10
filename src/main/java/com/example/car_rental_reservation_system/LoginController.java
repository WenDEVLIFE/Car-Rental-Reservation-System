package com.example.car_rental_reservation_system;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    private Stage stage;

    @FXML
    private TextField usernamefield;

    @FXML
    private PasswordField passwordfield;


    @FXML
    private Button MinimizeButton;

    @FXML
    private Button CloseButton;

    @FXML
    private CheckBox checkPasswordBox;


    public void setStage(Stage stage){
        this.stage = stage;

    }

    @FXML
    void LoginAction(javafx.event.ActionEvent event) {

        String username = usernamefield.getText();
        String password = passwordfield.getText();

        if(username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your username and password");
            alert.showAndWait();
        }
        else {
            // call the loginAuthenticator class
            loginAuthentication login = new loginAuthentication();
            login.Login(event, username, password,usernamefield, passwordfield );
        }



    }

    @FXML
    void Closefunction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void MinusFunction(ActionEvent event) {
        stage.setIconified(true);
    }
    public void initialize(){
        Tooltip tooltip = new Tooltip("Minimize");
        MinimizeButton.setTooltip(tooltip);

        Tooltip tooltip2 = new Tooltip("Close");
        CloseButton.setTooltip(tooltip2);

        System.out.println("LoginController has been initialized");

    }

    @FXML
    void checkpassword (ActionEvent event) {
        System.out.println("Check password");

        if (checkPasswordBox.isSelected()) {
            // If checkbox is selected, show the password
            passwordfield.setPromptText(passwordfield.getText());
            passwordfield.clear();

        } else {
            // If checkbox is not selected, hide the password
            passwordfield.setText(passwordfield.getPromptText());
            passwordfield.setPromptText("Password");

        }

    }
}