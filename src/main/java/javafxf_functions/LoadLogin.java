package javafxf_functions;

import com.example.car_rental_reservation_system.LoginController;
import com.example.car_rental_reservation_system.LoginSystem;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class LoadLogin {
    public void loadLogin() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginSystem.class.getResource("hello-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("/com/example/car_rental_reservation_system/icons/CarLogo2.png"));
            Stage stage = new Stage();
            stage.getIcons().add(icon);
            stage.setTitle("Car rental reservation system");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initStyle(StageStyle.UNDECORATED);  // Set the stage style here
            stage.show();

            // Set up mouse event handlers for dragging
            AtomicReference<Double> xOffset = new AtomicReference<>((double) 0);
            AtomicReference<Double> yOffset = new AtomicReference<>((double) 0);

            // Set up mouse event handlers for dragging the stage around (continued)
            scene.setOnMousePressed(event -> {
                xOffset.set(event.getSceneX());
                yOffset.set(event.getSceneY());
            });

            // Set up mouse event handlers for dragging the stage around (continued)
            scene.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - xOffset.get());
                stage.setY(event.getScreenY() - yOffset.get());
            });

            LoginController loginController = fxmlLoader.getController();
            loginController.setStage(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
