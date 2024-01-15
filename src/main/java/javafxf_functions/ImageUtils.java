package javafxf_functions;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class ImageUtils {

    public static Image convertBlobToImage(Blob blob) {
        try {
            if (blob != null) {
                byte[] bytes = blob.getBytes(1, (int) blob.length());
                return new Image(new ByteArrayInputStream(bytes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, such as logging or returning a default Image
        }
        return getDefaultImage();
    }
    private static Image getDefaultImage() {
        try {
            // Attempt to load the default image from resources
            InputStream stream = ImageUtils.class.getResourceAsStream("/100x100.png");
            if (stream != null) {
                return new Image(stream);
            } else {
                // If loading from resources fails, use a placeholder image
                return new Image("https://via.placeholder.com/100x100");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Log the exception or handle it appropriately
        }
        return null;
    }
}
