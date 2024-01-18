package javafx_table_buttons;

import DatabaseFunction.DeleteDataFromMYSQL;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx_table_functions.Report;
import javafx_table_functions.UserTable;

public class ButtonReport extends TableCell<Report, Void> {
    private final Button button;

    private TableView<Report> ReportView;

    ObservableList< Report > ReportList;
    public ButtonReport(String buttonText, TableView < Report > ReportView, ObservableList < Report > ReportList) {
        this.button = new Button(buttonText);
        this.ReportView = ReportView;
        this.ReportList = ReportList;
        this.button.setOnAction(event -> {
            Report selectedreport = getTableRow().getItem();
            if (selectedreport != null) {
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

                        }
                    });
                } else if (buttonText.equals("Edit")) {
                    // Code for editing user
                    // You may want to open a new dialog or switch to another view here
                    // For example, show an edit dialog or switch to the edit user view
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

