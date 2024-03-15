module com.example.pooproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;

    exports com.example.pooproject.Controller;
    opens com.example.pooproject to javafx.fxml;
    exports com.example.pooproject;
    opens com.example.pooproject.Controller to javafx.fxml;
}