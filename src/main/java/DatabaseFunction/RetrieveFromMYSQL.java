package DatabaseFunction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx_table_functions.CarImage;
import javafx_table_functions.CarImage2;
import javafxf_functions.TaskTable;
import javafxf_functions.UserTable;

import java.io.InputStream;
import java.sql.*;

public  class RetrieveFromMYSQL {

    // This is the connection to the database without this, you cannot connect to the database
    private final String MYSQL_URL = MYSQLDATABASE.getDatabaseURL();
    private final String MYSQL_USERNAME = MYSQLDATABASE.getDatabaseUsername();
    private final String MYSQL_PASSWORD = MYSQLDATABASE.getDatabasePassword();

    // This will retrieve the data from the database and put it on the table
    public ObservableList<UserTable> RetrieveUserTable() {
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
        ObservableList<TaskTable> TaskList= FXCollections.observableArrayList();
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

        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT CarID, Carname, CarPlateNum, CarPrice, CarImage FROM rentedcars")) {

            while (resultSet.next()) {
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
        ObservableList<CarImage2> RentedCars = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT CarID, Carname, CarPlateNum, CarPrice, CarImage,PersonRentName, DateRented, DateReturn FROM availablecar")) {

            while (resultSet.next()) {
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
}