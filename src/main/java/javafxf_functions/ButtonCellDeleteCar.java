package javafxf_functions;

import DatabaseFunction.DeleteDataFromMYSQL;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;
public class ButtonCellDeleteCar extends TableCell<CarImage, Void> {
    private final Button button;

    private TableView<CarImage> CarView;

    private TableView<CarImage> CarView1;

    private ObservableList<CarImage> CarList;

    // Constructor for the ButtonCell
     public ButtonCellDeleteCar (String buttonText, TableView<CarImage> CarView, ObservableList<CarImage> CarList, TableView <CarImage> CarView1 ) {
        this.button = new Button(buttonText);
        this.CarView = CarView;
        this.CarList = CarList;
        this.CarView1 = CarView1;

        this.button.setOnAction(event -> {
            CarImage SelectedCar = getTableRow().getItem();
            if (SelectedCar  != null) {
                if (buttonText.equals("Delete Car")) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Delete Car");
                    alert.setContentText("Are you sure you want to delete this car?");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {

                            // Delete car from database
                            DeleteDataFromMYSQL.DeleteCar( SelectedCar.getCarID());
                            CarList.remove(SelectedCar);
                            CarView.refresh();
                            CarView1.refresh();
                        }
                    });




                }
                // Rent Car function
                 else if (buttonText.equals("Rent Car")) {

                     System.out.println("Rent Car");

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
            if ("Delete Car".equals(buttonText)) {
                button.getStyleClass().add("jfx-buttoncell");
            } else if ("Rent Car".equals(buttonText)) {
                button.getStyleClass().add("jfx-buttoncell");
            }

            setGraphic(button);
        }
    }


    // Static method to create a callback for the table column
    public static Callback<TableColumn<UserTable, Void>, TableCell<CarImage, Void>> forTableColumn(String buttonText , TableView<CarImage> CarView, ObservableList<CarImage> CarList, TableView <CarImage> CarView1) {
        return param -> new ButtonCellDeleteCar (buttonText , CarView, CarList, CarView1);
    }
}

