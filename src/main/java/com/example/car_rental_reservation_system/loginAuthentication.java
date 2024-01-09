package com.example.car_rental_reservation_system;

import com.example.car_rental_reservation_system.CarSystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class loginAuthentication {


    private Stage CarStage;

    public void Login(ActionEvent event) {

        LoginSuccess(event);
    }

    private void LoginSuccess(ActionEvent event) {

        System.out.println("Login button pressed");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText("Login button pressed");
        alert.showAndWait();

        Node sourceNode = (Node) event.getSource();
        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
        currentStage.close();
        loadCarystem();

    }

    private void loadCarystem() {
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
}
