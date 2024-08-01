package edu.unam.pooproject.controller;

import edu.unam.pooproject.Services.AccionServicio;
import edu.unam.pooproject.Services.ExpedienteServicio;
import edu.unam.pooproject.Services.VentanaEmergente;
import edu.unam.pooproject.db.Conexion;
import edu.unam.pooproject.modelo.Accion;
import edu.unam.pooproject.modelo.Expediente;
import edu.unam.pooproject.repositorio.Repositorio;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AccionController extends NavegacionController {

    private Repositorio repositorio;
    private AccionServicio accionServicio;
    private ExpedienteServicio expedienteServicio;
    private VentanaEmergente ventana = new VentanaEmergente();
    private Accion accionSeleccionada = null;
    private Expediente expediente;
    private Integer idAccion;

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
        if (idAccion != null) {
            Accion accionEditada = this.accionServicio.buscarPorId(idAccion);
            if (!verificarCamposAccion()) {
                return;
            }
            accionEditada.setFecha(fechaAccion.getValue());
            accionEditada.setTitulo(txtTituloAccion.getText().trim());
            accionEditada.setAccion(taAccion.getText().trim());
            this.accionServicio.editarAccion(accionEditada);
        } else {
            Accion accion = new Accion();
            accion.setExpediente(expediente);
            if (!verificarCamposAccion()) {
                return;
            }
            accion.setFecha(fechaAccion.getValue());
            accion.setTitulo(txtTituloAccion.getText().trim());
            accion.setAccion(taAccion.getText().trim());
            this.accionServicio.agregarAccion(accion);
        }
        actualizarExpediente();
        actualizarTabla();
        limpiarCampos();
        initialize();
    }

    @FXML
    void eliminarAccion(ActionEvent event) {
        this.accionSeleccionada = tvAccionesPorExpediente.getSelectionModel().getSelectedItem();

        if (accionSeleccionada == null) {
            ventana.mostrarError("Debe seleccionar una accion de la tabla para eliminarlo");
            return;
        }

        Alert alertConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmacion.setTitle("Confirmar");
        alertConfirmacion.setHeaderText("¿Estás seguro de eliminar esta accion?");
        alertConfirmacion.setContentText("Se eliminará permanentemente a la accion seleccionada.");
        Optional<ButtonType> resultado = alertConfirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            this.accionServicio.eliminarAccion(accionSeleccionada);
            actualizarExpediente();
            actualizarTabla();
        }
    }

    @FXML
    void editarAccion(ActionEvent event) {
        this.accionSeleccionada = tvAccionesPorExpediente.getSelectionModel().getSelectedItem();

        if (accionSeleccionada == null) {
            ventana.mostrarError("Selecciona una accion para editar.");
            return;
        }

        Optional<ButtonType> resultado = ventana.mostrarConfirmacion("¿Deseas editar esta accion?", "Se editarán los datos de la accion seleccionada.\n Al finalizar la edicion presione 'Cargar'");
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            fechaAccion.setValue(accionSeleccionada.getFecha());
            txtTituloAccion.setText(accionSeleccionada.getTitulo());
            taAccion.setText(accionSeleccionada.getAccion());
            this.idAccion = accionSeleccionada.getId();
        }
    }

    private void actualizarExpediente() {
        if (expediente != null) {
            expediente = expedienteServicio.buscarPorId(expediente.getId());
        }
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    public void initialize() {
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.accionServicio = new AccionServicio(this.repositorio);
        this.expedienteServicio = new ExpedienteServicio(this.repositorio);

        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        colTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colAccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAccion()));
        if (expediente != null) {
            lbExpedienteTitulo.setText(expediente.getTitulo());
            rellenarTabla(expediente);
        }
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
        if (expediente != null) {
            lbExpedienteTitulo.setText(expediente.getTitulo());
            rellenarTabla(expediente);
        }
    }

    private void rellenarTabla(Expediente expediente) {
        if (expediente != null) {
            List<Accion> acciones = expediente.getAcciones();
            ObservableList<Accion> listaAcciones = FXCollections.observableArrayList(acciones);
            tvAccionesPorExpediente.setItems(listaAcciones);
            tvAccionesPorExpediente.refresh();
        }
    }

    private void limpiarCampos() {
        fechaAccion.setValue(null);
        txtTituloAccion.clear();
        taAccion.clear();
        idAccion = null;
    }

    private boolean verificarCamposAccion() {
        if (fechaAccion.getValue() == null) {
            ventana.mostrarError("Error al cargar la accion, debe seleccionar una fecha.");
            return false;
        } else if (fechaAccion.getValue().isBefore(LocalDate.now())) {
            ventana.mostrarError("Error al cargar la accion, la fecha ingresada debe ser posterior o igual a la de hoy.");
            return false;
        }
        String titulo = txtTituloAccion.getText().trim();
        String accion = taAccion.getText().trim();
        if (titulo == null || accion == null || titulo.isEmpty() || accion.isEmpty()) {
            ventana.mostrarError("Por favor ingrese texto en los campos.");
            return false;
        }
        if (!titulo.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ,.() ]+") || !accion.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ,.() ]+")) {
            ventana.mostrarError("El titulo o la accion no pueden contener símbolos ni números.");
            return false;
        }
        return true;
    }

    private void actualizarTabla() {
        if (expediente != null) {
            expediente = expedienteServicio.buscarPorId(expediente.getId());
            List<Accion> acciones = expediente.getAcciones();
            ObservableList<Accion> listaAcciones = FXCollections.observableArrayList(acciones);
            tvAccionesPorExpediente.setItems(listaAcciones);
            tvAccionesPorExpediente.refresh();
        }
    }
}
