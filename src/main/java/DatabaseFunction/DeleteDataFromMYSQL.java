package DatabaseFunction;

import javafx.scene.control.Alert;
import javafx_table_functions.TaskTable;
import javafx_table_functions.UserTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDataFromMYSQL {

    /* This is the connection to the database without this,
    you cannot connectto the database */

    private final String MYSQL_URL = MYSQLDATABASE.getDatabaseURL();
    private final String MYSQL_USERNAME = MYSQLDATABASE.getDatabaseUsername();
    private final String MYSQL_PASSWORD = MYSQLDATABASE.getDatabasePassword();


    public void deleteUser(UserTable selectedUser) {
        // Code for deleting user from database
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD)) {

            // This will also find the user by the UserID
            String deleteQuery = "DELETE FROM caruser WHERE UserID = ?";

            // then passed it here on the preparedStatement to delete the userID
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, selectedUser.getUserID()); // Assuming there's a method named getId() in your User class
                int rowsDeleted = preparedStatement.executeUpdate();

                // This will show the alert if the user is deleted
                if (rowsDeleted > 0) {
                    System.out.println("User deleted successfully");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("System Message");
                    alert.setHeaderText(null);
                    alert.setContentText("User deletion successfully");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            // This will error if the code is incorrect but the code is working fine
            throw new RuntimeException(e);
        }
    }
    public void DeleteTask(TaskTable selectedTask){
        // Code for deleting task from database
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD)) {

            // Find the task by the TaskID
            String deleteQuery = "DELETE FROM tasktable WHERE TaskID = ?";

            // Then passed on the prepared statement to delete the TaskID
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, selectedTask.getTaskID()); // Assuming there's a method named getId() in your User class
                int rowsDeleted = preparedStatement.executeUpdate();

                // This will show the alert if the task is deleted
                if (rowsDeleted > 0) {
                    System.out.println("User deleted successfully");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("System Message");
                    alert.setHeaderText(null);
                    alert.setContentText("User deletion successfully");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            // This will error if the code is incorrect but the code is working fine
            throw new RuntimeException(e);
        }
    }
    public static void DeleteCar( int carID) {
        // Code for deleting car from database
        try (Connection connection = DriverManager.getConnection(MYSQLDATABASE.getDatabaseURL(), MYSQLDATABASE.getDatabaseUsername(), MYSQLDATABASE.getDatabasePassword())) {

            // This will find the car by the CarID
            String deleteQuery = "DELETE FROM rentedcars WHERE CarID = ?";

            // Then passed on the prepared statement to delete the CarID
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, carID); // Assuming there's a method named getId() in your User class
                int rowsDeleted = preparedStatement.executeUpdate();

                // This will show the alert if the car is deleted
                if (rowsDeleted > 0) {
                    System.out.println("Car deleted successfully");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("System Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Car deletion successfully");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            // This will error if the code is incorrect but the code is working fine
            throw new RuntimeException(e);
        }
    }

    public void deleteReport(int reportID) {
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD)) {

            // This will find the report by the ReportID
            String deleteQuery = "DELETE FROM reporttable WHERE ReportID = ?";

            // Then passed on the prepared statement to delete the ReportID
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, reportID); // Assuming there's a method named getId() in your User class
                int rowsDeleted = preparedStatement.executeUpdate();

                // This will show the alert if the report is deleted
                if (rowsDeleted > 0) {
                    System.out.println("Report deleted successfully");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("System Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Report deletion successfully");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            // This will error if the code is incorrect but the code is working fine
            throw new RuntimeException(e);
        }
    }
}
