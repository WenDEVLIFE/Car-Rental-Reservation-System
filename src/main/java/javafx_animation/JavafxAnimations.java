package javafx_animation;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx_table_functions.CarImage;
import javafx_table_functions.CarImage2;
import javafx_table_functions.SalesTable;
import javafx_table_functions.UserTable;

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

        System.runFinalization();

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

        System.runFinalization();
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


        System.runFinalization();

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

        System.runFinalization();



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

        System.runFinalization();
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

        System.runFinalization();
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

        System.runFinalization();
    }

    public void PendingCar(TableView<CarImage2> carView2, Pane rentedCarPane, Pane rentedCarBG) {
        // Create fade transitions
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), carView2);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1), rentedCarPane);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);
        fadeTransition2.play();

        FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(1), rentedCarBG);
        fadeTransition3.setFromValue(0);
        fadeTransition3.setToValue(1);
        fadeTransition3.play();

        System.runFinalization();
    }


    public void Sales(TableView<SalesTable> SalesView, Pane SalesTPane, Pane SalesMPane, Pane SalesBackground, Pane SalesNavPane, Button PrintSales, Button RefreshSales) {
        // set scale
        SalesTPane.setScaleX(0.1);
        SalesView.setScaleY(0.1);
        SalesMPane.setScaleX(0.1);
        RefreshSales.setScaleY(0.1);
        SalesBackground.setScaleX(0.1);
        PrintSales.setScaleY(0.1);
        SalesNavPane.setScaleX(0.1);

        // Create scale transitions
        ScaleTransition salesScaleTransition = new ScaleTransition(Duration.seconds(1), SalesTPane);
        salesScaleTransition.setToX(1);
        salesScaleTransition.setToY(1);

        ScaleTransition salesViewScaleTransition = new ScaleTransition(Duration.seconds(1), SalesView);
        salesViewScaleTransition.setToX(1);
        salesViewScaleTransition.setToY(1);

        ScaleTransition salesMPaneScaleTransition = new ScaleTransition(Duration.seconds(1), SalesMPane);
        salesMPaneScaleTransition.setToX(1);
        salesMPaneScaleTransition.setToY(1);

        ScaleTransition salesBackgroundScaleTransition = new ScaleTransition(Duration.seconds(1), SalesBackground);
        salesBackgroundScaleTransition.setToX(1);
        salesBackgroundScaleTransition.setToY(1);

        ScaleTransition salesNavPaneScaleTransition = new ScaleTransition(Duration.seconds(1), SalesNavPane);
        salesNavPaneScaleTransition.setToX(1);
        salesNavPaneScaleTransition.setToY(1);

        ScaleTransition salesPrintScaleTransition = new ScaleTransition(Duration.seconds(1), PrintSales);
        salesPrintScaleTransition.setToX(1);
        salesPrintScaleTransition.setToY(1);

        ScaleTransition salesRefreshScaleTransition = new ScaleTransition(Duration.seconds(1), RefreshSales);
        salesRefreshScaleTransition.setToX(1);
        salesRefreshScaleTransition.setToY(1);

        // Play scale transitions
        salesScaleTransition.play();
        salesViewScaleTransition.play();
        salesMPaneScaleTransition.play();
        salesBackgroundScaleTransition.play();
        salesNavPaneScaleTransition.play();
        salesPrintScaleTransition.play();
        salesRefreshScaleTransition.play();

        System.runFinalization();


    }
}
