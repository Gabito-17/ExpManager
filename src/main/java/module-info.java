module com.example.pooproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pooproject to javafx.fxml;
    exports com.example.pooproject;
}