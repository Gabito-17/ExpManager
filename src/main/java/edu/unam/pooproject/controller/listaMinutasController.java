package edu.unam.pooproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class listaMinutasController extends NavegacionController{

    @FXML
    private Button btnMenuAsistencia;

    @FXML
    private Button btnMenuExpedientes;

    @FXML
    private Button btnMenuMiembros;

    @FXML
    private Button btnMenuMinutas;

    @FXML
    private Button btnMenuPersonas;

    @FXML
    private Button btnMenuReuniones;

    @FXML
    private TableColumn<?, ?> colAcciones;

    @FXML
    private TableColumn<?, ?> colExpediente;

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colResumen;

    @FXML
    private TableColumn<?, ?> colReunion;

    @FXML
    private TableColumn<?, ?> colTema;

    @FXML
    private TableView<?> tvMinutas;

}
