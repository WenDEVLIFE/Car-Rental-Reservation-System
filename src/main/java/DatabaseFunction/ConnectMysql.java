package DatabaseFunction;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafxf_functions.UserTable;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

public class ConnectMysql {
    private String MYSQL_URL = MYSQLDATABASE.getDatabaseURL();
    private String MYSQL_USERNAME = MYSQLDATABASE.getDatabaseUsername();
    private String MYSQL_PASSWORD = MYSQLDATABASE.getDatabasePassword();

    public void checkUsername (String username, String password, String status, ObservableList<UserTable> UserList, TableView<UserTable> UserView, TextField UsernameField, PasswordField PasswordfieldText, PasswordField ConfirmpasswordFieldText, ComboBox<String> StatusUser){
        try {
            if( doesUsernameExist("username") ) {
                System.out.println("Username already exists");
            } else {
                System.out.println("Username does not exist");
                RegisterUser(username, password, status, UserList, UserView,  UsernameField,  PasswordfieldText,ConfirmpasswordFieldText, StatusUser);
            }


        } catch ( Exception e) {
            e.printStackTrace();
        }

    }

    public boolean doesUsernameExist(String username) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection( MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD)) {
            String selectSQL = "SELECT COUNT(*) FROM caruser WHERE username = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectSQL)) {
                selectStatement.setString(1, username);

                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        }
        return false;
    }


    private void RegisterUser(String username, String password, String status, ObservableList<UserTable> UserList, TableView<UserTable> UserView, TextField UsernameField, PasswordField PasswordfieldText, PasswordField ConfirmpasswordFieldText, ComboBox<String> StatusUser) throws ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);

        try (Connection con = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD)) {
            // Get the maximum ID value from the user table.
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(UserID) FROM caruser");
            int highestId = 0;
            if (resultSet.next()) {
                highestId = resultSet.getInt(1);
            }

            // Increment the highest ID value by 1 to get the new ID value.
            int newId = highestId + 1;

            String query = "INSERT INTO caruser (UserID, username, password, salt, Status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, newId);
                pst.setString(2, username);
                pst.setString(3, hashedPassword);
                pst.setBytes(4, salt);
                pst.setString(5, status);

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("User has been registered");
                    alert.showAndWait();
                    UserList.add(new UserTable(newId, username, status));
                    UserView.setItems(UserList);
                    UsernameField.clear();
                    PasswordfieldText.clear();
                    ConfirmpasswordFieldText.clear();
                    StatusUser.setValue("Select a status");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Log or display the exception
        }
    }

    private static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeySpecException, NoSuchAlgorithmException {
        int iterations = 10000; // Number of iterations
        int keyLength = 256; // Key length in bits

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hashedNewPassword = factory.generateSecret(spec).getEncoded();

        return bytesToHex(hashedNewPassword);
    }
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // You can adjust the salt size as needed
        random.nextBytes(salt);
        return salt;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte aByte : bytes) {
            hexString.append(String.format("%02x", aByte));
        }
        return hexString.toString();
    }

    public void InsertAppointment_TaskInfo(String appointment_info, String receviedDate, Text calendarinfo){
        String date = calendarinfo.getText();
        try {
            Connection con = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(TaskID) FROM tasktable");
            int highestId = 0;
            if (resultSet.next()) {
                highestId = resultSet.getInt(1);
            }

            // Increment the highest ID value by 1 to get the new ID value.
            int newId = highestId + 1;

            String query = "INSERT INTO tasktable (TaskID, TaskInfo, Date) VALUES (?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, newId);
                pst.setString(2, appointment_info);
                pst.setString(3, date);

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Task has been added");
                    alert.showAndWait();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Log or display the exception
        }
    }

}
