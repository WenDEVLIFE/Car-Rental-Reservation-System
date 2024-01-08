package com.example.car_rental_reservation_system;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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

    public void setStage(Stage stage){
        this.stage = stage;

    }

    @FXML
    void LoginAction(javafx.event.ActionEvent event) {


        loginAuthentication login = new loginAuthentication();
        login.Login(event);


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
}