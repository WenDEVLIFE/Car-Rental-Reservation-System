package DatabaseFunction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafxf_functions.TaskTable;
import javafxf_functions.UserTable;

import java.sql.*;

public  class RetrieveFromMYSQL {

    // This is the connection to the database without this, you cannot connect to the database
    public String MYSQL_URL = "jdbc:mysql://localhost:3306/car_rental_resevation_db";
    public String MYSQL_USERNAME = "root";

    public String MYSQL_PASSWORD = "";

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
}