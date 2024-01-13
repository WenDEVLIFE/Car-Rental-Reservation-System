package com.example.car_rental_reservation_system;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageTableCell extends javafx.scene.control.TableCell<javafxf_functions.CarImage, javafx.scene.image.Image> {
    private final ImageView imageView;

    public ImageTableCell() {
        this.imageView = new ImageView();
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
    }

    @Override
    protected void updateItem(Image item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        } else {
            imageView.setImage(item);
            setGraphic(imageView);
        }
    }

}