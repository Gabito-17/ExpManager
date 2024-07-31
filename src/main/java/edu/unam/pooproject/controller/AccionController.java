package edu.unam.pooproject.controller;
import java.time.LocalDate;
import java.util.List;

import edu.unam.pooproject.Services.ExpedienteServicio;
import edu.unam.pooproject.Services.AccionServicio;
import edu.unam.pooproject.Services.VentanaEmergente;
import edu.unam.pooproject.db.Conexion;
import edu.unam.pooproject.modelo.Accion;
import edu.unam.pooproject.modelo.Expediente;
import edu.unam.pooproject.repositorio.Repositorio;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AccionController extends NavegacionController {

    private Repositorio repositorio;
    private AccionServicio accionServicioServicio;
    private ExpedienteServicio expedienteServicio;
    private VentanaEmergente ventana = new VentanaEmergente();
    private Accion accionSeleccionada = null;
    private Integer idExpediente;

    @FXML
    private Button btnCargar;

    @FXML
    private Button btnEditarAccion;

    @FXML
    private Button btnEliminarAccion;

    @FXML
    private Button btnLimpiarCampos;

    @FXML
    private Button btnMenuAsistencia;

    @FXML
    private Button btnMenuExpedientes;

    @FXML
    private Button btnMenuInicio;

    @FXML
    private Button btnMenuMiembros;

    @FXML
    private Button btnMenuMinutas;

    @FXML
    private Button btnMenuReuniones;

    @FXML
    private TableColumn<Accion, Integer> colId;

    @FXML
    private TableColumn<Accion, String> colFecha;

    @FXML
    private TableColumn<Accion, String> colTitulo;

    @FXML
    private TableColumn<Accion, String> colAccion;

    @FXML
    private DatePicker fechaAccion;

    @FXML
    private TextArea taAccion;

    @FXML
    private TableView<Accion> tvAccionesPorExpediente;

    @FXML
    private TextField txtTituloAccion;

    @FXML
    private Label lbExpedienteTitulo;

    @FXML
    void cargarAccion(ActionEvent event) {

    }

    @FXML
    void editarAccion(ActionEvent event) {

    }

    @FXML
    void eliminarAccion(ActionEvent event) {

    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        fechaAccion.setValue(null);
        txtTituloAccion.clear();
        taAccion.clear();
    }

    @FXML
    public void initialize() {
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.accionServicioServicio = new AccionServicio(this.repositorio);

        // Configurar las columnas del TableView para que muestren los datos de las Acciones
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        colTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colAccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAccion()));
        // Cargar todas las acciones de la base de datos y mostrarlas en el TableView
        rellenarTabla(idExpediente);
    }

    public void setExpedienteId(int expedienteId) {
        this.idExpediente = expedienteId;
        rellenarTabla(expedienteId);
    }

    private void rellenarTabla(int expedienteId) {
        Expediente expediente = expedienteServicio.buscarPorId(expedienteId);
        lbExpedienteTitulo.setText(expediente.getTitulo());
        if (expediente != null) {
            List<Accion> acciones = expediente.getAcciones();
            ObservableList<Accion> listaAcciones = FXCollections.observableArrayList(acciones);
            tvAccionesPorExpediente.setItems(listaAcciones);
            System.out.println(listaAcciones);
        }
    }



}
