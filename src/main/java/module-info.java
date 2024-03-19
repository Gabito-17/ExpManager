module com.example.pooproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;

    exports com.example.pooproject.Controller;
    opens com.example.pooproject to javafx.fxml;
    opens com.example.pooproject.modelo;
    exports com.example.pooproject;
    opens com.example.pooproject.Controller to javafx.fxml;
}