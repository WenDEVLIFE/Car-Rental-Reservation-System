package DatabaseFunction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx_table_functions.CarImage;
import javafx_table_functions.CarImage2;
import javafx_table_functions.SalesTable;
import javafx_table_functions.TaskTable;
import javafx_table_functions.UserTable;

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
    public void RetrieTotalCars(){

    }
    public void RetrieveRentedCars(){

    }
    public void RetrieveAdmin(){

    }
    public void RetrieveSTAFF(){

    }
    public void RetrieveAvailableCars(){

    }
    public void RetrieveReports(){

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