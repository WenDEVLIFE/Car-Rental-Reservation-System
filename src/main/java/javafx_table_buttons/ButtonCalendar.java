package javafx_table_buttons;

import DatabaseFunction.DeleteDataFromMYSQL;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx_table_functions.TaskTable;
import javafx_table_functions.UserTable;

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
            TaskTable selectedTask = getTableRow().getItem();
            if (selectedTask != null) {
                if (buttonText.equals("Done Task")) {
                    // Code for Done Task

                    // Code to handle task completion
                    Platform.runLater(() -> {
                        ObservableList<TaskTable> taskItems = FXCollections.observableArrayList(
                                getTableView().getItems()
                        );

                        taskItems.remove(selectedTask);

                        DeleteDataFromMYSQL deleteDataFromMYSQL = new DeleteDataFromMYSQL();
                        deleteDataFromMYSQL.DeleteTask(selectedTask);

                        TaskList.remove(selectedTask);

                        TaskView.setItems(taskItems);
                        TaskView.refresh();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("System Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Task Done successfully");
                        alert.showAndWait();
                    });
                } else if (buttonText.equals("Delete Task")) {
                    // Code for deleting task
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Delete Task");
                        alert.setHeaderText(null);
                        alert.setContentText("Are you sure you want to delete this task?");

                        ButtonType buttonTypeYes = new ButtonType("Yes");
                        ButtonType buttonTypeNo = new ButtonType("No");

                        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                        alert.showAndWait().ifPresent(response -> {

                            // Code to handle task deletion
                            if (response == buttonTypeYes) {
                                // Then collect all the data from the database
                                ObservableList<TaskTable> taskItems = FXCollections.observableArrayList(
                                        getTableView().getItems()
                                );

                                // Then update the Observerlist
                                taskItems.remove(selectedTask);

                                // Call the database controller
                                DeleteDataFromMYSQL deleteDataFromMYSQL = new DeleteDataFromMYSQL();
                                deleteDataFromMYSQL.DeleteTask(selectedTask);

                                // Then update the table
                                TaskList.remove(selectedTask);

                                TaskView.setItems(taskItems);
                                TaskView.refresh();
                            }
                        });
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
                button.getStyleClass().add("jfx-buttoncell");
            }

            setGraphic(button);
        }
    }


    // Static method to create a callback for the table column
    public static Callback<TableColumn<UserTable, Void>, TableCell<TaskTable, Void>> forTableColumn(String buttonText, TableView<TaskTable> TaskView, ObservableList<TaskTable> TaskList) {
        return param -> new ButtonCalendar(buttonText , TaskView, TaskList );
    }
}