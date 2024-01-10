package com.example.car_rental_reservation_system;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx_animation.JavafxAnimations;
import javafxf_functions.*;

import java.io.IOException;
import java.time.ZonedDateTime;

public class CarSystemController {

    private Stage CarStage_Receiver;

    @FXML
    private Button MinimizeButton;

    @FXML
    private Button CloseButton;

    @FXML
    private AnchorPane dashboardRoot;

    @FXML
    private FlowPane calendar;

    @FXML
    private Pane dashboardPanel;

    @FXML
    private Pane CarPanel;

    @FXML
    private Pane AdminPane;

    @FXML
    private Pane DashboardTitle;

    @FXML
    private Pane DashBoardBackground;

    @FXML
    private Pane StaffPane;

    @FXML
    private Pane ReportPane;

    @FXML
    private Pane AvailableCarsPane;

    @FXML
    private Pane UserPane;

    @FXML
    private Pane AdminTitle1;

    @FXML
    private Pane AdminBackground;

    @FXML
    private Pane CalendarPanel;

    @FXML
    private Pane calendarpanel;

    @FXML
    private TabPane DashboardTabPane;

    @FXML
    private Tab DashboardTab;

    @FXML
    private Tab AdminTab;

    @FXML
    private Tab CalendarTab;

    @FXML
    private ComboBox <String> StatusUser;

    @FXML
    private TextField UsernameField;

    @FXML
    private PasswordField PasswordfieldText;

    @FXML
    private PasswordField ConfirmpasswordFieldText;

    @FXML
    private CheckBox checkPasswordBox;


    @FXML
    private Label setstatus1;

    @FXML
    private Label Setusername1;

    @FXML
    private Label setstatus2;

    @FXML
    private Label Setusername2;

    @FXML
    private Label setstatus3;

    @FXML
    private Label Setusername3;

    @FXML
    private Text year;

    @FXML
    private Text month;


    public ZonedDateTime dateFocus;
      public ZonedDateTime today;


    @FXML
    private TableView<UserTable> UserView;

    ObservableList <UserTable>  UserList= FXCollections.observableArrayList();




    // This will be connected on the car system fxml file

    public void Setstage(Stage CarStage){

        this.CarStage_Receiver = CarStage;
    }
    public void setUsername_And_Status(String username, String status){
        System.out.println(username);
        System.out.println(status);

        // This will set the username and status on all tabs and panes
        Setusername1.setText(username);
        setstatus1.setText(status);
        Setusername2.setText(username);
        setstatus2.setText(status);
        Setusername3.setText(username);
        setstatus3.setText(status);

    }

    @FXML
    void Minus_Action (ActionEvent event){
        CarStage_Receiver.setIconified(true);
    }

    @FXML
    void Close_Action (ActionEvent event){
        Platform.exit();
    }

    @FXML
    void Logout (ActionEvent event) throws IOException {

            System.out.println("Logout");
            CarStage_Receiver.close();
            LoadLogin loadLogin = new LoadLogin();
            loadLogin.loadLogin();


    }

    // for sidenavbar actions
    @FXML
    void DashboardActions (ActionEvent event) {
        // This will be connected on the car system fxml file
        TabActions tabActions = new TabActions();
        tabActions.DashboardActions(DashboardTabPane, DashboardTab);

        // Create scale transitions
        JavafxAnimations animations = new JavafxAnimations();
        animations.loadfx_animation(CarPanel, AdminPane, dashboardPanel, DashboardTitle,
                DashBoardBackground, StaffPane, ReportPane, AvailableCarsPane);

    }
    @FXML
    void nextCalendar (ActionEvent event){

        // This will be connected on the car system fxml file
        TabActions tabActions = new TabActions();
        tabActions.Calendar(CalendarTab, DashboardTabPane);

        // Create scale transitions

        // Create scale transitions
        JavafxAnimations animations = new JavafxAnimations();
        animations.fade_calendars(  CalendarPanel, calendarpanel, calendar, DashboardTabPane);
    }

    @FXML
    void CreateUser (ActionEvent event) {

        // This will be connected on the car system fxml file
        TabActions tabActions = new TabActions();
        tabActions.CreateUserActions(DashboardTabPane, AdminTab);

        // Create scale transitions
        JavafxAnimations animations = new JavafxAnimations();
        animations.fade_animations(UserPane, UserView, AdminTitle1, AdminBackground);

    }



    // This action is to create a user
     @FXML
     void CreateUserToDB(ActionEvent event) {
         String username = UsernameField.getText();
         String password = PasswordfieldText.getText();
         String confirmpassword = ConfirmpasswordFieldText.getText();
         String status = StatusUser.getSelectionModel().getSelectedItem();

         if (username.isEmpty() || password.isEmpty() || confirmpassword.isEmpty() || status.isEmpty()) {
             showErrorAlert("Please fill up all the fields");
         } else {
             if (password.equals(confirmpassword)) {
                 try {
                     if (!verifyPasswordLength(password)) {
                         showErrorAlert("Password must be 8-20 characters");
                     } else {
                         boolean hasCapsLock = false;
                         boolean hasSpecialCharacters = false;

                         for (char character : password.toCharArray()) {
                             if (Character.isUpperCase(character)) {
                                 hasCapsLock = true;
                             } else if (!Character.isLetterOrDigit(character)) {
                                 hasSpecialCharacters = true;
                             }
                         }

                         // Check for both caps lock and special characters
                         if (!hasCapsLock || !hasSpecialCharacters) {
                             showErrorAlert("Password must have at least one uppercase letter and one special character");
                         } else {
                             ConnectMysql connectMysql = new ConnectMysql();
                             connectMysql.checkUsername(username, password, status, UserList, UserView, UsernameField, PasswordfieldText, ConfirmpasswordFieldText, StatusUser);
                         }
                     }
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             } else {
                 showErrorAlert("Password does not match");
             }
         }
     }

    // Helper method to show error alerts
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static boolean verifyPasswordLength(String password) {
        int length = password.length();
        return length >= 8 && length <=20;
    }

    // This function is to see passworld and hide the password

        @FXML
        void checkpassword (ActionEvent event) {
            System.out.println("Check password");

            if (checkPasswordBox.isSelected()) {
                // If checkbox is selected, show the password
                PasswordfieldText.setPromptText(PasswordfieldText.getText());
                PasswordfieldText.clear();
                ConfirmpasswordFieldText.setPromptText(ConfirmpasswordFieldText.getText());
                ConfirmpasswordFieldText.clear();
            } else {
                // If checkbox is not selected, hide the password
                PasswordfieldText.setText(PasswordfieldText.getPromptText());
                PasswordfieldText.setPromptText("Password");
                ConfirmpasswordFieldText.setText(ConfirmpasswordFieldText .getPromptText());
                ConfirmpasswordFieldText.setPromptText("Confirm Password");
            }

        }

    @FXML
    void next(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        CalendarDisplay calendarDisplay = new CalendarDisplay(calendar, year, month, dateFocus, today);
        calendarDisplay.drawCalendar();
    }
    @FXML
    void back(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        CalendarDisplay calendarDisplay = new CalendarDisplay(calendar, year, month, dateFocus, today);
        calendarDisplay.drawCalendar();
    }


    public void initialize (){
        Tooltip tooltip = new Tooltip("Minimize");
        MinimizeButton.setTooltip(tooltip);

        Tooltip tooltip2 = new Tooltip("Close");
        CloseButton.setTooltip(tooltip2);

        System.out.println(CarStage_Receiver+"has a value");

        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        CalendarDisplay calendarDisplay = new CalendarDisplay(calendar, year, month, dateFocus, today);
        calendarDisplay.drawCalendar();


         // Create scale transitions
        JavafxAnimations animations = new JavafxAnimations();
        animations.loadfx_animation(CarPanel, AdminPane, dashboardPanel, DashboardTitle,
                DashBoardBackground, StaffPane, ReportPane, AvailableCarsPane);

        ObservableList <String> Status = FXCollections.observableArrayList("Select a status","Admin", "Staff");
        StatusUser.setItems(Status);

        StatusUser.setValue("Select a status");

        TableColumn<UserTable, Integer> userIDColumn = new TableColumn<>("UserID");
        userIDColumn.setCellValueFactory(cellData -> cellData.getValue().userIDProperty().asObject());
        userIDColumn.setCellFactory(CustomTableCellFactory.cellFactoryForInteger());
        userIDColumn.setMinWidth(110);

        TableColumn<UserTable, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        usernameColumn.setCellFactory(CustomTableCellFactory.cellFactoryForString());
        usernameColumn.setMinWidth(110);

        TableColumn<UserTable, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        statusColumn.setCellFactory(CustomTableCellFactory.cellFactoryForString());
        statusColumn.setMinWidth(110);

        TableColumn <UserTable, Void> actionColumn = new TableColumn<>("Delete");
        actionColumn.setCellFactory(param -> new ButtonCellDeleteUser("Delete", UserView, UserList));
        actionColumn.setMinWidth(100);

        TableColumn<UserTable, Void> actionColumn2 = new TableColumn<>("Edit");
        actionColumn2.setCellFactory(param -> new ButtonCellDeleteUser("Edit", UserView, UserList));
        actionColumn2.setMinWidth(100);

        UserView.getColumns().addAll(userIDColumn, usernameColumn, statusColumn, actionColumn, actionColumn2);


        LoadUserTable(); // This will load the user table


    }


    public void LoadUserTable(){
        UserView.getItems().clear();

        try{
            RetrieveFromMYSQL retrieveFromMYSQL = new RetrieveFromMYSQL();
            UserList = retrieveFromMYSQL.RetrieveUserTable();
            UserView.setItems(UserList);
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
