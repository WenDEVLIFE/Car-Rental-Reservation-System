package javafx_animation;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafxf_functions.UserTable;

public class JavafxAnimations {

    public void loadfx_animation(Pane CarPanel, Pane AdminPane, Pane dashboardPanel, Pane DashboardTitle, Pane DashBoardBackground, Pane StaffPane, Pane ReportPane, Pane AvailableCarsPane) {
        dashboardPanel.setScaleX(0.1);
        dashboardPanel.setScaleY(0.1);

        CarPanel.setScaleX(0.1);
        CarPanel.setScaleY(0.1);

        AdminPane.setScaleX(0.1);
        AdminPane.setScaleY(0.1);

        DashboardTitle.setScaleX(0.1);
        DashboardTitle.setScaleY(0.1);

        DashBoardBackground.setScaleX(0.1);
        DashBoardBackground.setScaleY(0.1);

        StaffPane.setScaleX(0.1);
        StaffPane.setScaleY(0.1);

        ReportPane.setScaleX(0.1);
        ReportPane.setScaleY(0.1);

        AvailableCarsPane.setScaleX(0.1);
        AvailableCarsPane.setScaleY(0.1);


        // Create scale transitions
        ScaleTransition dashboardScaleTransition = new ScaleTransition(Duration.seconds(1), dashboardPanel);
        dashboardScaleTransition.setToX(1);
        dashboardScaleTransition.setToY(1);

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(2), dashboardPanel);
        rotateTransition.setByAngle(360);
        rotateTransition.play();

        ScaleTransition carScaleTransition = new ScaleTransition(Duration.seconds(1), CarPanel);
        carScaleTransition.setToX(1);
        carScaleTransition.setToY(1);

        RotateTransition rotateTransition2 = new RotateTransition(Duration.seconds(2), CarPanel);
        rotateTransition2.setByAngle(360);
        rotateTransition2.play();

        ScaleTransition adminScaleTransition = new ScaleTransition(Duration.seconds(1), AdminPane);
        adminScaleTransition.setToX(1);
        adminScaleTransition.setToY(1);

        RotateTransition rotateTransition3 = new RotateTransition(Duration.seconds(2), AdminPane);
        rotateTransition3.setByAngle(360);
        rotateTransition3.play();

        ScaleTransition dashboardTitleScaleTransition = new ScaleTransition(Duration.seconds(1), DashboardTitle);
        dashboardTitleScaleTransition.setToX(1);
        dashboardTitleScaleTransition.setToY(1);

        RotateTransition rotateTransition4 = new RotateTransition(Duration.seconds(2), DashboardTitle);
        rotateTransition4.setByAngle(360);
        rotateTransition4.play();

        ScaleTransition dashboardBackgroundScaleTransition = new ScaleTransition(Duration.seconds(1), DashBoardBackground);
        dashboardBackgroundScaleTransition.setToX(1);
        dashboardBackgroundScaleTransition.setToY(1);

        RotateTransition rotateTransition5 = new RotateTransition(Duration.seconds(2), DashBoardBackground);
        rotateTransition5.setByAngle(360);
        rotateTransition5.play();

        ScaleTransition staffScaleTransition = new ScaleTransition(Duration.seconds(1), StaffPane);
        staffScaleTransition.setToX(1);
        staffScaleTransition.setToY(1);

        RotateTransition rotateTransition6 = new RotateTransition(Duration.seconds(2), StaffPane);
        rotateTransition6.setByAngle(360);
        rotateTransition6.play();

        ScaleTransition reportScaleTransition = new ScaleTransition(Duration.seconds(1), ReportPane);
        reportScaleTransition.setToX(1);
        reportScaleTransition.setToY(1);

        RotateTransition rotateTransition7 = new RotateTransition(Duration.seconds(2), ReportPane);
        rotateTransition7.setByAngle(360);
        rotateTransition7.play();

        ScaleTransition availableCarsScaleTransition = new ScaleTransition(Duration.seconds(1), AvailableCarsPane);
        availableCarsScaleTransition.setToX(1);
        availableCarsScaleTransition.setToY(1);

        RotateTransition rotateTransition8 = new RotateTransition(Duration.seconds(2), AvailableCarsPane);
        rotateTransition8.setByAngle(360);
        rotateTransition8.play();




        // Play scale transitions
        dashboardScaleTransition.play();
        carScaleTransition.play();
        adminScaleTransition.play();
        dashboardTitleScaleTransition.play();
        dashboardBackgroundScaleTransition.play();
        staffScaleTransition.play();
        reportScaleTransition.play();
        availableCarsScaleTransition.play();

    }

    public void fade_animations(Pane UserPane, TableView<UserTable> UserView, Pane AdminTitle1, Pane AdminBackground) {
        // Create fade transitions
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), UserPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1), UserView);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);
        fadeTransition2.play();

        FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(1), AdminTitle1);
        fadeTransition3.setFromValue(0);
        fadeTransition3.setToValue(1);
        fadeTransition3.play();

        FadeTransition fadeTransition4 = new FadeTransition(Duration.seconds(1), AdminBackground);
        fadeTransition4.setFromValue(0);
        fadeTransition4.setToValue(1);
        fadeTransition4.play();
    }

}
