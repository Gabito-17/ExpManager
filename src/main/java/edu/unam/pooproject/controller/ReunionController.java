package edu.unam.pooproject.controller;

import edu.unam.pooproject.Services.*;
import edu.unam.pooproject.db.Conexion;
import edu.unam.pooproject.modelo.*;
import edu.unam.pooproject.repositorio.Repositorio;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ReunionController extends NavegacionController {

    private Repositorio repositorio;
    private PersonaServicio personaServicio;

    private MinutaServicio minutaServicio;
    private ExpedienteServicio expedienteServicio;
    private ReunionServicio reunionServicio;
    private AsistenciaServicio asistenciaServicio;
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
    private TableView<Reunion> tvDetallesReunion;
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
    private TableView<Asistencia> tvMiembroAsistencia;
    @FXML
    private TableColumn<Asistencia, String> colMiembroAsistencia;
    @FXML
    private TableColumn<Asistencia, String> colReunionAsistencia;
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
    private Label lblDetallesDetalle;
    @FXML
    private RadioButton rbAusente;
    @FXML
    private RadioButton rbPresente;
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
        this.minutaServicio = new MinutaServicio(this.repositorio);
        this.expedienteServicio = new ExpedienteServicio(this.repositorio);
        this.reunionServicio = new ReunionServicio(this.repositorio);
        this.asistenciaServicio = new AsistenciaServicio(this.repositorio);
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
        rellenarTablas();

        //CargarComboBoxes
        rellenarComboBoxHorarios();
        rellenarComboBoxMiembros();
        rellenarComboBoxExpedientes();
    }

    private void rellenarTablas() {
        // Obtener todas las personas de la base de datos a través del servicio
        List<Reunion> reuniones = reunionServicio.obtenerTodos();

        // Convertir la lista de personas en una ObservableList
        ObservableList<Reunion> listaReuniones = FXCollections.observableArrayList(reuniones);

        // Asignar la lista de personas al TableView
        tvReunion.setItems(listaReuniones);
        tvDetallesReunion.setItems(listaReuniones);
        tvReunionAsistencia.setItems(listaReuniones);
    }

    private void rellenarComboBoxHorarios() {
        // Crear una lista de horarios
        ObservableList<String> horarios = FXCollections.observableArrayList(
                "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
                "16:00", "17:00", "18:00", "19:00", "20:00"
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

        // Crear lista a partir de los expedientes registradss
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
    public void verDetalles(MouseEvent event) {
        if (tvDetallesReunion.getSelectionModel().getSelectedItem() != null) {
            Reunion reunionSeleccionada = tvDetallesReunion.getSelectionModel().getSelectedItem();

            lblFecha.setText(reunionSeleccionada.getFecha().toString());
            lblHoraFin.setText(reunionSeleccionada.getHoraFin());
            lblHoraInicio.setText(reunionSeleccionada.getHoraInicio());
            lblDetallesDetalle.setText(reunionSeleccionada.getDetalles());
            lblLugar.setText(reunionSeleccionada.getLugar());
            lblEstado.setText(reunionSeleccionada.getEstado());

            // Obtener el orden de los expedientes de la reunión
            List<Expediente> ordenExpedientes = reunionSeleccionada.getOrden();

            // Convertir la lista de expedientes en una ObservableList
            ObservableList<Expediente> listaOrden = FXCollections.observableArrayList(ordenExpedientes);

            // Asignar la lista de expedientes al TableView
            tvOrden.setItems(listaOrden);

            // Configurar las propiedades de las columnas de la tabla de expedientes si aún no están configuradas
            tvOrden.getColumns().clear();
            TableColumn<Expediente, Integer> colOrdenId = new TableColumn<>("ID");
            colOrdenId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            TableColumn<Expediente, String> colOrdenTitulo = new TableColumn<>("Título");
            colOrdenTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));

            tvOrden.getColumns().addAll(colOrdenId, colOrdenTitulo);

            // Obtener los miembros de la reunión seleccionada
            List<Persona> miembrosReunion = reunionSeleccionada.getMiembros();

            // Convertir la lista de personas en una ObservableList
            ObservableList<Persona> listaMiembros = FXCollections.observableArrayList(miembrosReunion);

            // Asignar la lista de personas al ListView
            lstDetalleMiembros.setItems(listaMiembros);
            // Configurar el CellFactory del ListView
            lstDetalleMiembros.setCellFactory(param -> new TextFieldListCell<>(new StringConverter<Persona>() {
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
            ventana.mostrarError("Debe seleccionar una reunión para ver los detalles.");
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
    public void cargarReunion(ActionEvent event) {
        //Instanciar una reunion
        Reunion reunion = new Reunion();
        //Establecer el estado de la reunion en "Abierto"
        reunion.setEstado(true);

        //verificar lista expedientes
        if (listaExpedientes.size() < 1) {
            ventana.mostrarError("Error al cargar la reunion, debe añadir al menos 1 expediente.");
            return;
        }
        //verificar lista miembros
        if (listaMiembros.size() < 2) {
            ventana.mostrarError("Error al cargar la reunion, debe añadir al menos 2 miembros.");
            return;
        }
        //verificar que el campo Fecha sea valido
        if (dpFecha.getValue() == null) {
            ventana.mostrarError("Error al cargar la reunion, debe seleccionar una fecha.");
            return;
        } else if (dpFecha.getValue().isBefore(LocalDate.now())) {
            ventana.mostrarError("Error al cargar la reunion, la fecha ingresada debe ser posterior o igual a la de hoy.");
            return;
        }
        //Verificar la Hora de inicio y final
        if (!verificarHoraFin()) {
            ventana.mostrarError("Error al cargar la reunion, la hora de inicio debe ser anterior a la hora de finalizacion.");
            return;
        }

        // Verificar longitud del lugar
        if (txtLugar.getText().length() < 3) {
            ventana.mostrarError("Error al cargar la reunion, el 'Lugar' debe tener al menos 3 caracteres.");
            return;
        }

        // Verificar caracteres del título
        if (!txtLugar.getText().matches("[a-zA-Z0-9\\s,]+")) {
            ventana.mostrarError("Error al cargar la reunión, el 'Lugar' no debe contener caracteres especiales.");
            return;
        }


        // Verificar longitud de la nota
        if (taDetalles.getText().length() < 10) {
            ventana.mostrarError("Error al cargar reunion, 'Detalles' debe tener al menos 10 caracteres.");
            return;
        }

        //Establecer la fecha de la reunion
        reunion.setFecha(dpFecha.getValue());
        //Establecer la hora de inicio
        reunion.setHoraInicio(cmbHoraInicio.getValue().toString());
        //Establecer la hora de finalizacion
        reunion.setHoraFin(cmbHoraFin.getValue().toString());
        //Establecer el lugar de la reunion
        reunion.setLugar(txtLugar.getText().trim().replaceAll("\\s+", " "));
        //Establecer los detalles de la reunion
        reunion.setDetalles(taDetalles.getText().trim().replaceAll("\\s+", " "));
        //Establecer miembros
        reunion.setMiembros(lstMiembros.getItems());
        //Establecer la lista de expedientes
        reunion.setOrden(lstExpedientes.getItems());
        reunionServicio.agregarReunion(reunion);
        ObservableList<Minuta> listaMinutas = FXCollections.observableArrayList();

        for (Expediente expediente : reunion.getOrden()) {
            Minuta minuta = new Minuta();
            minuta.setExpediente(expediente);
            minuta.setReunion(reunion);
            minutaServicio.cargarMinuta(minuta);
            listaMinutas.add(minuta);
        }
        reunion.setMinutas(listaMinutas);
        reunionServicio.editarReunion(reunion);

        asistenciaServicio.crearAsistencia(reunion, lstMiembros.getItems());
        ventana.mostrarExito("La Reunion fue cargada con exito!");
        limpiarCampos();
        rellenarTablas();
    }

    @FXML
    public void verMinutas(ActionEvent event) {
        Reunion reunionSeleccionada = tvDetallesReunion.getSelectionModel().getSelectedItem();
        if (reunionSeleccionada != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/minuta-view.fxml"));
                Parent root = loader.load();
                MinutaController minutaController = loader.getController();
                minutaController.setReunionId(reunionSeleccionada.getId());

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                // Cerrar la ventana actual
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                ventana.mostrarError("Error al cargar la ventana de minutas.");
                e.printStackTrace();
            }
        } else {
            ventana.mostrarError("Debe seleccionar una reunión para ver las minutas.");
        }
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
    public void cargarDatosAsistencia(MouseEvent event) {
        //Obtener la reunion seleccionada
        Reunion reunionSeleccionada = tvReunionAsistencia.getSelectionModel().getSelectedItem();
        if (reunionSeleccionada != null) {
            lblFechaAsistencia.setText(tvReunionAsistencia.getSelectionModel().getSelectedItem().getFecha().toString());
            lblHoraFinAsistencia.setText(tvReunionAsistencia.getSelectionModel().getSelectedItem().getHoraFin());
            lblHoraInicioAsistencia.setText(tvReunionAsistencia.getSelectionModel().getSelectedItem().getHoraInicio());
            lblLugarAsistencia.setText(tvReunionAsistencia.getSelectionModel().getSelectedItem().getLugar());
            lblEstadoAsistencia.setText(tvReunionAsistencia.getSelectionModel().getSelectedItem().getEstado());

            //Obtener Asistencias
            List<Asistencia> asistencia = reunionSeleccionada.getAsistencia();
            //Si la Reunion no tiene asistencia cargada, habilitar el RadioButton de Asistencia
            if (asistencia == null) {

                rbPresente.setDisable(false);
                rbAusente.setDisable(false);

            } else {
                rbPresente.setDisable(true);
                rbAusente.setDisable(true);
                // Convertir la lista de Asistencias a ObservableList
                ObservableList<Asistencia> listaAsistencias = FXCollections.observableArrayList(asistencia);
                tvMiembroAsistencia.setItems(listaAsistencias);
            }


        } else {
            ventana.mostrarError("Debe seleccionar un miembro para ver los detalles.");
            return;
        }
    }

    @FXML
    public void seleccionarAsistencia() {

        if (rbPresente.isSelected()) {

        }
    }

    @FXML
    public void cargarAsistencia(MouseEvent event) {
        if (lstMiembrosAsistencia.getSelectionModel().getSelectedItem() != null) {


        } else {
            ventana.mostrarError("Debe seleccionar un miembro para ver los detalles.");
            return;
        }


    }
}


