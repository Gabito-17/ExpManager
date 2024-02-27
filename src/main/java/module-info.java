module com.example.pooproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pooproject to javafx.fxml;
    exports com.example.pooproject;
}