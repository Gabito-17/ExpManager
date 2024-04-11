package edu.unam.pooproject.controller;

import edu.unam.pooproject.Services.*;
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

import java.time.LocalDate;
import java.util.List;

public class ReunionController {

    private Repositorio repositorio;
    private PersonaServicio personaServicio;
    private ExpedienteServicio expedienteServicio;
    private ReunionServicio reunionServicio;
    private VentanaEmergente ventana = new VentanaEmergente();
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
    @FXML
    private TableView<Reunion> tvDetallesReuniones;
    @FXML
    private TableColumn<Reunion, Integer> colDetalleNro;
    @FXML
    private TableColumn<Reunion, String> colDetalleFecha;
    @FXML
    private TableColumn<Reunion, String> colDetalleInicio;
    @FXML
    private TableColumn<Reunion, String> colDetalleFin;
    @FXML
    private TableColumn<Reunion, Boolean> colDetalleEstado;
    @FXML
    private TableColumn<Reunion, String> colDetalleLugar;
    @FXML
    private TableView<Reunion> tvReunionAsistencia;
    @FXML
    private TableColumn<Reunion, Integer> colNroAsistencia;
    @FXML
    private TableColumn<Reunion, String> colFechaAsistencia;
    @FXML
    private TableColumn<Reunion, String> colHoraInicioAsistencia;
    @FXML
    private TableColumn<Reunion, String> colHoraFinAsistencia;
    @FXML
    private TableColumn<Reunion, String> colDetalleAsistencia;
    @FXML
    private ListView<Persona> lstMiembrosAsistencia;
    @FXML
    private Label lblFechaAsistencia;
    @FXML
    private Label lblHoraInicioAsistencia;
    @FXML
    private Label lblHoraFinAsistencia;
    @FXML
    private Label lblEstadoAsistencia;
    @FXML
    private TextArea taDetallesDetalle;
    @FXML
    private Label lblLugar;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblHoraInicio;
    @FXML
    private Label lblHoraFin;
    @FXML
    private Label lblEstado;
    @FXML
    private Label lblLugarAsistencia;
    @FXML
    private ListView<Persona> lstDetalleMiembros;
    @FXML
    private TableView<Expediente> tvOrden;

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
        colNroAsistencia.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colFechaAsistencia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        colHoraFinAsistencia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraFin().toString()));
        colHoraInicioAsistencia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraInicio().toString()));
        colDetalleAsistencia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDetalles().toString()));
        colDetalleNro.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colDetalleFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        colDetalleFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraFin().toString()));
        colDetalleInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraInicio().toString()));
        colDetalleLugar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDetalles().toString()));
        rellenarTabla();

        //CargarComboBoxes
        rellenarComboBoxHorarios();
        rellenarComboBoxMiembros();
        rellenarComboBoxExpedientes();
    }

    private void rellenarTabla() {
        // Obtener todas las personas de la base de datos a través del servicio
        List<Reunion> reuniones = reunionServicio.obtenerTodos();

        // Convertir la lista de personas en una ObservableList
        ObservableList<Reunion> listaReuniones = FXCollections.observableArrayList(reuniones);

        // Asignar la lista de personas al TableView
        tvReunion.setItems(listaReuniones);
        tvDetallesReuniones.setItems(listaReuniones);
        tvReunionAsistencia.setItems(listaReuniones);
    }

    private void rellenarComboBoxHorarios() {
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
    public boolean verificarHoraFin() {
        if (cmbHoraInicio.getSelectionModel().isEmpty()) {
            return false;
        } else if (!cmbHoraFin.getSelectionModel().isEmpty()) {
            if (cmbHoraFin.getSelectionModel().getSelectedIndex() <= cmbHoraInicio.getSelectionModel().getSelectedIndex()) {
                ventana.mostrarError("La hora de inicio de la reunion debe ser anterior a la hora de finalizacion");
                cmbHoraFin.getSelectionModel().clearSelection();
                rellenarComboBoxHorarios();
            } else return true;
        }
        return false;
    }

    @FXML
    public void verificarHoraInicio() {
        if (cmbHoraFin.getSelectionModel().isEmpty()) {
            return;
        } else if (!cmbHoraFin.getSelectionModel().isEmpty()) {
            if (cmbHoraFin.getSelectionModel().getSelectedIndex() <= cmbHoraInicio.getSelectionModel().getSelectedIndex()) {
                ventana.mostrarError("La hora de inicio de la reunion debe ser anterior a la hora de finalizacion");
                cmbHoraInicio.getSelectionModel().clearSelection();
                rellenarComboBoxHorarios();
            }
        }
    }

    private void rellenarComboBoxExpedientes() {

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

    private void rellenarComboBoxMiembros() {

        // Crear lista a partir de las personas registradas
        List<Persona> miembros = personaServicio.obtenerMiembros();

        ObservableList<Persona> opcionesMiembros = FXCollections.observableArrayList(miembros);

        // Asignar la lista observable al ComboBoxIniciantes
        cmbMiembros.setItems(opcionesMiembros);
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
                ventana.mostrarError("El miembro del consejo ya se encuentra agregado a la reunion.");
            }
        } else {
            ventana.mostrarError("Por favor, seleccione el miembro del consejo que desea agregar.");
        }
    }

    @FXML
    public void verDetalles() {
        if (tvDetallesReuniones.getSelectionModel().getSelectedItem() != null) {
            lblFecha.setText(tvDetallesReuniones.getSelectionModel().getSelectedItem().getFecha().toString());
            lblHoraFin.setText(tvDetallesReuniones.getSelectionModel().getSelectedItem().getHoraFin());
            lblHoraInicio.setText(tvDetallesReuniones.getSelectionModel().getSelectedItem().getHoraInicio());
            taDetallesDetalle.setText(tvDetallesReuniones.getSelectionModel().getSelectedItem().getDetalles());
            lblLugar.setText(tvDetallesReuniones.getSelectionModel().getSelectedItem().getLugar());
            lblEstado.setText(tvDetallesReuniones.getSelectionModel().getSelectedItem().getEstado());

            // Obtener todas las personas de la base de datos a través del servicio
            List<Persona> miembros = personaServicio.obtenerMiembros();

            // Convertir la lista de personas en una ObservableList
            ObservableList<Persona> listaMiembros = FXCollections.observableArrayList(miembros);

            // Asignar la lista de personas al TableView
            lstDetalleMiembros.setItems(listaMiembros);
            lstDetalleMiembros.setCellFactory(param -> new TextFieldListCell<>(new StringConverter<Persona>() {
                @Override
                public String toString(Persona persona) {
                    if (persona != null) {
                        return persona.getApellido() + " " + persona.getNombre();
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
            ventana.mostrarError("Debe seleccionar un miembro para ver los detalles.");
            return;
        }


    }


    @FXML
    public void quitarMiembro() {
        Persona miembroSeleccionado = lstMiembros.getSelectionModel().getSelectedItem();
        if (miembroSeleccionado != null) {
            listaMiembros.remove(miembroSeleccionado);
            lstMiembros.setItems(listaMiembros);
        } else {
            ventana.mostrarError("Debe seleccionar un miembro para quitar.");
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
                ventana.mostrarError("El expediente ya se encuentra agregado a la reunion.");
            }
        } else {
            ventana.mostrarError("Por favor, seleccione el expedienteque desea agregar.");
        }
    }

    @FXML
    public void quitarExpediente() {
        Expediente expedienteSeleccionado = lstExpedientes.getSelectionModel().getSelectedItem();
        if (expedienteSeleccionado != null) {
            listaExpedientes.remove(expedienteSeleccionado);
            lstExpedientes.setItems(listaExpedientes);
        } else {
            ventana.mostrarError("Debe seleccionar un expediente para quitar.");
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

    @FXML
    public void cargarReunion(ActionEvent event) {
        //Instanciar una reunion
        Reunion reunion = new Reunion();
        //Establecer el estado de la reunion en "Abierto"
        reunion.setEstado(true);
        //verificar que el campo Fecha sea valido
        if (dpFecha.getValue().isBefore(LocalDate.now())) {
            ventana.mostrarError("Error al cargar la reunion, la fecha ingresada debe ser posterior o igual a la de hoy.");
            return;
        }
        //Establecer la fecha
        reunion.setFecha(dpFecha.getValue());
        //Verificar la Hora de inicio y final
        if (!verificarHoraFin()) {
            ventana.mostrarError("Error al cargar la reunion, la hora de inicio debe ser anterior a la hora de final.");
            return;
        }
        //Establecer la hora de inicio
        reunion.setHoraInicio(cmbHoraInicio.getValue().toString());
        //Establecer la hora de finalizacion
        reunion.setHoraFin(cmbHoraFin.getValue().toString());
        // Verificar longitud del lugar
        if (txtLugar.getText().length() < 3) {
            ventana.mostrarError("Error al cargar la reunion, el título debe tener al menos 3 caracteres.");
            return;
        }

        // Verificar caracteres del título
        if (txtLugar.getText().matches("[a-zA-Z\\s]+")) {
            ventana.mostrarError("Error al cargar la reunion, el título no debe contener caracteres especiales ni números.");
            return;
        }

        // Verificar longitud de la nota
        if (taDetalles.getText().length() < 10) {
            ventana.mostrarError("Error al cargar reunion, la nota debe tener al menos 10 caracteres.");
            return;
        }

        //Establecer el titulo
        reunion.setLugar(txtLugar.getText());
        //Establecer la nota
        reunion.setDetalles(taDetalles.getText());


        if (lstMiembros.getItems() == null) {
            ventana.mostrarError("Error al cargar la reunion, no selecciono ningún miembro del consejo que participe en ella.");
            return;
        }
        reunion.setMiembros(lstMiembros.getItems());
        if (lstExpedientes.getItems() == null) {
            ventana.mostrarError("Error al cargar la reunion, no selecciono ningún expediente del consejo que se discuta en ella.");
            return;
        }
        reunion.setExpedientes(lstExpedientes.getItems());
        reunionServicio.agregarReunion(reunion);
        ventana.mostrarExito("La Reunion fue cargada con exito!");
        limpiarCampos();
        rellenarTabla();
    }


    //limpiar campos
    @FXML
    public void limpiarCampos() {
        // Limpiar campos
        cmbHoraInicio.getSelectionModel().clearSelection();
        cmbHoraFin.getSelectionModel().clearSelection();
        cmbMiembros.getSelectionModel().clearSelection();
        lstMiembros.getItems().clear();
        dpFecha.setValue(null);
        txtLugar.clear();
        taDetalles.clear();
        lstExpedientes.getItems().clear();
    }

    @FXML
    public void modificar(ActionEvent event) {

    }

    //eliminar registro
    //toma el registro seleccionado en la tabla y pide confirmacion para eliminarlo
    @FXML
    public void eliminar(ActionEvent event) {

    }

    @FXML
    public void cargarAsistencia(ActionEvent event) {
        if (tvReunionAsistencia.getSelectionModel().getSelectedItem() != null) {
            // Obtener todas las personas de la base de datos a través del servicio
            List<Persona> miembros = tvReunionAsistencia.getSelectionModel().getSelectedItem().getMiembros();

            // Convertir la lista de personas en una ObservableList
            ObservableList<Persona> listaMiembros = FXCollections.observableArrayList(miembros);

            // Asignar la lista de personas al ListView
            lstMiembrosAsistencia.setCellFactory(param -> new TextFieldListCell<>(new StringConverter<Persona>() {
                @Override
                public String toString(Persona persona) {
                    if (persona != null) {
                        return persona.getApellido() + " " + persona.getNombre();
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

            lstMiembrosAsistencia.setItems(listaMiembros);
        } else {
            ventana.mostrarError("Debe seleccionar una reunion para marcar su asistencia.");
            return;
        }
    }

}


