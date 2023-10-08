module com.example.pizzeria {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.pizzeria to javafx.fxml;
    exports com.example.pizzeria;
    exports com.example.pizzeria.controles;
    opens com.example.pizzeria.controles to javafx.fxml;
    exports com.example.pizzeria.clases;
    opens com.example.pizzeria.clases to javafx.fxml;
}