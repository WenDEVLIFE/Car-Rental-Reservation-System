package javafxf_functions;

import DatabaseFunction.DeleteDataFromMYSQL;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;

// This class is used to create a button in a table cell
public class ButtonCalendar extends TableCell<TaskTable, Void> {
    private final Button button;

    private TableView <TaskTable> TaskView;

    private ObservableList<TaskTable> TaskList;

    // Constructor for the ButtonCell
    public ButtonCalendar(String buttonText, TableView<TaskTable> TaskView, ObservableList<TaskTable> TaskList) {
        this.button = new Button(buttonText);
        this.TaskView = TaskView;
        this.TaskList = TaskList;

        this.button.setOnAction(event -> {
            TaskTable SelectedTask = getTableRow().getItem();
            if (SelectedTask != null) {
                if (buttonText.equals("Done Task")) {
                    // Code for Done Task

                            // Code to handle user deletion
                            getTableView().getItems().remove(SelectedTask);
                            // Remove the user from the database or perform any other deletion logic

                            DeleteDataFromMYSQL deleteDataFromMYSQL = new DeleteDataFromMYSQL();
                            deleteDataFromMYSQL.DeleteTask(SelectedTask);

                            TaskList.remove(SelectedTask);
                            TaskView.refresh();

                            Alert  alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("System Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Task Done successfully");


                } else if (buttonText.equals("Delete Task")) {
                    // You may want to open a new dialog or switch to another view here
                    // For example, show an edit dialog or switch to the edit user view
                    // Code for deleting user
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete Task");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this user?");

                    ButtonType buttonTypeYes = new ButtonType("Yes");
                    ButtonType buttonTypeNo = new ButtonType("No");

                    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                    alert.showAndWait().ifPresent(response -> {
                        if (response == buttonTypeYes) {
                            // Code to handle user deletion
                            getTableView().getItems().remove(SelectedTask);
                            // Remove the user from the database or perform any other deletion logic

                            DeleteDataFromMYSQL deleteDataFromMYSQL = new DeleteDataFromMYSQL();
                            deleteDataFromMYSQL.DeleteTask(SelectedTask);

                            TaskList.remove(SelectedTask);
                            TaskView.refresh();


                        }
                    });
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
            String buttonText = button.getText();
            if ("Done Task".equals(buttonText) || "Delete Task".equals(buttonText)) {
                button.getStyleClass().add("jfx-button");
            }

            setGraphic(button);
        }
    }


    // Static method to create a callback for the table column
    public static Callback<TableColumn<UserTable, Void>, TableCell<TaskTable, Void>> forTableColumn(String buttonText, TableView<TaskTable> TaskView, ObservableList<TaskTable> taskList) {
        return param -> new ButtonCalendar(buttonText , TaskView, taskList );
    }
}