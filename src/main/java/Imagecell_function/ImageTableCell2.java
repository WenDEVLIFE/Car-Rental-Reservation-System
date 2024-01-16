package Imagecell_function;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx_table_functions.CarImage2;

public class ImageTableCell2 extends javafx.scene.control.TableCell<CarImage2, javafx.scene.image.Image> {
    private final ImageView imageView;

    public ImageTableCell2() {
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