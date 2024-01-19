package javafx_table_buttons;

import DatabaseFunction.DeleteDataFromMYSQL;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx_animation.JavafxAnimations;
import javafx_table_functions.UserTable;

import java.util.Optional;

public class ButtonCellDeleteUser extends TableCell<UserTable, Void> {
    private final Button button;

    private TableView < UserTable > UserView;

    ObservableList < UserTable > UserList;

    private TabPane DashboardTabPane;

    private Tab ChangePassTab;

    private Tab ChangeUserTab;

    private TextField UsernameFields;

    private TextField OldUserField;

    private Pane ChangeUserPane ;
    private Pane  ChangePassPane;
    private Pane  ChangeUserBG;
    private Pane  ChangePassBG;
    private Pane  ChangePassTabPane;
    private Pane ChangeUsersPane;

    public ButtonCellDeleteUser(String buttonText, TableView < UserTable > UserView, ObservableList < UserTable > UserList, TabPane DashboardTabPane, Tab ChangePassTab, Tab ChangeUserTab, TextField UsernameFields, TextField OldUserField, Pane ChangeUserPane, Pane  ChangePassPane, Pane  ChangeUserBG, Pane  ChangePassBG, Pane  ChangePassTabPane, Pane ChangeUsersPane) {
        this.button = new Button(buttonText);
        this.UserView = UserView;
        this.UserList = UserList;
        this.DashboardTabPane = DashboardTabPane;
        this.ChangePassTab = ChangePassTab;
        this.ChangeUserTab = ChangeUserTab;
        this.UsernameFields = UsernameFields;
        this.OldUserField = OldUserField;
        this.ChangeUserPane = ChangeUserPane;
        this.ChangePassPane = ChangePassPane;
        this.ChangeUserBG = ChangeUserBG;
        this.ChangePassBG = ChangePassBG;
        this.ChangePassTabPane = ChangePassTabPane;
        this.ChangeUsersPane = ChangeUsersPane;
        this.button.setOnAction(event -> {
            UserTable selectedUser = getTableRow().getItem();
            if (selectedUser != null) {
                if (buttonText.equals("Delete")) {
                    // Code for deleting user
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this user?");

                    ButtonType buttonTypeYes = new ButtonType("Yes");
                    ButtonType buttonTypeNo = new ButtonType("No");

                    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                    alert.showAndWait().ifPresent(response -> {
                        if (response == buttonTypeYes) {
                            // Code to handle user deletion
                            System.gc(); // This line seems unnecessary for user deletion
                            DeleteDataFromMYSQL db = new DeleteDataFromMYSQL();
                            db.deleteUser(selectedUser);

                            // remove the student from the table and to the database
                            UserList.remove(selectedUser);
                            UserView.refresh();
                        }
                    });
                } else if (buttonText.equals("Edit")) {
                    // Code for editing user
                    // You may want to open a new dialog or switch to another view here
                    // For example, show an edit dialog or switch to the edit user view
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Edit Message ");
                    alert.setContentText("Choose your option to edit.");
                    alert.setHeaderText(null);

                    // Add the buttons
                    ButtonType button1 = new ButtonType("Change Password");
                    ButtonType button2 = new ButtonType("Change Username");
                    ButtonType button3 = new ButtonType("Cancel");
                    alert.getButtonTypes().setAll(button1, button2);

                    // Handle button events
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent()) {
                        if (result.get() == button1) {
                            // Button 1 event handling
                            // Add your code here

                            // Change the tab to Change User
                            DashboardTabPane.getSelectionModel().select(ChangeUserTab);

                            // Set the text field to the selected user
                            OldUserField.setText(selectedUser.getUsername());

                            // Create scale transitions
                            JavafxAnimations animations = new JavafxAnimations();
                            Pane [] panes = {ChangeUserPane, ChangeUserBG, ChangeUsersPane};
                            animations.ChangeUserFX(panes);
                        } else if (result.get() == button2) {
                            // Button 2 event handling
                            // Add your code here
                            // Change the tab to Change Password
                            DashboardTabPane.getSelectionModel().select(ChangePassTab);

                            // Set the text field to the selected user
                            UsernameFields.setText(selectedUser.getUsername());

                            // Create scale transitions
                            JavafxAnimations animations = new JavafxAnimations();
                            Pane [] panes = {ChangePassPane, ChangePassBG, ChangePassTabPane};
                            animations.ChangePassFX(panes);


                        } else if (result.get() == button3) {
                            // Button 3 event handling
                            // Add your code here
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Information");
                            alert2.setHeaderText(null);
                            alert2.setContentText("You have cancelled the edit operation");
                            alert2.showAndWait();
                        }


                    }

                }
            }
        });
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            // Set style class for the button based on buttonText
            String buttonText = getButton().getText();
            if ("Delete".equals(buttonText)) {
                button.getStyleClass().add("jfx-buttoncell");
            } else if ("Edit".equals(buttonText)) {
                button.getStyleClass().add("jfx-buttoncell");
            }

            setGraphic(button);
        }
    }

    public Button getButton() {
        return button;
    }
}