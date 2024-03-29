package javafxf_functions;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class TabActions {


    // This method is used to switch between tabs
    public void DashboardActions(TabPane DashboardTabPane, Tab DashboardTab) {
        // This method is used to switch between tabs
        DashboardTabPane.getSelectionModel().select(DashboardTab);
    }

    public void CreateUserActions(TabPane DashboardTabPane, Tab AdminTab) {
        // This method is used to switch between tabs
        DashboardTabPane.getSelectionModel().select(AdminTab);
    }

    public void Calendar(Tab CalendarTab, TabPane DashboardTabPane) {
        // This method is used to switch between tabs
        DashboardTabPane.getSelectionModel().select(CalendarTab);
    }
    public void GoTOAppointment(TabPane DashboardTabPane, Tab CalendarInputTab) {
        // This method is used to switch between tabs
        DashboardTabPane.getSelectionModel().select(CalendarInputTab);
    }

    public void CreateAppointmentActions(Tab AppointmentTab, TabPane DashboardTabPane) {
        // This method is used to switch between tabs
        DashboardTabPane.getSelectionModel().select(AppointmentTab);
    }

    public void GoToAddCar(TabPane DashboardTabPane, Tab AddRentCarTab) {
        // This method is used to switch between tabs
        DashboardTabPane.getSelectionModel().select(AddRentCarTab);

    }

    public void GoToAvailCars(TabPane DashboardTabPane, Tab AvailableRentedCarTab) {
        // This method is used to switch between tabs
        DashboardTabPane.getSelectionModel().select(AvailableRentedCarTab);
    }

    public void PendingCars(TabPane DashboardTabPane, Tab PendingCarTab) {
        // This method is used to switch between tabs
        DashboardTabPane.getSelectionModel().select(PendingCarTab);
    }

    public void RentCar(TabPane DashboardTabPane, Tab RentCarTab) {
        // This method is used to switch between tabs
        DashboardTabPane.getSelectionModel().select(RentCarTab);
    }

    public void GoToSales(TabPane DashboardTabPane, Tab SalesTab) {
        // This method is used to switch between tabs
        DashboardTabPane.getSelectionModel().select(SalesTab);
    }

    public void GoToReport(TabPane DashboardTabPane, Tab ReportTAB) {
        // This method is used to switch between tabs
        DashboardTabPane.getSelectionModel().select(ReportTAB);

    }

    public void GoToPersonal(TabPane DashboardTabPane, Tab PersonalTab) {
        // This method is used to switch between tabs
        DashboardTabPane.getSelectionModel().select(PersonalTab);
    }

    public void GoToChangePass(TabPane DashboardTabPane, Tab ChangePassTab) {
        // This method is used to switch between tabs
        DashboardTabPane.getSelectionModel().select(ChangePassTab);
    }

    public void GoToChangeUser(TabPane DashboardTabPane, Tab ChangeUserTab) {
        // This method is used to switch between tabs
        DashboardTabPane.getSelectionModel().select(ChangeUserTab);
    }
}
