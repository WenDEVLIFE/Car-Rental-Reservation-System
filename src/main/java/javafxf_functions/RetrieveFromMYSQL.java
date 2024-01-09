package javafxf_functions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class RetrieveFromMYSQL {
    public String MYSQL_URL = "jdbc:mysql://localhost:3306/car_rental_resevation_db";
    public String MYSQL_USERNAME = "root";

    public String MYSQL_PASSWORD = "";

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

                UserList.add(new UserTable(id, username, status));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return UserList;
    }
}
