package javafxf_functions;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDataFromMYSQL {
    public String MYSQL_URL = "jdbc:mysql://localhost:3306/car_rental_resevation_db";
    public String MYSQL_USERNAME = "root";

    public String MYSQL_PASSWORD = "";


    public void deleteUser(UserTable selectedUser) {
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD)) {
            String deleteQuery = "DELETE FROM caruser WHERE UserID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, selectedUser.getUserID()); // Assuming there's a method named getId() in your User class
                int rowsDeleted = preparedStatement.executeUpdate();
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
            throw new RuntimeException(e);
        }
    }
}
