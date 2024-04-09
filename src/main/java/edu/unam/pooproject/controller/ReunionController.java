package edu.unam.pooproject.controller;

import edu.unam.pooproject.Services.Enrutador;
import edu.unam.pooproject.Services.ExpedienteServicio;
import edu.unam.pooproject.Services.PersonaServicio;
import edu.unam.pooproject.Services.ReunionServicio;
import edu.unam.pooproject.db.Conexion;
import edu.unam.pooproject.modelo.Expediente;
import edu.unam.pooproject.modelo.Persona;
import edu.unam.pooproject.modelo.Reunion;
import edu.unam.pooproject.repositorio.Repositorio;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;

import java.util.List;

public class ReunionController {

    private Repositorio repositorio;
    private PersonaServicio personaServicio;
    private ExpedienteServicio expedienteServicio;
    private ReunionServicio reunionServicio;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private ComboBox cmbHoraInicio;
    @FXML
    private ComboBox cmbHoraFin;
    @FXML
    private ComboBox cmbMiembros;
    @FXML
    private ComboBox cmbExpedientes;
    @FXML
    private ListView<Persona> lstMiembros;

    @FXML
    private TextField txtLugar;
    @FXML
    private TextArea taDetalles;
    @FXML
    private ListView<Expediente> lstExpedientes;
    @FXML
    private TableView<Reunion> tvReunion;

    @FXML
    private TableColumn<Reunion, Integer> colId;
    @FXML
    private TableColumn<Reunion, String> colFecha;
    @FXML
    private TableColumn<Reunion, String> colHoraInicio;
    @FXML
    private TableColumn<Reunion, String> colHoraFin;
    @FXML
    private TableColumn<Reunion, Boolean> colEstado;
    @FXML
    private TableColumn<Reunion, String> colDetalles;
    private ObservableList<Persona> listaMiembros = FXCollections.observableArrayList();
    private ObservableList<Expediente> listaExpedientes = FXCollections.observableArrayList();


    public void initialize() {
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.personaServicio = new PersonaServicio(this.repositorio);
        this.expedienteServicio = new ExpedienteServicio(this.repositorio);
        this.reunionServicio = new ReunionServicio(this.repositorio);
        // Configurar las propiedades de las columnas
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        colHoraFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraFin().toString()));
        colHoraInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraInicio().toString()));
        colDetalles.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDetalles().toString()));

        rellenarTabla();

        //CargarComboBoxes
        cargarComboBoxHorarios();
        cargarComboBoxMiembros();
        cargarComboBoxExpedientes();
    }

    private void rellenarTabla() {
        // Obtener todas las personas de la base de datos a través del servicio
        List<Reunion> reuniones = reunionServicio.obtenerTodos();

        // Convertir la lista de personas en una ObservableList
        ObservableList<Reunion> listaReuniones = FXCollections.observableArrayList(reuniones);

        // Asignar la lista de personas al TableView
        tvReunion.setItems(listaReuniones);
    }

    private void cargarComboBoxHorarios() {
        // Crear una lista de horarios
        ObservableList<String> horarios = FXCollections.observableArrayList(
                "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
                "16:00 PM", "17:00 PM", "18:00 PM", "19:00 PM", "20:00 PM"
        );
        // Establecer la lista de horarios en el ComboBox
        cmbHoraInicio.setItems(horarios);
        cmbHoraFin.setItems(horarios);
    }

    @FXML
    public void verificarHoraFin() {
        if (cmbHoraInicio.getSelectionModel().isEmpty()) {
            return;
        } else if (!cmbHoraFin.getSelectionModel().isEmpty()) {
            if (cmbHoraFin.getSelectionModel().getSelectedIndex() <= cmbHoraInicio.getSelectionModel().getSelectedIndex()) {
                mostrarError("La hora de inicio de la reunion debe ser anterior a la hora de finalizacion");
                cmbHoraFin.getSelectionModel().clearSelection();
                cargarComboBoxHorarios();
            }
        }
    }

    @FXML
    public void verificarHoraInicio() {
        if (cmbHoraFin.getSelectionModel().isEmpty()) {
            return;
        } else if (!cmbHoraFin.getSelectionModel().isEmpty()) {
            if (cmbHoraFin.getSelectionModel().getSelectedIndex() <= cmbHoraInicio.getSelectionModel().getSelectedIndex()) {
                mostrarError("La hora de inicio de la reunion debe ser anterior a la hora de finalizacion");
                cmbHoraInicio.getSelectionModel().clearSelection();
                cargarComboBoxHorarios();
            }
        }
    }

    private void cargarComboBoxExpedientes() {

        // Crear lista a partir de las personas registradas
        List<Expediente> expedientes = repositorio.obtenerTodos(Expediente.class);

        ObservableList<Expediente> opcionesExpedientes = FXCollections.observableArrayList(expedientes);

        // Asignar la lista observable al ComboBoxIniciantes
        cmbExpedientes.setItems(opcionesExpedientes);
        cmbExpedientes.setConverter(new StringConverter<Expediente>() {
            public String toString(Expediente expediente) {
                if (expediente != null) {
                    return expediente.getTitulo();
                } else {
                    return "";
                }
            }

            public Expediente fromString(String string) {
                // No se usa en este caso
                return null;
            }
        });
    }

    private void cargarComboBoxMiembros() {

        // Crear lista a partir de las personas registradas
        List<Persona> personas = repositorio.obtenerTodos(Persona.class);

        ObservableList<Persona> opcionesPersonas = FXCollections.observableArrayList(personas);

        // Asignar la lista observable al ComboBoxIniciantes
        cmbMiembros.setItems(opcionesPersonas);
        cmbMiembros.setConverter(new StringConverter<Persona>() {
            public String toString(Persona persona) {
                if (persona != null) {
                    return persona.getApellido() + " " + persona.getNombre();
                } else {
                    return "";
                }
            }

            public Persona fromString(String string) {
                // No se usa en este caso
                return null;
            }
        });
    }

    @FXML
    public void agregarMiembro() {
        Persona miembroSeleccionado = (Persona) cmbMiembros.getValue();
        if (miembroSeleccionado != null) {
            if (!listaMiembros.contains(miembroSeleccionado)) {
                listaMiembros.add(miembroSeleccionado);
                lstMiembros.setItems(listaMiembros);

                // Configurar el CellFactory del ListView después de agregar un nuevo involucrado
                lstMiembros.setCellFactory(param -> new TextFieldListCell<>(new StringConverter<Persona>() {
                    @Override
                    public String toString(Persona persona) {
                        if (persona != null) {
                            return persona.getNombre() + " " + persona.getApellido();
                        } else {
                            return "";
                        }
                    }

                    @Override
                    public Persona fromString(String string) {
                        // No se usa en este caso
                        return null;
                    }
                }));
            } else {
                mostrarError("El miembro del consejo ya se encuentra agregado a la reunion.");
            }
        } else {
            mostrarError("Por favor, seleccione el miembro del consejo que desea agregar.");
        }
    }


    @FXML
    public void quitarMiembro() {
        Persona miembroSeleccionado = lstMiembros.getSelectionModel().getSelectedItem();
        if (miembroSeleccionado != null) {
            listaMiembros.remove(miembroSeleccionado);
            lstMiembros.setItems(listaMiembros);
        } else {
            mostrarError("Debe seleccionar un miembro para quitar.");
        }
    }

    @FXML
    public void agregarExpediente() {
        Expediente expedienteSeleccionado = (Expediente) cmbExpedientes.getValue();
        if (expedienteSeleccionado != null) {
            if (!listaExpedientes.contains(expedienteSeleccionado)) {
                listaExpedientes.add(expedienteSeleccionado);
                lstExpedientes.setItems(listaExpedientes);

                // Configurar el CellFactory del ListView después de agregar un nuevo involucrado
                lstExpedientes.setCellFactory(param -> new TextFieldListCell<>(new StringConverter<Expediente>() {
                    @Override
                    public String toString(Expediente expediente) {
                        if (expediente != null) {
                            return expediente.getTitulo();
                        } else {
                            return "";
                        }
                    }

                    @Override
                    public Expediente fromString(String string) {
                        // No se usa en este caso
                        return null;
                    }
                }));
            } else {
                mostrarError("El expediente ya se encuentra agregado a la reunion.");
            }
        } else {
            mostrarError("Por favor, seleccione el expedienteque desea agregar.");
        }
    }

    @FXML
    public void quitarExpediente() {
        Expediente expedienteSeleccionado = lstExpedientes.getSelectionModel().getSelectedItem();
        if (expedienteSeleccionado != null) {
            listaExpedientes.remove(expedienteSeleccionado);
            lstExpedientes.setItems(listaExpedientes);
        } else {
            mostrarError("Debe seleccionar un expediente para quitar.");
        }
    }


    @FXML
    public void menuExpediente(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/expediente-view.fxml");
    }

    //Ubicar en ventana "Miembro"
    @FXML
    public void menuMiembro(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/miembros-view.fxml");
    }

    //Ubicar en ventana "Reunion"
    @FXML
    public void menuReunion(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/reunion-view.fxml");
    }

    //Ubicar en ventana "Asistencia"
    @FXML
    public void menuAsistencia(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/asistencia-view.fxml");
    }

    //Ubicar en ventana "Minuta"
    @FXML
    public void menuMinuta(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/minuta-view.fxml");
    }

    @FXML
    public void menuPersona(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/personas-view.fxml");
    }


    //Acciones del formulario
    //cargar (o modificar) un registro
    //verifica el id, si ya existe debe modificarlo, de lo contrario crea uno nuevo
    @FXML
    public void cargarRegistro(ActionEvent event) {

    }

    //limpiar campos
    @FXML
    public void limpiarCampos(ActionEvent event) {
        // Limpiar campos de los ComboBox
        cmbHoraInicio.getSelectionModel().clearSelection();
        cmbHoraFin.getSelectionModel().clearSelection();

        // Verificar si la lista de miembros tiene elementos antes de limpiarla
        if (!lstMiembros.getItems().isEmpty()) {
            lstMiembros.getItems().clear();
        }
    }

    @FXML
    public void modificar(ActionEvent event) {

    }

    //eliminar registro
    //toma el registro seleccionado en la tabla y pide confirmacion para eliminarlo
    @FXML
    public void eliminar(ActionEvent event) {

    }

    private void mostrarError(String s) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText((String) null);
        alertError.setContentText(s);
        alertError.showAndWait();
    }

}
