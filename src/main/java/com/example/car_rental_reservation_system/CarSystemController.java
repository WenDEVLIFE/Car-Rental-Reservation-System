package com.example.car_rental_reservation_system;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class CarSystemController {

    private Stage CarStage_Receiver;

    @FXML
    private Button MinimizeButton;

    @FXML
    private Button CloseButton;

    // This will be connected on the car system fxml file

    public void Setstage(Stage CarStage){

        this.CarStage_Receiver = CarStage;
    }

    @FXML
    void Minus_Action (ActionEvent event){
        CarStage_Receiver.setIconified(true);
    }

    @FXML
    void Close_Action (ActionEvent event){
        Platform.exit();
    }
    public void initialize (){
        Tooltip tooltip = new Tooltip("Minimize");
        MinimizeButton.setTooltip(tooltip);

        Tooltip tooltip2 = new Tooltip("Close");
        CloseButton.setTooltip(tooltip2);

        System.out.println(CarStage_Receiver+"has a value");
    }


}
