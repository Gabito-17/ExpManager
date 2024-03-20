module com.example.pooproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;

    exports edu.unam.pooproject.Controller;
    opens edu.unam.pooproject to javafx.fxml;
    opens edu.unam.pooproject.modelo;
    exports edu.unam.pooproject;
    opens edu.unam.pooproject.Controller to javafx.fxml;
}