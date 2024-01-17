module com.example.car_rental_reservation_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;
    requires itextpdf;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.example.car_rental_reservation_system to javafx.fxml;
    exports com.example.car_rental_reservation_system;
    exports javafxf_functions;
    opens javafxf_functions to javafx.fxml;
    exports DatabaseFunction;
    opens DatabaseFunction to javafx.fxml;
    exports javafx_table_functions;
    opens javafx_table_functions to javafx.fxml;
    exports Imagecell_function;
    opens Imagecell_function to javafx.fxml;
    exports javafx_table_buttons;
    opens javafx_table_buttons to javafx.fxml;
}