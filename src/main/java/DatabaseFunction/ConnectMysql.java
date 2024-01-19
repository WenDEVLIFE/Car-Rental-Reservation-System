package DatabaseFunction;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx_table_functions.UserTable;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

public class ConnectMysql {
    private final String MYSQL_URL = MYSQLDATABASE.getDatabaseURL();
    private final String MYSQL_USERNAME = MYSQLDATABASE.getDatabaseUsername();
    private final String MYSQL_PASSWORD = MYSQLDATABASE.getDatabasePassword();

    public void checkUsername (String username, String password, String status, ObservableList<UserTable> UserList, TableView<UserTable> UserView, TextField UsernameField, PasswordField PasswordfieldText, PasswordField ConfirmpasswordFieldText, ComboBox<String> StatusUser){
        try {
            if( doesUsernameExist("username") ) {
                System.out.println("Username already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Username already exists");
                alert.showAndWait();
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



    public void ChangeUsername(String username, String oldusername, String password, Label Setusername1, Label Setusername2, Label Setusername3, Label Setusername4, Label setusername5, Label Setusername6, Label Setusername7, Label Setusername8, Label Setusername9, Label Setusername10, Label Setusername11, Label Setusername12, Label Setusername13, Label Setusername14, Label Setusername15, TextField OldUserField, TextField NewUserField, PasswordField UserPasswordField) {


        boolean usernameChanged = changeUsername( username, oldusername, password);

        if (usernameChanged) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Username has been changed");
            alert.showAndWait();
            String oldusername1 = Setusername12.getText();
            if (oldusername1.equals(oldusername)) {
                Setusername1.setText(username);
                Setusername2.setText(username);
                Setusername3.setText(username);
                Setusername4.setText(username);
                setusername5.setText(username);
                Setusername6.setText(username);
                Setusername7.setText(username);
                Setusername8.setText(username);
                Setusername9.setText(username);
                Setusername10.setText(username);
                Setusername11.setText(username);
                Setusername12.setText(username);
                Setusername13.setText(username);
                Setusername14.setText(username);
                Setusername15.setText(username);

                OldUserField.clear();
                NewUserField.clear();
                UserPasswordField.clear();
            }
        } else {
            System.out.println("Username change failed. Please check your credentials.");
        }

    }
    public boolean changeUsername(String username, String oldUsername, String password) {
        try {
            Connection con = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
            String selectQuery = "SELECT * FROM caruser WHERE username = ?";
            try (PreparedStatement selectStatement = con.prepareStatement(selectQuery)) {
                selectStatement.setString(1, oldUsername);
                ResultSet resultSet = selectStatement.executeQuery();

                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    byte[] salt = resultSet.getBytes("salt");

                    if (PasswordUtils.verifyPassword(password, storedPassword, salt)) {
                        String updateQuery = "UPDATE caruser SET username = ? WHERE username = ?";
                        try (PreparedStatement updateStatement = con.prepareStatement(updateQuery)) {
                            updateStatement.setString(1, username);
                            updateStatement.setString(2, oldUsername);

                            int rowsAffected = updateStatement.executeUpdate();

                            if (rowsAffected > 0) {
                                return true;
                            }
                        }
                    } else {
                        // Incorrect password
                        return false;
                    }
                } else {
                    // Username does not exist
                    return false;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public void ChangePassword(String oldpassword, String newpassword, String username, PasswordField OldpasswordField, PasswordField NewPasswordField, TextField UsernameFields) {

        boolean passwordChanged = changePassword(oldpassword, newpassword, username);

        if (passwordChanged) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Password has been changed");
            alert.showAndWait();
            OldpasswordField.clear();
            NewPasswordField.clear();
            UsernameFields.clear();
        } else {
            System.out.println("Password change failed. Please check your credentials.");
        }
    }

    private boolean changePassword(String oldpassword, String newpassword, String username) {
        try {
            Connection con = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
            String selectQuery = "SELECT * FROM caruser WHERE username = ?";
            try (PreparedStatement selectStatement = con.prepareStatement(selectQuery)) {
                selectStatement.setString(1, username);
                ResultSet resultSet = selectStatement.executeQuery();

                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    byte[] salt = resultSet.getBytes("salt");

                    if (PasswordUtils.verifyPassword(oldpassword, storedPassword, salt)) {
                        // Old password is correct, generate new salt and hash the new password
                        byte[] newSalt = PasswordUtils.generateSalt();
                        String newHashedPassword = PasswordUtils.hashPassword(newpassword, newSalt);

                        String updateQuery = "UPDATE caruser SET password = ?, salt = ? WHERE username = ?";
                        try (PreparedStatement updateStatement = con.prepareStatement(updateQuery)) {
                            updateStatement.setString(1, newHashedPassword);
                            updateStatement.setBytes(2, newSalt);
                            updateStatement.setString(3, username);

                            int rowsAffected = updateStatement.executeUpdate();

                            return rowsAffected > 0;
                        }
                    } else {
                        // Incorrect old password
                        return false;
                    }
                } else {
                    // Username does not exist
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Username does not exist");
                    alert.showAndWait();
                    return false;


                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
