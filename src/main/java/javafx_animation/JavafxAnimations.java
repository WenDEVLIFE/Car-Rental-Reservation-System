package javafx_animation;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafxf_functions.CarImage;
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

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), dashboardPanel);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        ScaleTransition carScaleTransition = new ScaleTransition(Duration.seconds(1), CarPanel);
        carScaleTransition.setToX(1);
        carScaleTransition.setToY(1);

        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1), CarPanel);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);

        ScaleTransition adminScaleTransition = new ScaleTransition(Duration.seconds(1), AdminPane);
        adminScaleTransition.setToX(1);
        adminScaleTransition.setToY(1);

        FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(1), AdminPane);
        fadeTransition3.setFromValue(0);
        fadeTransition3.setToValue(1);

        ScaleTransition dashboardTitleScaleTransition = new ScaleTransition(Duration.seconds(1), DashboardTitle);
        dashboardTitleScaleTransition.setToX(1);
        dashboardTitleScaleTransition.setToY(1);

        FadeTransition fadeTransition4 = new FadeTransition(Duration.seconds(1), DashboardTitle);
        fadeTransition4.setFromValue(0);
        fadeTransition4.setToValue(1);

        ScaleTransition dashboardBackgroundScaleTransition = new ScaleTransition(Duration.seconds(1), DashBoardBackground);
        dashboardBackgroundScaleTransition.setToX(1);
        dashboardBackgroundScaleTransition.setToY(1);

        FadeTransition fadeTransition5 = new FadeTransition(Duration.seconds(1), DashBoardBackground);
        fadeTransition5.setFromValue(0);
        fadeTransition5.setToValue(1);

        ScaleTransition staffScaleTransition = new ScaleTransition(Duration.seconds(1), StaffPane);
        staffScaleTransition.setToX(1);
        staffScaleTransition.setToY(1);

        FadeTransition fadeTransition6 = new FadeTransition(Duration.seconds(1), StaffPane);
        fadeTransition6.setFromValue(0);
        fadeTransition6.setToValue(1);

        ScaleTransition reportScaleTransition = new ScaleTransition(Duration.seconds(1), ReportPane);
        reportScaleTransition.setToX(1);
        reportScaleTransition.setToY(1);

        FadeTransition fadeTransition7 = new FadeTransition(Duration.seconds(1), ReportPane);
        fadeTransition7.setFromValue(0);
        fadeTransition7.setToValue(1);

        ScaleTransition availableCarsScaleTransition = new ScaleTransition(Duration.seconds(1), AvailableCarsPane);
        availableCarsScaleTransition.setToX(1);
        availableCarsScaleTransition.setToY(1);

        FadeTransition fadeTransition8 = new FadeTransition(Duration.seconds(1), AvailableCarsPane);
        fadeTransition8.setFromValue(0);
        fadeTransition8.setToValue(1);







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

    public void fade_calendars(Pane calendarPanel, Pane calendarpanel, FlowPane calendar, TabPane dashboardTabPane) {

        // Create fade transitions
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), calendarPanel);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1), calendarpanel);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);
        fadeTransition2.play();

        FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(1), calendar);
        fadeTransition3.setFromValue(0);
        fadeTransition3.setToValue(1);
        fadeTransition3.play();

        FadeTransition fadeTransition4 = new FadeTransition(Duration.seconds(1), dashboardTabPane);
        fadeTransition4.setFromValue(0);
        fadeTransition4.setToValue(1);
        fadeTransition4.play();




    }


    public void fade_Calendar_Activity(Pane CalendarDisplayPane, Pane CalendarTitlePane) {
        // Create fade transitions
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), CalendarDisplayPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1), CalendarTitlePane);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);
        fadeTransition2.play();



    }

    public void fade_Appointment(Pane AppointmentPane, Pane AppointmentInputPane, Pane AppointmentBackground) {
        // Create fade transitions
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), AppointmentPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1), AppointmentInputPane);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);
        fadeTransition2.play();

        FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(1), AppointmentBackground);
        fadeTransition3.setFromValue(0);
        fadeTransition3.setToValue(1);
        fadeTransition3.play();
    }


    public void FaceRentCar(Pane CarPane, Pane AddRentCarPaneBG, Pane AddRentCarPanerPane, TableView<CarImage> CarView) {

        // Create fade transitions
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), CarPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1), AddRentCarPaneBG);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);
        fadeTransition2.play();

        FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(1), AddRentCarPanerPane);
        fadeTransition3.setFromValue(0);
        fadeTransition3.setToValue(1);
        fadeTransition3.play();

        FadeTransition fadeTransition4 = new FadeTransition(Duration.seconds(1), CarView);
        fadeTransition4.setFromValue(0);
        fadeTransition4.setToValue(1);
        fadeTransition4.play();
    }

    public void AvailableCar(Pane AvailableCarPane, Pane AvaiLableCarBG, TableView<CarImage> CarView1) {
        // Create fade transitions
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), AvailableCarPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1), AvaiLableCarBG);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);
        fadeTransition2.play();

        FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(1), CarView1);
        fadeTransition3.setFromValue(0);
        fadeTransition3.setToValue(1);
        fadeTransition3.play();
    }
}
