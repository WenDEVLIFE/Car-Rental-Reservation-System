package com.example.car_rental_reservation_system;

import DatabaseFunction.ConnectMysql;
import DatabaseFunction.RentMYSQL_DATABASE;
import DatabaseFunction.RetrieveFromMYSQL;
import Imagecell_function.ImageTableCell;
import Imagecell_function.ImageTableCell2;
import javafx_table_buttons.*;
import javafx_table_functions.SalesTable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx_animation.JavafxAnimations;
import javafx_table_functions.*;
import javafxf_functions.*;
import org.controlsfx.control.action.Action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private Pane CalendarTitlePane;

    @FXML
    private Pane CalendarDisplayPane;

    @FXML
    private Pane AppointmentPane;

    @FXML
    private Pane AppointmentBackground;

    @FXML
    private Pane AppointmentInputPane;

    @FXML
    private Pane CarPane;

    @FXML
    private Pane AddRentCarPaneBG;

    @FXML
    private Pane AddRentCarPanerPane;

    @FXML
    private Pane AvailableCarPane;

    @FXML
    private Pane AVAILableCarBG;

    @FXML
    private Pane RentedCarPane;

    @FXML
    private Pane RentedCarBG;

    @FXML
    private Pane SalesTPane;

    @FXML
    private Pane SalesMPane;

    @FXML
    private Pane SalesBackground;

    @FXML
    private Pane SalesNavPane;

    @FXML
    private Pane ReportSPane;

    @FXML
    private Pane ReportBG;


    @FXML
    private Button PrintSales;

    @FXML
    private Button RefreshSales;

    @FXML
    private Button PrintReport;

    @FXML
    private Button RefrestReport;


    @FXML
    private TabPane DashboardTabPane;

    @FXML
    private Tab DashboardTab;

    @FXML
    private Tab AdminTab;

    @FXML
    private Tab CalendarTab;

    @FXML
    private Tab CalendarInputTab;

    @FXML
    private Tab AppointmentTab;

    @FXML
    private Tab AddRentCarTab;

    @FXML
    private Tab AvailableRentedCarTab;

    @FXML
    private Tab PendingCarTab;

    @FXML
    private Tab RentCarTab;

    @FXML
    private Tab SalesTab;

    @FXML
    private Tab ReportTAB;

    @FXML
    private ComboBox <String> StatusUser;

    @FXML
    private TextField UsernameField;

    @FXML
    private TextField Carname;

    @FXML
    private TextField CarPlateNum;

    @FXML
    private TextField CarPrice;

    @FXML
    private TextField Search_AvailableCars;

    @FXML
    private TextField Search_AvailableCars1;

    @FXML
    private TextField SearchCarName;

    @FXML
    private TextField PersonRentedName;

    @FXML
    private TextField DateRented;

    @FXML
    private TextField PersonPay;

    @FXML
    private TextField DateReturn;

    @FXML
    private TextField CashierName;


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
    private Label Setusername4;

    @FXML
    private Label setstatus4;

    @FXML
    private Label Setusername5;

    @FXML
    private Label setstatus5;

    @FXML
    private Label Setusername6;

    @FXML
    private Label setstatus6;

    @FXML
    private Label Setusername7;

    @FXML
    private Label setstatus7;

    @FXML
    private Label Setusername8;

    @FXML
    private Label setstatus8;

    @FXML
    private Label Setusername9;

    @FXML
    private Label setstatus9;

    @FXML
    private Label Setusername10;

    @FXML
    private Label setstatus10;


    @FXML
    private Label PersonLabel;

    @FXML
    private Label CashierLabel;

    @FXML
    private Label Paylabel;

    @FXML
    private Label DateLabel;

    @FXML
    private Label DateRLabel;

    @FXML
    private Label DateRRLabel;

    @FXML
    private Label CarFile;

    @FXML
    private Label SalesToday;

    @FXML
    private Label SalesThismonth;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private Text todayinfo;

    @FXML
    private Text calendarinfo;

    @FXML
    private TextArea AppointmentTextArea;

    public ZonedDateTime dateFocus;
      public ZonedDateTime today;

      public String receviedDate;

      private File Store_image;

    @FXML
    private TableView<UserTable> UserView;

    ObservableList <UserTable>  UserList= FXCollections.observableArrayList();

    @FXML
    private TableView<TaskTable> TaskView;

    ObservableList <TaskTable>  TaskList= FXCollections.observableArrayList();


    @FXML
    private TableView<CarImage> CarView1;

    @FXML
    private TableView<CarImage> CarView;

    @FXML
    private TableView<CarImage2> CarView2;

    ObservableList <CarImage>  CarList= FXCollections.observableArrayList();

    @FXML
    ObservableList <CarImage2>  RentedCars= FXCollections.observableArrayList();

    @FXML
    private TableView<SalesTable> SalesView;

    ObservableList <SalesTable>  SalesList= FXCollections.observableArrayList();

    @FXML
    private TableView <Report> ReportView;

    ObservableList <Report> ReportList= FXCollections.observableArrayList();

    @FXML
    private ComboBox <String> MonthCombo;

    @FXML
    private ComboBox <String> MonthCombo1;



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
        Setusername4.setText(username);
        setstatus4.setText(status);
        Setusername5.setText(username);
        setstatus5.setText(status);
        Setusername6.setText(username);
        setstatus6.setText(status);
        Setusername7.setText(username);
        setstatus7.setText(status);
        Setusername8.setText(username);
        setstatus8.setText(status);
        Setusername9.setText(username);
        setstatus9.setText(status);
        Setusername10.setText(username);
        setstatus10.setText(status);

    }

    public void DateReceiver(String formatdate){
        // recveive the format date function
        if( formatdate!=null){
            //
            System.out.println("The date is successfully received");
            this.receviedDate = formatdate;
            System.out.println("We successfully received the date "+receviedDate);
        } else {
            throw new NullPointerException("The date is null");
        }
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

           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Confirmation Dialog");
           alert.setHeaderText("Look, a Confirmation Dialog");
           alert.setContentText("Are you sure you want to logout?");
           alert.showAndWait();
              if (alert.getResult() == ButtonType.OK) {
                // ... user chose OK
                  System.out.println("Logout");
                  CarStage_Receiver.close();
                  LoadLogin loadLogin = new LoadLogin();
                  loadLogin.loadLogin();

                  System.runFinalization();
            } else {
                // ... user chose CANCEL or closed the dialog
                System.out.println("You clicked cancel");
              }



    }

    // for sidenavbar actions
    @FXML
    void DashboardActions (ActionEvent event) {
        // Go to next tab pane
        TabActions tabActions = new TabActions();
        tabActions.DashboardActions(DashboardTabPane, DashboardTab);

        // Create scale transitions
        JavafxAnimations animations = new JavafxAnimations();
        animations.loadfx_animation(CarPanel, AdminPane, dashboardPanel, DashboardTitle,
                DashBoardBackground, StaffPane, ReportPane, AvailableCarsPane);

    }
    @FXML
    void nextCalendar (ActionEvent event){

        // Go to next tab pane
        TabActions tabActions = new TabActions();
        tabActions.Calendar(CalendarTab, DashboardTabPane);

        // Create scale transitions

        // Create scale transitions
        JavafxAnimations animations = new JavafxAnimations();
        animations.fade_Calendar_Activity(  CalendarPanel, calendarpanel, calendar);
    }

    @FXML
    void CreateUser (ActionEvent event) {

        // Go to next tab pane
        TabActions tabActions = new TabActions();
        tabActions.CreateUserActions(DashboardTabPane, AdminTab);

        // Create scale transitions
        JavafxAnimations animations = new JavafxAnimations();
        animations.fade_admin(UserView, UserPane, AdminTitle1, AdminBackground);

    }
    @FXML
    void AddCarTabActions (ActionEvent event){

        // Go to next tab pane
        TabActions tabActions = new TabActions();
        tabActions.GoToAddCar(DashboardTabPane, AddRentCarTab);

        JavafxAnimations animations = new JavafxAnimations();
        animations.FaceRentCar(CarView, CarPane, AddRentCarPaneBG, AddRentCarPanerPane);

    }

    @FXML
    void RentACarAction (ActionEvent event){

        // Go to next tab pane
        TabActions tabActions = new TabActions();
        tabActions.GoToAvailCars( DashboardTabPane, AvailableRentedCarTab );

        // Create scale transitions
        JavafxAnimations animations = new JavafxAnimations();
        animations.AvailableCar( CarView1, AvailableCarPane, AVAILableCarBG);


    }

    @FXML
    void CreateAppointment (ActionEvent event) {

        String date = calendarinfo.getText();

        TabActions tabActions = new TabActions();
        tabActions.CreateAppointmentActions(AppointmentTab, DashboardTabPane);

        // Create scale transitions
        JavafxAnimations fade = new JavafxAnimations();
        fade.fade_Appointment(AppointmentPane, AppointmentInputPane, AppointmentBackground);

    }
     @ FXML
     void PendingAction (ActionEvent event){
            TabActions  tabActions = new TabActions();
            tabActions.PendingCars(DashboardTabPane, PendingCarTab);

            // Create scale transitions
            JavafxAnimations animations = new JavafxAnimations();
            animations.PendingCar(CarView2, RentedCarPane, RentedCarBG);

     }

     @FXML
     void SalesAction (ActionEvent event){
            TabActions tabActions = new TabActions();
            tabActions.GoToSales(DashboardTabPane, SalesTab);

            // Create scale transitions
            JavafxAnimations animations = new JavafxAnimations();
         Pane[] panes = {SalesTPane, SalesMPane, SalesBackground, SalesNavPane};
         Button[] buttons = {PrintSales, RefreshSales};
            animations.Sales(SalesView, panes, buttons);
     }
     @FXML
     void GoToReport (ActionEvent event){
        TabActions tabActions = new TabActions();
            tabActions.GoToReport(DashboardTabPane, ReportTAB);

            // Create scale transitions
            Pane [] panes = {ReportSPane, ReportBG};
            JavafxAnimations animations = new JavafxAnimations();
            Button [] buttons = {PrintReport, RefrestReport};
            animations.Report(ReportView, panes, buttons);
     }

    // This action is to create a user
     @FXML
     void CreateUserToDB(ActionEvent event) {
        // This will create a user to the database
         String username = UsernameField.getText();
         String password = PasswordfieldText.getText();
         String confirmpassword = ConfirmpasswordFieldText.getText();
         String status = StatusUser.getSelectionModel().getSelectedItem();

         // This will check if the fields are null or not
         if (username.isEmpty() || password.isEmpty() || confirmpassword.isEmpty() || status.isEmpty()) {
             showErrorAlert("Please fill up all the fields");
         } else {
             if (password.equals(confirmpassword)) {
                 try {
                     if (!verifyPasswordLength(password)) {
                         showErrorAlert("Password must be 8-20 characters");
                     } else {
                         // Check for uppercase and special characters
                         boolean hasCapsLock = false;
                         boolean hasSpecialCharacters = false;

                         // Loop through each character in the password
                         for (char character : password.toCharArray()) {
                             // Check for uppercase and special characters
                             if (Character.isUpperCase(character)) {
                                 // If there is an uppercase letter, set hasCapsLock to true
                                 hasCapsLock = true;
                             } else if (!Character.isLetterOrDigit(character)) {
                                 // If there is a special character, set hasSpecialCharacters to true
                                 hasSpecialCharacters = true;
                             }
                         }

                         // Check for both caps lock and special characters
                         if (!hasCapsLock || !hasSpecialCharacters) {
                             // If either hasCapsLock or hasSpecialCharacters is false, show error alert
                             showErrorAlert("Password must have at least one uppercase letter and one special character");
                         } else {
                             // If all checks are passed, call the connectMysql class to insert the user to the database
                             ConnectMysql connectMysql = new ConnectMysql();

                             // This will check if the username is already exist or not
                             connectMysql.checkUsername(username, password, status, UserList, UserView,
                                     UsernameField, PasswordfieldText, ConfirmpasswordFieldText, StatusUser);
                         }
                     }
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             } else {
                 // This will show the error that password does not match
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
        // This function will check the assigned password length
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
        /*  This is the back function button of the calendar
         * Then it will add the months and pass the parameter */
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        CalendarDisplay calendarDisplay = new CalendarDisplay(calendar, year, month, dateFocus, today, todayinfo,
                calendarinfo,CalendarInputTab, DashboardTabPane, CalendarDisplayPane,
                CalendarTitlePane, TaskList, TaskView);
        calendarDisplay.drawCalendar();
    }

    @FXML
    void back(ActionEvent event) {
        /*  This is the back function button of the calendar
        * Then it will minus the months and pass the parameter */
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        CalendarDisplay calendarDisplay = new CalendarDisplay(calendar, year, month, dateFocus, today,
                todayinfo, calendarinfo,CalendarInputTab, DashboardTabPane,
                CalendarDisplayPane, CalendarTitlePane, TaskList, TaskView);
        calendarDisplay.drawCalendar();
    }

    @FXML
    void SendApointment_MYSQL (ActionEvent event){
        // This will send the appointment to the database
      String appointment_info = AppointmentTextArea.getText();
        if (appointment_info.isEmpty()){
            showErrorAlert("Please fill up the appointment field");
        } else {
            try {
                // Call the connectMysql class to insert the appointment to the database
                ConnectMysql connectMysql = new ConnectMysql();
                connectMysql.InsertAppointment_TaskInfo(appointment_info, receviedDate, calendarinfo);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    void AddImageAction (ActionEvent event){
        //give me upload image file function code here please
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                // This will filter the file to image files only
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(null);

        // This will open the file chooser and get the file
        java.io.File file = fileChooser.showOpenDialog( CarStage_Receiver);

        // This will check if the file is null or not
        if (file != null) {

            // This will get the absolute path of the file
            System.out.println(file.getAbsolutePath());
            Store_image = file;
            System.out.println(Store_image);

            // This will set the image to the label
            CarFile.setText(file.getName());

        }
        else{
            // This will throw an error if the file is null
            throw new NullPointerException("The file is null");
        }

    }
    @FXML
    void AddRentedCars(ActionEvent event){
        // This will add the rented cars to the database
        String carname = Carname.getText();
        String plate  = CarPlateNum.getText();
        String carprice = CarPrice.getText();
        int price = Integer.parseInt(carprice);

        if (carname.isEmpty() ||  plate.isEmpty() || carprice.isEmpty()){
            showErrorAlert("Please fill up all the fields");
        } else {
            try {
                // Call the connectMysql class to insert the appointment to the database
                RentMYSQL_DATABASE rentMYSQL_database = new RentMYSQL_DATABASE();
                rentMYSQL_database.AddCar(carname, plate, price,Store_image,
                        CarView, CarList, Carname, CarPlateNum, CarPrice, CarFile);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    void Rent (ActionEvent event){

        // This will rent the car
        String carname = SearchCarName.getText();
        String personrented = PersonRentedName.getText();
        String date_rented = DateRented.getText();
        String personpay = PersonPay.getText();
        int pay = Integer.parseInt(personpay);
        String date_return = DateReturn.getText();
        String cashiername = CashierName.getText();

        // if the textfield or value is null or empty, show error alert
        if (personrented.isEmpty() || date_rented.isEmpty() || personpay.isEmpty() || date_return.isEmpty() || cashiername.isEmpty() || carname.isEmpty()){
            showErrorAlert("Please fill up all the fields");
        } else {
            try {
                // Call the connectMysql class to insert the appointment to the database
                RentMYSQL_DATABASE rentMYSQL_database = new RentMYSQL_DATABASE();
                rentMYSQL_database.MoveTheRentCarToPending(carname, personrented, date_rented, pay, date_return, cashiername, PersonPay,PersonLabel, DateRLabel, DateRRLabel, CashierLabel, Paylabel, DateLabel, RentedCars, CarView2, SearchCarName, PersonRentedName, DateRented, DateReturn, CashierName);

            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    @FXML
    void PrintCheck(ActionEvent event){
        // This will print the check
        String personrented = PersonLabel.getText();
        String personpay = Paylabel.getText();
        int pay = Integer.parseInt(personpay);
        String date = DateLabel.getText();
        String date_rented = DateRLabel.getText();
        String date_return = DateRRLabel.getText();
        String cashiername = CashierLabel.getText();

        if(personrented == null || personpay == null || date == null || date_rented == null || date_return == null || cashiername == null){
            showErrorAlert("Please fill up all the fields");
        } else {
            try {
                // Call the connectMysql class to insert the appointment to the database
                PrintController printController = new PrintController();
                printController.PrintCheck(personrented, pay, date, date_rented, date_return, cashiername);

            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    @FXML
    void ClearChecks(ActionEvent event){
         // This will clear the checks
        PersonRentedName.clear();
        DateRented.clear();
        PersonPay.clear();
        DateReturn.clear();
        CashierName.clear();
        PersonLabel.setText("");
        DateRLabel.setText("");
        DateRRLabel.setText("");
        CashierLabel.setText("");
        Paylabel.setText("");
        DateLabel.setText("");    }
    @FXML
    void RefreshTable(ActionEvent event){
        // This will refresh the table
        LoadCar();

    }


    @FXML
    void SelectMonth(ActionEvent event) {
        // This will select the month
        String month = MonthCombo.getSelectionModel().getSelectedItem();

        if ("Select a month".equals(month)) {
            // If the default month is selected, show all sales data
            SalesView.getItems().setAll(SalesList);
            LoadSalesTable();
            return;
        }

        if (month == null || month.isEmpty()) {
            showErrorAlert("Please select a month");
            return;
        }

        // Filter the data based on the selected month
        List<SalesTable> filteredSales = SalesList.stream()
                .filter(sales -> getMonthFromSales(sales).equalsIgnoreCase(month))
                .collect(Collectors.toList());
        LoadSalesTable();

        // Clear the existing items in the SalesView
        SalesView.getItems().clear();

        // Add the filtered data to the SalesView
        SalesView.getItems().addAll(filteredSales);
    }

    // Utility method to extract the month from SalesTable
    private String getMonthFromSales(SalesTable sales) {
        // Placeholder: Replace this with the actual method or property to get the date from SalesTable
        String salesDate = sales.getDate();  // Replace 'getDate()' with the actual method or property
        return getMonthFromDate(salesDate);
    }

    // Utility method to extract the month from the date string
    private String getMonthFromDate(String date) {
        try {
            // get the month from the date string
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // This will parse the date
            Date parsedDate = dateFormat.parse(date);

            // The calendar will get the month
            Calendar calendar = Calendar.getInstance();

            // This will set the time
            calendar.setTime(parsedDate);

            // This will get the month
            int month = calendar.get(Calendar.MONTH);

            // This will return the month
            return new DateFormatSymbols().getMonths()[month];
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    @FXML
    void PrintExcel(ActionEvent event){
         // This will print the excel
        PrintController printController = new PrintController();
        printController.printReports_Excel(SalesView);
    }
    @FXML
    void RefreshSales (ActionEvent event){
        // This will refresh the sales table
        LoadSalesTable();

    }

    @FXML
    void SelectMonthReport (ActionEvent event){

    }

    @FXML
    void PrintReport (ActionEvent event){

    }
    @FXML
    void RefreshReport (ActionEvent event){

    }

    public void initialize () throws SQLException {

        // This will initialize the car system
        System.runFinalization();

        // This will set the tooltip of the minimize and close button
        Tooltip tooltip = new Tooltip("Minimize");
        MinimizeButton.setTooltip(tooltip);

        Tooltip tooltip2 = new Tooltip("Close");
        CloseButton.setTooltip(tooltip2);

        System.out.println(CarStage_Receiver+"has a value");

        // This will set the date focus
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        CalendarDisplay calendarDisplay = new CalendarDisplay(calendar, year, month, dateFocus, today, todayinfo, calendarinfo,CalendarInputTab, DashboardTabPane,CalendarDisplayPane, CalendarTitlePane, TaskList, TaskView);
        calendarDisplay.drawCalendar();

         // Create scale transitions
        JavafxAnimations animations = new JavafxAnimations();
        animations.loadfx_animation(CarPanel, AdminPane, dashboardPanel, DashboardTitle,
                DashBoardBackground, StaffPane, ReportPane, AvailableCarsPane);

        ObservableList <String> Status = FXCollections.observableArrayList("Select a status","Admin", "Staff");
        StatusUser.setItems(Status);

        StatusUser.setValue("Select a status");

        // This will set the combo box month
        ObservableList <String> Month = FXCollections.observableArrayList("Select a month","January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November","December");
        MonthCombo.setItems(Month);
        MonthCombo1.setItems(Month);


        // This will set the sales this day to the label
        RetrieveFromMYSQL sales = new RetrieveFromMYSQL();
        sales.display_salesToday(SalesToday);

        // This will set the sales this month value to the label
        RetrieveFromMYSQL sales_this_month = new RetrieveFromMYSQL();
        sales_this_month.DisplaySalesThisMonth(SalesThismonth);

        // This is the function of autosearch in car table
        Search_AvailableCars.setPromptText("Search Available Cars");
        Search_AvailableCars.textProperty().addListener((observable, oldValue, newValue) -> {
            CarView1.setItems(CarList.filtered(carImage -> carImage.getCarname().toLowerCase().contains(newValue.toLowerCase())));
        });

        Search_AvailableCars1.setPromptText("Search Available Cars");
        Search_AvailableCars1.textProperty().addListener((observable, oldValue, newValue) -> {
            CarView2.setItems(RentedCars.filtered(carImage -> carImage.getCarname().toLowerCase().contains(newValue.toLowerCase())));
        });



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


        // This will load the task table
        TableColumn<TaskTable, Integer> taskIDColumn = new TableColumn<>("TaskID");
        taskIDColumn.setCellValueFactory(cellData -> cellData.getValue().taskIDProperty().asObject());
        taskIDColumn.setCellFactory(CustomTableCellFactoryTask.cellFactoryForInteger());
        taskIDColumn.setMinWidth(110);

        TableColumn<TaskTable, String> taskNameColumn = new TableColumn<>("Task Name");
        taskNameColumn.setCellValueFactory(cellData -> cellData.getValue().taskNameProperty());
        taskNameColumn.setCellFactory(CustomTableCellFactoryTask.cellFactoryForString());
        taskNameColumn.setMinWidth(285);

        TableColumn<TaskTable, String> taskStatusColumn = new TableColumn<>("Date");
        taskStatusColumn.setCellValueFactory(cellData ->  cellData.getValue().DateProperty());
        taskStatusColumn.setCellFactory(CustomTableCellFactoryTask.cellFactoryForString());
        taskStatusColumn.setMinWidth(110);

        TableColumn <TaskTable, Void> actionColumn3 = new TableColumn<>("Done Task");
        actionColumn3.setCellFactory(param -> new ButtonCalendar("Done Task", TaskView, TaskList));
        actionColumn3.setMinWidth(100);

        TableColumn<TaskTable, Void> actionColumn4 = new TableColumn<>("Delete Task");
        actionColumn4.setCellFactory(param -> new ButtonCalendar("Delete Task", TaskView, TaskList));

        TaskView.getColumns().addAll(taskIDColumn, taskNameColumn, taskStatusColumn, actionColumn3, actionColumn4);


        // This will load the car table
        TableColumn<CarImage, Integer> carIDColumn = new TableColumn<>("CarID");
        carIDColumn.setCellValueFactory(cellData -> cellData.getValue().CarIDProperty().asObject());
        carIDColumn.setCellFactory(CustomTableCellFactoryCar.cellFactoryForInteger());
        carIDColumn.setMinWidth(110);


        TableColumn<CarImage, Image> imageColumn = new TableColumn<>("Car Image");
        imageColumn.setCellValueFactory(cellData ->  cellData.getValue().CarImageProperty());
        imageColumn.setCellFactory(param -> new ImageTableCell());


        TableColumn<CarImage, String> carNameColumn = new TableColumn<>("Car Name");
        carNameColumn.setCellValueFactory(cellData -> cellData.getValue().CarnameProperty());
        carNameColumn.setCellFactory(CustomTableCellFactoryCar.cellFactoryForString());

        TableColumn<CarImage, String> carPlateNumColumn = new TableColumn<>("Car Plate Number");
        carPlateNumColumn.setCellValueFactory(cellData -> cellData.getValue().CarPlateNumProperty());
        carPlateNumColumn.setCellFactory(CustomTableCellFactoryCar.cellFactoryForString());

        TableColumn<CarImage, Integer> carPriceColumn = new TableColumn<>("Car Price");
        carPriceColumn.setCellValueFactory(cellData -> cellData.getValue().CarPriceProperty().asObject());
        carPriceColumn.setCellFactory(CustomTableCellFactoryCar.cellFactoryForInteger());

        TableColumn <CarImage, Void> actionColumn5 = new TableColumn<>("Delete Car");
        actionColumn5.setCellFactory(param -> new ButtonCellDeleteCar("Delete Car", CarView, CarList, CarView1, DashboardTabPane, RentCarTab));
        actionColumn5.setMinWidth(100);

        CarView.getColumns().addAll(carIDColumn, imageColumn, carNameColumn, carPlateNumColumn, carPriceColumn, actionColumn5);

        TableColumn<CarImage, Integer> carIDColumn1 = new TableColumn<>("CarID");
        carIDColumn1.setCellValueFactory(cellData -> cellData.getValue().CarIDProperty().asObject());
        carIDColumn1.setCellFactory(CustomTableCellFactoryCar.cellFactoryForInteger());
        carIDColumn1.setMinWidth(130);


        TableColumn<CarImage, Image> imageColumn1 = new TableColumn<>("Car Image");
        imageColumn1.setCellValueFactory(cellData ->  cellData.getValue().CarImageProperty());
        imageColumn1.setCellFactory(param -> new ImageTableCell());
        imageColumn1.setMinWidth(130);


        TableColumn<CarImage, String> carNameColumn1 = new TableColumn<>("Car Name");
        carNameColumn1.setCellValueFactory(cellData -> cellData.getValue().CarnameProperty());
        carNameColumn1.setCellFactory(CustomTableCellFactoryCar.cellFactoryForString());
        carNameColumn1.setMinWidth(130);


        TableColumn<CarImage, String> carPlateNumColumn1 = new TableColumn<>("Car Plate Number");
        carPlateNumColumn1.setCellValueFactory(cellData -> cellData.getValue().CarPlateNumProperty());
        carPlateNumColumn1.setCellFactory(CustomTableCellFactoryCar.cellFactoryForString());
        carPlateNumColumn1.setMinWidth(110);

        TableColumn<CarImage, Integer> carPriceColumn1 = new TableColumn<>("Car Price");
        carPriceColumn1.setCellValueFactory(cellData -> cellData.getValue().CarPriceProperty().asObject());
        carPriceColumn1.setCellFactory(CustomTableCellFactoryCar.cellFactoryForInteger());
        carPriceColumn1.setMinWidth(110);

        TableColumn <CarImage, Void> actionColumn55 = new TableColumn<>("Delete Car");
        actionColumn55.setCellFactory(param -> new ButtonCellDeleteCar("Delete Car", CarView, CarList, CarView1, DashboardTabPane, RentCarTab));
        actionColumn55.setMinWidth(100);

        TableColumn <CarImage, Void> actionColumn6 = new TableColumn<>("Rent Car");
        actionColumn6.setCellFactory(param -> new ButtonCellDeleteCar("Rent Car", CarView, CarList, CarView1, DashboardTabPane, RentCarTab));
        actionColumn6.setMinWidth(100);

        CarView1.getColumns().addAll(carIDColumn1, imageColumn1, carNameColumn1, carPlateNumColumn1, carPriceColumn1, actionColumn55, actionColumn6);

        // This will load the car tables
        LoadCar();

        TableColumn<CarImage2, Integer> CarIDColumn = new TableColumn<>("CarID");
        CarIDColumn.setCellValueFactory(cellData -> cellData.getValue().CarIDProperty().asObject());
        CarIDColumn.setCellFactory(CustomTableCellFactoryCar2.cellFactoryForInteger());

        TableColumn<CarImage2, Image> ImageColumn = new TableColumn<>("Car Image");
        ImageColumn.setCellValueFactory(cellData ->  cellData.getValue().CarImageProperty());
        ImageColumn.setCellFactory(param -> new ImageTableCell2());

        TableColumn<CarImage2, String> CarNameColumn = new TableColumn<>("Car Name");
        CarNameColumn.setCellValueFactory(cellData -> cellData.getValue().CarnameProperty());
        CarNameColumn.setCellFactory(CustomTableCellFactoryCar2.cellFactoryForString());

        TableColumn<CarImage2, String> CarPlateNumColumn = new TableColumn<>("Car Plate Number");
        CarPlateNumColumn.setCellValueFactory(cellData -> cellData.getValue().CarPlateNumProperty());
        CarPlateNumColumn.setCellFactory(CustomTableCellFactoryCar2.cellFactoryForString());
        CarPlateNumColumn.setMinWidth(130);

        TableColumn<CarImage2, Integer> CarPriceColumn = new TableColumn<>("Car Price");
        CarPriceColumn.setCellValueFactory(cellData -> cellData.getValue().CarPriceProperty().asObject());
        CarPriceColumn.setCellFactory(CustomTableCellFactoryCar2.cellFactoryForInteger());

        TableColumn<CarImage2, String> PersonName = new TableColumn<>("Person Rented");
        PersonName.setCellValueFactory(cellData -> cellData.getValue().PersonRentedProperty());
        PersonName.setCellFactory(CustomTableCellFactoryCar2.cellFactoryForString());
        PersonName.setMinWidth(150);

        TableColumn<CarImage2, String> DateRented = new TableColumn<>("Date Rented");
        DateRented.setCellValueFactory(cellData -> cellData.getValue().DateRentedProperty());
        DateRented.setCellFactory(CustomTableCellFactoryCar2.cellFactoryForString());

        TableColumn<CarImage2, String> DateReturn = new TableColumn<>("Date Return");
        DateReturn.setCellValueFactory(cellData -> cellData.getValue().DateReturnProperty());
        DateReturn.setCellFactory(CustomTableCellFactoryCar2.cellFactoryForString());

        TableColumn<CarImage2, Void> actionColumn7 = new TableColumn<>("Moved to Rented");
        actionColumn7.setCellFactory(param -> new ButtonCellDeleteCar1("Moved to Rented", CarView2, RentedCars, CarView1,CarList,CarView));
        actionColumn7.setMinWidth(100);


        CarView2.getColumns().addAll(CarIDColumn, ImageColumn, CarNameColumn, CarPlateNumColumn, CarPriceColumn, PersonName, DateRented, DateReturn, actionColumn7);

     // call the loading pending method
        LoadPending();

        // This will load the sales table
        TableColumn<SalesTable, Integer> SalesIDColumn = new TableColumn<>("SalesID");
        SalesIDColumn.setCellValueFactory(cellData -> cellData.getValue().SalesIDProperty().asObject());
        SalesIDColumn.setCellFactory(CustomTableCellFactorySales.cellFactoryForInteger());

        TableColumn<SalesTable, String> SalesNameColumn = new TableColumn<>("Person Name");
        SalesNameColumn.setCellValueFactory(cellData -> cellData.getValue().SalesNameProperty());
        SalesNameColumn.setCellFactory(CustomTableCellFactorySales.cellFactoryForString());

        TableColumn<SalesTable, String> SalesDateColumn = new TableColumn<>("Date");
        SalesDateColumn.setCellValueFactory(cellData -> cellData.getValue().SalesDateProperty());
        SalesDateColumn.setCellFactory(CustomTableCellFactorySales.cellFactoryForString());

        TableColumn<SalesTable, Integer> SalesPriceColumn = new TableColumn<>("Amount");
        SalesPriceColumn.setCellValueFactory(cellData -> cellData.getValue().SalesPriceProperty().asObject());
        SalesPriceColumn.setCellFactory(CustomTableCellFactorySales.cellFactoryForInteger());

        TableColumn <SalesTable, String> CashierColumn = new TableColumn<>("CashierName");
        CashierColumn.setCellValueFactory(cellData -> cellData.getValue().CashierNameProperty());
        CashierColumn.setCellFactory(CustomTableCellFactorySales.cellFactoryForString());

        TableColumn <SalesTable, Void> deletecolumn = new TableColumn<>("Delete");
       deletecolumn.setCellFactory(param -> new ButtonCellDeleteSales("Delete", SalesView, SalesList));

        SalesView.getColumns().addAll(SalesIDColumn, SalesNameColumn, SalesDateColumn, SalesPriceColumn, CashierColumn, deletecolumn);

        // This will load the sales table
        LoadSalesTable();

        // This will load the report table
        TableColumn <Report, Integer> ReportIDColumn = new TableColumn<>("ReportID");
        ReportIDColumn.setCellValueFactory(cellData -> cellData.getValue().ReportIDProperty().asObject());
        ReportIDColumn.setCellFactory(CustomTableCellFactoryReport.cellFactoryForInteger());

        TableColumn <Report, String> ReportNameColumn = new TableColumn<>("Username");
        ReportNameColumn.setCellValueFactory(cellData -> cellData.getValue().ReportNameProperty());
        ReportNameColumn.setCellFactory(CustomTableCellFactoryReport.cellFactoryForString());
        ReportNameColumn.setMinWidth(130);

        TableColumn <Report, String> ReportActionColumn = new TableColumn<>("ReportInfo");
        ReportActionColumn.setCellValueFactory(cellData -> cellData.getValue().ReportInfoProperty());
        ReportActionColumn.setCellFactory(CustomTableCellFactoryReport.cellFactoryForString());
        ReportActionColumn.setMinWidth(200);


        TableColumn <Report, String> ReportDateColumn = new TableColumn<>("Date");
        ReportDateColumn.setCellValueFactory(cellData -> cellData.getValue().ReportDateProperty());
        ReportDateColumn.setCellFactory(CustomTableCellFactoryReport.cellFactoryForString());
        ReportDateColumn.setMinWidth(130);

        TableColumn <Report, String> ReportTimeColumn = new TableColumn<>("Time");
        ReportTimeColumn.setCellValueFactory(cellData -> cellData.getValue().ReportTimeProperty());
        ReportTimeColumn.setCellFactory(CustomTableCellFactoryReport.cellFactoryForString());
        ReportTimeColumn.setMinWidth(130);

        TableColumn <Report, Void> ReportDeleteColumn = new TableColumn<>("Delete");
        ReportDeleteColumn.setCellFactory(param -> new ButtonReport("Delete", ReportView, ReportList));
        ReportDeleteColumn.setMinWidth(140);

        ReportView.getColumns().addAll(ReportIDColumn, ReportNameColumn, ReportActionColumn, ReportDateColumn, ReportTimeColumn, ReportDeleteColumn);

        LoadReport();




    }

    // This will load the user table
    public void LoadUserTable(){
        UserView.getItems().clear();

        try{
            // calll the database controller to retrieve the value
            RetrieveFromMYSQL retrieveFromMYSQL = new RetrieveFromMYSQL();
            UserList = retrieveFromMYSQL.RetrieveUserTable();
            UserView.setItems(UserList);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public void LoadCar(){

        // load the Carview and CarView1
        CarView.getItems().clear();
        CarView1.getItems().clear();

        try {

            // call the database controller to retrieve the value
            RetrieveFromMYSQL retrieveFromMYSQL = new RetrieveFromMYSQL();
            CarList = retrieveFromMYSQL.RetrieveCarTable();
            CarView.setItems(CarList);
            CarView1.setItems(CarList);


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void LoadPending(){

        // Load the approved cars
        CarView2.getItems().clear();

        try {

            // call the database controller to retrieve the value
            RetrieveFromMYSQL retrieveFromMYSQL = new RetrieveFromMYSQL();
            RentedCars = retrieveFromMYSQL.RetrievePendingCar();
            CarView2.setItems(RentedCars);
            CarView2.setItems( RentedCars);


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // This will load the sales method
    private void LoadSalesTable() {

        // Load the sales table
        SalesView.getItems().clear();

        try {

            // call the database controller to retrieve the value
            RetrieveFromMYSQL retrieveFromMYSQL = new RetrieveFromMYSQL();
            SalesList = retrieveFromMYSQL.RetrieveSalesTable();
            SalesView.setItems(SalesList);

        } catch ( Exception e){
            e.printStackTrace();
        }
    }

    private void LoadReport(){
        // Load the report table
        ReportView.getItems().clear();

        try {

            // call the database controller to retrieve the value
            RetrieveFromMYSQL retrieveFromMYSQL = new RetrieveFromMYSQL();
            ReportList = retrieveFromMYSQL.RetrieveReportTable();
            ReportView.setItems(ReportList);
        } catch ( Exception e){
            e.printStackTrace();
        }
    }


}
