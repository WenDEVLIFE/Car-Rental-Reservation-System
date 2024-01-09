package javafxf_functions;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserTable {
    private final SimpleIntegerProperty userID;
    private final SimpleStringProperty username;
    private final SimpleStringProperty status;

    public UserTable(int userID, String username, String status) {
        this.userID = new SimpleIntegerProperty(userID);
        this.username = new SimpleStringProperty(username);
        this.status = new SimpleStringProperty(status);
    }

    public SimpleIntegerProperty userIDProperty() {
        return userID;
    }

    public int getUserID() {
        return userID.get();
    }

    public void setUserID(int userID) {
        this.userID.set(userID);
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
