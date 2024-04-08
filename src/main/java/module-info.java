module com.example.pooproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;

    exports edu.unam.pooproject.controller;
    opens edu.unam.pooproject to javafx.fxml;
    exports edu.unam.pooproject;
    opens edu.unam.pooproject.controller to javafx.fxml;
    opens edu.unam.pooproject.modelo;
    opens edu.unam.pooproject.repositorio;
}