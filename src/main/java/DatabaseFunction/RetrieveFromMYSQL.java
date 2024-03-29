package DatabaseFunction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx_table_functions.*;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;

public  class RetrieveFromMYSQL {

    // This is the connection to the database without this, you cannot connect to the database
    private final String MYSQL_URL = MYSQLDATABASE.getDatabaseURL();
    private final String MYSQL_USERNAME = MYSQLDATABASE.getDatabaseUsername();
    private final String MYSQL_PASSWORD = MYSQLDATABASE.getDatabasePassword();

    // This will retrieve the data from the database and put it on the table
    public ObservableList<UserTable> RetrieveUserTable() {

        // This will create an ObservableList of UserTable
        ObservableList<UserTable> UserList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT UserID, username, Status FROM caruser")) {

            // Populate the ObservableList with data from the ResultSet
            while (resultSet.next()) {
                int id = resultSet.getInt("UserID");
                String username = resultSet.getString("username");
                String status = resultSet.getString("Status");

                // This will add the data to the UserList
                UserList.add(new UserTable(id, username, status));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //  This will return the UserList
        return UserList;
    }

    // This will retrieve the data from the database and put it on the table
    public ObservableList<TaskTable> RetrieveCalendarActivity() {

        // This will create an ObservableList of TaskTable
        ObservableList<TaskTable> TaskList= FXCollections.observableArrayList();

        // This will retrieve the data from the database and put it on the table
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT TaskID, TaskInfo, Date FROM tasktable")) {

            // Populate the ObservableList with data from the ResultSet
            while (resultSet.next()) {
                int id = resultSet.getInt("TaskID");
                String taskinfo = resultSet.getString("TaskInfo");
                String date = resultSet.getString("Date");

                // This will add the data to the TaskList
                TaskList.add(new TaskTable(id, taskinfo, date));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // This will return the TaskList
        return TaskList;
    }

    public ObservableList<CarImage> RetrieveCarTable() {
        ObservableList<CarImage> CarList = FXCollections.observableArrayList();

        // This will retrieve the data from the database and put it on the table
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT CarID, Carname, CarPlateNum, CarPrice, CarImage FROM rentedcars")) {

            while (resultSet.next()) {
                // Get the data from the current row using the column index - column data are in the VARCHAR format
                int carID = resultSet.getInt("CarID");
                String carname = resultSet.getString("Carname");
                String carPlateNum = resultSet.getString("CarPlateNum");
                int carPrice = resultSet.getInt("CarPrice");

                // Retrieve BLOB data
                Blob carImageBlob = resultSet.getBlob("CarImage");
                InputStream carImageStream = carImageBlob.getBinaryStream();

                // Convert BLOB to Image
                Image carImage = new Image(carImageStream);

                // Add CarImage to the list
                CarList.add(new CarImage(carID, carname, carPlateNum, carPrice, carImage));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CarList;
    }

    public ObservableList<CarImage2> RetrievePendingCar() {
        // This will create an ObservableList of CarImage2
        ObservableList<CarImage2> RentedCars = FXCollections.observableArrayList();

        // This will retrieve the data from the database and put it on the table
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT CarID, Carname, CarPlateNum, CarPrice, CarImage,PersonRentName, DateRented, DateReturn FROM availablecar")) {

            while (resultSet.next()) {
                 // Get the data from the current row using the column index - column data are in the VARCHAR format
                int carID = resultSet.getInt("CarID");
                String carname = resultSet.getString("Carname");
                String carPlateNum = resultSet.getString("CarPlateNum");
                int carPrice = resultSet.getInt("CarPrice");

                // Retrieve BLOB data
                Blob carImageBlob = resultSet.getBlob("CarImage");
                InputStream carImageStream = carImageBlob.getBinaryStream();

                // Convert BLOB to Image
                Image carImage = new Image(carImageStream);

                String PersonRentedName = resultSet.getString("PersonRentName");
                String DateRented = resultSet.getString("DateRented");
                String DateReturn = resultSet.getString("DateReturn");



                // Add CarImage to the list
                RentedCars.add(new CarImage2(carID, carname, carPlateNum, carPrice, carImage , PersonRentedName, DateRented, DateReturn));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return RentedCars ;
    }
    public ObservableList<SalesTable> RetrieveSalesTable() {
        // This will create an ObservableList of SalesTable
        ObservableList<SalesTable> SalesList = FXCollections.observableArrayList();
        // This will retrieve the data from the database and put it on the table
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT SaleID, PersonName, Amount, Date, PayedStatus, CashierName FROM salestable")) {

            // Populate the ObservableList with data from the ResultSet
            while (resultSet.next()) {
                // Get the data from the current row using the column index - column data are in the VARCHAR format
                int SalesID = resultSet.getInt("SaleID");
                String PersonName = resultSet.getString("PersonName");
                int PayAmount = resultSet.getInt("Amount");
                String Date = resultSet.getString("Date");
                String PayedStatus = resultSet.getString("PayedStatus");
                String CashierName = resultSet.getString("CashierName");

                // This will add the data to the SalesList
                SalesList.add(new SalesTable(SalesID, PersonName, PayAmount, Date, PayedStatus, CashierName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //  This will return the SalesList
        return SalesList;
    }
    public ObservableList<Report> RetrieveReportTable() {
        // This will create an ObservableList of Report Table
        ObservableList<Report> ReportList = FXCollections.observableArrayList();
        // This will retrieve the data from the database and put it on the table
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT ReportID, Username, ReportInfo, Date, Time FROM reporttable")) {

            // Populate the ObservableList with data from the ResultSet
            while (resultSet.next()) {
                // Get the data from the current row using the column index - column data are in the VARCHAR format
                int reportID = resultSet.getInt("ReportID");
                String Name = resultSet.getString("Username");
                String ReportInfo = resultSet.getString("ReportInfo");
                String Date = resultSet.getString("Date");
                String Time = resultSet.getString("Time");

                // This will add the data to the SalesList
                ReportList.add(new Report( reportID, Name, ReportInfo, Date ,Time));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //  This will return the ReportList
        return ReportList;
    }
    public void Retrieve_TotalCars(Label TotalCarCount) {
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
             Statement ignored = connection.createStatement()) {

            // Query to get the total sales for today
            String query = "SELECT COUNT(CarID) FROM rentedcars";
            String query1 = "SELECT COUNT(CarID) FROM availablecar";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 PreparedStatement preparedStatement1 = connection.prepareStatement(query1)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                ResultSet resultSet1 = preparedStatement1.executeQuery();

                if (resultSet.next() && resultSet1.next()) {
                    int totalCars = resultSet.getInt(1) + resultSet1.getInt(1);
                    System.out.println("Total Cars: " + totalCars);
                    TotalCarCount.setText(String.valueOf(totalCars));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void RetrieveRentedCars(Label RentedCarCount){
        try(Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
            Statement ignored = connection.createStatement()){

            String query = "SELECT COUNT(CarID) FROM rentedcars";

            try(PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    int totalCars = resultSet.getInt(1);
                    System.out.println("Total Cars: " + totalCars);
                    RentedCarCount.setText(String.valueOf(totalCars));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void RetrieveAdmin(Label AdminCount){
        try ( Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
              Statement ignored = connection.createStatement()){

            String query = "SELECT COUNT(UserID) FROM caruser WHERE Status = 'Admin'";

            try(PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    int totalAdmin = resultSet.getInt(1);
                    System.out.println("Total Admin: " + totalAdmin);
                    AdminCount.setText(String.valueOf(totalAdmin));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void RetrieveSTAFF(Label StaffCount){
        try ( Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
              Statement ignored = connection.createStatement()){

            String query = "SELECT COUNT(UserID) FROM caruser WHERE Status = 'Staff'";

            try(PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    int totalAdmin = resultSet.getInt(1);
                    System.out.println("Total Admin: " + totalAdmin);
                    StaffCount.setText(String.valueOf(totalAdmin));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void RetrieveAvailableCars(Label TotalAvaialbeCar){
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
              Statement ignored = connection.createStatement()){

            String query = "SELECT COUNT(CarID) FROM availablecar";

            try(PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    int totalAdmin = resultSet.getInt(1);
                    System.out.println("Total Admin: " + totalAdmin);
                    TotalAvaialbeCar.setText(String.valueOf(totalAdmin));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void RetrieveReports(Label ReportCount){
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
              Statement ignored = connection.createStatement()){

            String query = "SELECT COUNT(ReportID) FROM reporttable";

            try(PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    int totalAdmin = resultSet.getInt(1);
                    System.out.println("Total Reports: " + totalAdmin);
                    ReportCount.setText(String.valueOf(totalAdmin));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void display_salesToday(Label SalesToday ){
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
             Statement statement = connection.createStatement()) {
            // Get the current date
            String today = LocalDate.now().toString();

            // Query to get the total sales for today
            String query = "SELECT SUM(Amount) FROM salestable WHERE Date LIKE ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, today + "%");
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int totalSales = resultSet.getInt(1);
                    System.out.println("Total sales for today: " + totalSales);

                    // Display the total sales for today
                    SalesToday.setText(String.valueOf(totalSales));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void DisplaySalesThisMonth(Label SalesThismonth) throws SQLException {
        try (Connection con =DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
            Statement statement = con.createStatement()) {

            String query = "SELECT SUM(Amount) FROM salestable WHERE YEAR(Date) = YEAR(CURDATE()) AND MONTH(Date) = MONTH(CURDATE())";

            try(PreparedStatement preparedStatement = con.prepareStatement(query);
                ResultSet resultSet  = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    int totalSales = resultSet.getInt(1);
                    System.out.println("Total sales for this month: " + totalSales);
                    SalesThismonth.setText(String.valueOf(totalSales));
                }
            }


        }
    }

}