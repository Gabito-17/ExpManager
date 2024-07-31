package edu.unam.pooproject.controller;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import edu.unam.pooproject.Services.ExpedienteServicio;
import edu.unam.pooproject.Services.AccionServicio;
import edu.unam.pooproject.Services.VentanaEmergente;
import edu.unam.pooproject.db.Conexion;
import edu.unam.pooproject.modelo.Accion;
import edu.unam.pooproject.modelo.Expediente;
import edu.unam.pooproject.modelo.Persona;
import edu.unam.pooproject.repositorio.Repositorio;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AccionController extends NavegacionController {

    private Repositorio repositorio;
    private AccionServicio accionServicio;
    private ExpedienteServicio expedienteServicio;
    private VentanaEmergente ventana = new VentanaEmergente();
    private Accion accionSeleccionada = null;
    private Integer idExpediente;
    private boolean accionExiste;
    private Integer idExpedienteFijo;

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
        //Instancia una accion
        Accion accion = new Accion();
        //verificar que el campo Fecha sea valido
        if (fechaAccion.getValue() == null) {
            ventana.mostrarError("Error al cargar la accion, debe seleccionar una fecha.");
            return;
        } else if (fechaAccion.getValue().isBefore(LocalDate.now())) {
            ventana.mostrarError("Error al cargar la accion, la fecha ingresada debe ser posterior o igual a la de hoy.");
            return;
        }
        //asignar fecha de la accion
        accion.setFecha(fechaAccion.getValue());
        // Asignar Titulo
        accion.setTitulo(txtTituloAccion.getText().trim());
        if (!verificarTituloAccion()) {
            return; // Detener la carga si el Titulo no es valido
        }
        // Asignar Accion
        accion.setAccion(taAccion.getText().trim());
        if (!verificarTituloAccion()) {
            return; // Detener la carga si el Titulo no es valido
        }
        if (accionExiste) { // Si la accion ya existe, editarla
            this.accionServicio.editarAccion(accion);
        } else { // Si la accion no existe, agregarla
            this.accionServicio.agregarAccion(accion);
        }
        rellenarTabla(idExpedienteFijo);
        limpiarCampos();
    }

    @FXML
    void editarAccion(ActionEvent event) {
        // Obtener la persona seleccionada en el TableView
        this.accionSeleccionada = tvAccionesPorExpediente.getSelectionModel().getSelectedItem();

        // Verificar si hay alguna persona seleccionada
        if (accionSeleccionada == null) {
            ventana.mostrarError("Selecciona una accion para editar.");
            return;
        }
        // Cargar los datos de la persona seleccionada en los campos correspondientes
        Optional<ButtonType> resultado = ventana.mostrarConfirmacion("¿Deseas editar esta accion?", "Se editarán los datos de la accion seleccionada.\n Al finalizar la edicion presione 'Cargar'");
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            fechaAccion.setValue(accionSeleccionada.getFecha());
            txtTituloAccion.setText(accionSeleccionada.getTitulo());
            taAccion.setText(accionSeleccionada.getAccion());
            accionExiste = true;
        }
        
    }

    @FXML
    void eliminarAccion(ActionEvent event) {
        // Obtener el expediente seleccionado en la tabla
        this.accionSeleccionada = tvAccionesPorExpediente.getSelectionModel().getSelectedItem();

        // Verificar si hay algun expediente seleccionado
        if (accionSeleccionada == null) {
            ventana.mostrarError("Debe seleccionar una accion de la tabla para eliminarlo");
            return;
        }

        // Mostrar un cuadro de diálogo de confirmación
        Alert alertConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmacion.setTitle("Confirmar");
        alertConfirmacion.setHeaderText("¿Estás seguro de eliminar esta accion?");
        alertConfirmacion.setContentText("Se eliminará permanentemente a la accion seleccionada.");
        Optional<ButtonType> resultado = alertConfirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Eliminar el expediente seleccionado
            this.accionServicio.eliminarAccion(accionSeleccionada);
            // Actualizar la tabla
            rellenarTabla(idExpedienteFijo);
        }
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
        this.accionServicio = new AccionServicio(this.repositorio);

        // Configurar las columnas del TableView para que muestren los datos de las Acciones
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        colTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colAccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAccion()));

    }

    public void setExpedienteId(int expedienteId) {
        this.idExpediente = expedienteId;
        this.idExpedienteFijo = expedienteId;
        // Cargar todas las acciones de la base de datos y mostrarlas en el TableView
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

    private void limpiarCampos() {
        fechaAccion.setValue(null);
        txtTituloAccion.clear();
        taAccion.clear();
    }

    private boolean verificarTituloAccion() {
        String titulo = txtTituloAccion.getText().trim();
        String accion = taAccion.getText().trim();
        if (!titulo.isEmpty() || !accion.isEmpty()) {
            if ((titulo.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) || (accion.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"))) { // Solo permite letras, espacios y letras acentuadas
                return true;
            } else {
                ventana.mostrarError("El titulo o la accion no pueden contener símbolos ni números.");
                return false;
            }
        } else {
            ventana.mostrarError("Por favor ingrese texto en el campo.");
            return false;
        }
    }

}
