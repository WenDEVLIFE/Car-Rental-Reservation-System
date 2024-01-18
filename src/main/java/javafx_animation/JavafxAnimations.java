package javafx_animation;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx_table_functions.*;

import java.util.Arrays;

public class JavafxAnimations {

    private void applyFadeTransition(Node node) {

        // FadeTransition(Duration.seconds(1), node) : 1 second to fade the node
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(3);
        fadeTransition.play();
    }

    private void applyScaleTransition(Node node) {

         // ScaleTransition(Duration.seconds(1), node) : 1 second to scale the node
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), node);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    public void applyFadeAndScaleTransitions(Node... nodes) {

         // for each node in nodes
        for (Node node : nodes) {
            node.setScaleX(0.1);
            node.setScaleY(0.1);
            applyScaleTransition(node);
            applyFadeTransition(node);
        }
    }
    public void loadfx_animation(Pane... panes) {

        // for each node in nodes
        for (Pane pane : panes) {
            pane.setScaleX(0.1);
            pane.setScaleY(0.1);
            applyScaleTransition(pane);


        }
    }
    public void fade_Calendar_Activity(Pane... panes) {

        // for each node in nodes
        applyFadeAndScaleTransitions(panes);
    }

    public void fade_Appointment(Pane... panes) {

        // for each node in nodes
        applyFadeAndScaleTransitions(panes);
    }

    public void FaceRentCar(TableView <CarImage> CarView, Pane... panes) {

         // for each node in nodes
        applyFadeAndScaleTransitions(panes);
        applyFadeTransition(CarView);

    }

    public void AvailableCar(TableView <CarImage> CarView1, Pane... panes) {

        // for each node in nodes
        Arrays.stream(panes).forEach(pane -> {
            pane.setScaleX(0.1);
            pane.setScaleY(0.1);
            applyScaleTransition(pane);
        });

        applyFadeTransition(CarView1);
    }

    public void PendingCar(TableView<CarImage2> CarView2, Pane... panes) {

        // for each node in nodes
        applyFadeAndScaleTransitions(panes);
        applyFadeTransition(CarView2);
    }
    public void fade_admin(TableView <UserTable> UserView ,Pane... panes) {

        // for each in nodes
        applyFadeAndScaleTransitions(panes);
        applyFadeTransition(UserView);
    }

    public void Sales(TableView<SalesTable> SalesView, Pane[] panes, Button... buttons) {

        // for each in panes
        for (Pane pane : panes) {
            pane.setScaleX(0.1);
            pane.setScaleY(0.1);
            applyScaleTransition(pane);

        }

        // for each in nodes (SalesView)
        applyScaleTransition(SalesView);


        // fpr each in buttons
        for (Button button : buttons) {
            button.setScaleX(0.1);
            button.setScaleY(0.1);
            applyScaleTransition(button);
        }
    }

    public void Report(TableView<Report> reportView, Pane  [] panes , Button... buttons ) {

            // for each in panes
        Arrays.stream(panes).forEach(pane -> {
            pane.setScaleX(0.1);
            pane.setScaleY(0.1);
            applyScaleTransition(pane);
        });

            // for each in nodes (reportView)
            applyScaleTransition(reportView);

            // for each in buttons
            for (Button button : buttons) {
                button.setScaleX(0.1);
                button.setScaleY(0.1);
                applyScaleTransition(button);
            }
    }
}
