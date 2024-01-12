package DatabaseFunction;

import javafx.scene.control.Alert;
import javafxf_functions.TaskTable;
import javafxf_functions.UserTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDataFromMYSQL {

    /* This is the connection to the database without this,
    you cannot connectto the database */

    public String MYSQL_URL = "jdbc:mysql://localhost:3306/car_rental_resevation_db";
    public String MYSQL_USERNAME = "root";

    public String MYSQL_PASSWORD = "";


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
}