package edu.unam.pooproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MinutaController extends NavegacionController{

    @FXML
    private Button btnCargar;

    @FXML
    private Button btnLimpiar;

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
    private TableColumn<?, ?> colTema;

    @FXML
    private Label lbIdExpediente;

    @FXML
    private Label lbIdMinuta;

    @FXML
    private Label lbIdReunion;

    @FXML
    private TextArea taResumen;

    @FXML
    private TableView<?> tvMinutasDeLaReunion;

    @FXML
    private TextField txtTema;

    @FXML
    void cargarMinuta(ActionEvent event) {

    }

    @FXML
    void limpiarCampos(ActionEvent event) {

    }

}
