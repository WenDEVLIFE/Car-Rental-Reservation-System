package javafxf_functions;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
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
        }
        return null;
    }
}
