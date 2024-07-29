package edu.unam.pooproject.controller;

import java.util.List;

import edu.unam.pooproject.Services.ExpedienteServicio;
import edu.unam.pooproject.Services.MinutaServicio;
import edu.unam.pooproject.Services.ReunionServicio;
import edu.unam.pooproject.db.Conexion;
import edu.unam.pooproject.modelo.Expediente;
import edu.unam.pooproject.modelo.Minuta;
import edu.unam.pooproject.modelo.Reunion;
import edu.unam.pooproject.repositorio.Repositorio;
import jakarta.persistence.Id;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MinutaController extends NavegacionController{
    private Repositorio repositorio;
    private MinutaServicio minutaServicio;
    private ExpedienteServicio expedienteServicio;
    private ReunionServicio reunionServicio;

    @FXML
    private Button btnCargar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button editarMinuta;

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
    private TableColumn<Expediente, String> colExpediente;

    @FXML
    private TableColumn<Reunion, String> colFecha;

    @FXML
    private TableColumn<Minuta, Integer> colId;

    @FXML
    private TableColumn<Minuta, String> colResumen;

    @FXML
    private TableColumn<Minuta, String> colTema;

    @FXML
    private Label lbIdExpediente;

    @FXML
    private Label lbIdMinuta;

    @FXML
    private Label lbIdReunion;

    @FXML
    private TextArea taResumen;

    @FXML
    private TableView<Minuta> tvMinutasDeLaReunion;

    @FXML
    private TextField txtTema;

    public void initialize() {
        //lbIdExpediente = this.;
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.minutaServicio = new MinutaServicio(this.repositorio);
        this.expedienteServicio = new ExpedienteServicio(this.repositorio);
        this.reunionServicio = new ReunionServicio(this.repositorio);
        // Configurar las propiedades de las columnas
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().reunion.getFecha().toString()));
        colExpediente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().expediente.getNombre().toString()));
        colTema.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTema().toString()));
        colResumen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getResumen().toString()));
        rellenarTablas();
    }

    @FXML
    void cargarMinuta(ActionEvent event) {
        Minuta minuta = new Minuta();
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
    // Limpiar campos
        txtTema.clear();
        taResumen.clear();
    }

    @FXML
    void editarMinuta(ActionEvent event) {

    }

    void rellenarTablas() {
        // Obtener todas las personas de la base de datos a través del servicio
        List<Minuta> minutas = minutaServicio.obtenerTodos();

        // Convertir la lista de personas en una ObservableList
        ObservableList<Minuta> listaMinutas = FXCollections.observableArrayList(minutas);

        // Asignar la lista de personas al TableView
        tvMinutasDeLaReunion.setItems(listaMinutas);
    }
    

}
