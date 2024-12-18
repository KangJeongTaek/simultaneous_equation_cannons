module simultaneous_equation_cannons {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires org.kordamp.bootstrapfx.core;

    exports com.simultaneous_equation_cannons;
    opens com.simultaneous_equation_cannons to javafx.fxml;
}