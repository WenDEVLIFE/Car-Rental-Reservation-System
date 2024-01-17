package javafx_table_buttons;

import DatabaseFunction.RentMYSQL_DATABASE;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx_table_functions.CarImage;
import javafx_table_functions.CarImage2;
import javafx_table_functions.UserTable;

public class ButtonCellDeleteCar1 extends TableCell<CarImage2, Void> {
    private final Button button;

    private TableView<CarImage2> CarView2;

    private ObservableList<CarImage2> RentedCars;

    private TableView<CarImage> CarView1;

    private ObservableList<CarImage> CarList;

    private TableView<CarImage> CarView;

    // Constructor for the ButtonCell
    public ButtonCellDeleteCar1(String buttonText, TableView<CarImage2> CarView2, ObservableList<CarImage2> RentedCars, TableView<CarImage> CarView1, ObservableList<CarImage> CarList, TableView<CarImage> CarView) {
        this.button = new Button(buttonText);
        this.CarView2 = CarView2;
        this.RentedCars = RentedCars;
        this.CarView1 = CarView1;
        this.CarList = CarList;
        this.CarView = CarView;

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

                            String carname = SelectedCar.getCarName().get();
                            System.out.println(carname);
                            RentMYSQL_DATABASE rentMYSQL_database = new RentMYSQL_DATABASE();
                            rentMYSQL_database.MovetheCarBacktoRent(carname,CarView1,CarList,CarView);
                            RentedCars.remove(SelectedCar);
                            CarView2.refresh();



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
    public static Callback<TableColumn<UserTable, Void>, TableCell<CarImage2, Void>> forTableColumn(String buttonText , TableView<CarImage2> CarView2, ObservableList<CarImage2> RentedCars, TableView<CarImage> CarView1, ObservableList<CarImage> CarList, TableView<CarImage> CarView) {
        return param -> new ButtonCellDeleteCar1 (buttonText , CarView2, RentedCars, CarView1, CarList, CarView);
    }
}
