package javafxf_functions;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;

public class ButtonCellDeleteCar1 extends TableCell<CarImage2, Void> {
    private final Button button;

    private TableView<CarImage2> CarView2;

    private ObservableList<CarImage2> RentedCars;


    // Constructor for the ButtonCell
    public ButtonCellDeleteCar1(String buttonText, TableView<CarImage2> CarView2, ObservableList<CarImage2> RentedCars) {
        this.button = new Button(buttonText);
        this.CarView2 = CarView2;
        this.RentedCars = RentedCars;


        this.button.setOnAction(event -> {
            CarImage2 SelectedCar = getTableRow().getItem();
            if (SelectedCar != null) {
                if (buttonText.equals("Moved to Rented")) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Moved Car");
                    alert.setContentText("Are you sure you want to approve this car?");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {

                            // Delete car from database

                        }
                    });

                }
                else if (buttonText.equals("Delete Car")) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Delete Car");
                    alert.setContentText("Are you sure you want to delete this car?");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {

                            // Delete car from database

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
            if ("Moved to Rented".equals(buttonText)) {
                button.getStyleClass().add("jfx-buttoncell");
            } else if ("Delete Car".equals(buttonText)) {
                button.getStyleClass().add("jfx-buttoncell");
            }

            setGraphic(button);
        }
    }


    // Static method to create a callback for the table column
    public static Callback<TableColumn<UserTable, Void>, TableCell<CarImage2, Void>> forTableColumn(String buttonText , TableView<CarImage2> CarView2, ObservableList<CarImage2> RentedCars) {
        return param -> new ButtonCellDeleteCar1 (buttonText , CarView2, RentedCars);
    }
}
