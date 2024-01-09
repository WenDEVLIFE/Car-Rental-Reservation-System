module com.example.car_rental_reservation_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.car_rental_reservation_system to javafx.fxml;
    exports com.example.car_rental_reservation_system;
    exports javafxf_functions;
    opens javafxf_functions to javafx.fxml;
}