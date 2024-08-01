package edu.unam.pooproject.controller;

import edu.unam.pooproject.Services.ExpedienteServicio;
import edu.unam.pooproject.Services.PersonaServicio;
import edu.unam.pooproject.Services.VentanaEmergente;
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
import java.util.Optional;


public class ExpedienteController extends NavegacionController {
    private VentanaEmergente ventana = new VentanaEmergente();
    private LocalDate fechaHoy;
    private Repositorio repositorio;
    private PersonaServicio personaServicio;
    private ExpedienteServicio expedienteServicio;
    //Informacion del expediente
    @FXML
    private Label lblNroDetalle;
    @FXML
    private Label lblFechaDetalle;
    @FXML
    private Label lblInicianteDetalle;
    @FXML
    private Label lblTituloDetalle;
    @FXML
    private Label lblNotaDetalle;
    @FXML
    private Label lblFechaIngreso;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextArea taNota;
    @FXML
    private ComboBox<Persona> cmbIniciantes;
    @FXML
    private ComboBox<Persona> cmbInvolucrados;
    @FXML
    private ListView<Persona> lstInvolucrados;
    @FXML
    private TableView<Expediente> tvExpedientes;
    @FXML
    private TableColumn<Expediente, Integer> colId;
    @FXML
    private TableColumn<Expediente, String> colIniciante;
    @FXML
    private TableColumn<Expediente, String> colTitulo;
    @FXML
    private TableColumn<Expediente, String> colFechaIngreso;
    @FXML
    private TableColumn<Expediente, String> colEstado;
    @FXML
    private ListView<Persona> lstInvolucradoDetalle;
    @FXML
    private TableView<Expediente> tvExpedienteDetalle;

    @FXML
    private TableColumn<Expediente, Integer> colNroDetalle;
    @FXML
    private TableColumn<Expediente, String> colInicianteDetalle;
    @FXML
    private TableColumn<Expediente, String> colTituloDetalle;
    @FXML
    private TableColumn<Expediente, String> colFechaIngresoDetalle;
    @FXML
    private TableColumn<Expediente, String> colEstadoDetalle;
    private ObservableList<Persona> listaInvolucrados = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.personaServicio = new PersonaServicio(this.repositorio);
        this.expedienteServicio = new ExpedienteServicio(this.repositorio);

        // Configurar las columnas del TableView para que muestren los datos de Expediente
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colFechaIngreso.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaIngreso().toString()));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado().toString()));
        colIniciante.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIniciante().getNombreCompleto()));
        colNroDetalle.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colTituloDetalle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colFechaIngresoDetalle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaIngreso().toString()));
        colEstadoDetalle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado().toString()));
        colInicianteDetalle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIniciante().getNombreCompleto()));

        // Cargar todas los expedientes de la base de datos y mostrarlos en el TableView
        rellenarTabla();

        //guardar fecha actual
        fechaHoy = LocalDate.now();

        // Asignar la fecha al label FechaIngreso
        lblFechaIngreso.setText(fechaHoy.toString());
        cargarComboBox(cmbIniciantes);
        cargarComboBox(cmbInvolucrados);

    }

    private void cargarComboBox(ComboBox cmb) {

        // Crear lista a partir de las personas registradas
        List<Persona> personas = repositorio.obtenerTodos(Persona.class);

        ObservableList<Persona> opcionesPersonas = FXCollections.observableArrayList(personas);

        // Asignar la lista observable al ComboBoxIniciantes
        cmb.setItems(opcionesPersonas);
        cmb.setConverter(new StringConverter<Persona>() {
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
    public void verDetalles(MouseEvent event) {
        if (tvExpedienteDetalle.getSelectionModel().getSelectedItem() != null) {
            lblFechaDetalle.setText(tvExpedienteDetalle.getSelectionModel().getSelectedItem().getFechaIngreso().toString());
            lblNroDetalle.setText(tvExpedienteDetalle.getSelectionModel().getSelectedItem().getId().toString());
            lblInicianteDetalle.setText(tvExpedienteDetalle.getSelectionModel().getSelectedItem().getIniciante().getNombreCompleto());
            lblTituloDetalle.setText(tvExpedienteDetalle.getSelectionModel().getSelectedItem().getTitulo().toString());
            lblNotaDetalle.setText(tvExpedienteDetalle.getSelectionModel().getSelectedItem().getNota());

            // Obtener todas las personas involucradas en el expediente seleccionado
            List<Persona> involucradosExpediente = expedienteServicio.obtenerInvolucrados(tvExpedienteDetalle.getSelectionModel().getSelectedItem());

            if (involucradosExpediente != null && !involucradosExpediente.isEmpty()) {
                // Convertir la lista de personas en una ObservableList
                ObservableList<Persona> listaInvolucrados = FXCollections.observableArrayList(involucradosExpediente);

                // Asignar la lista de personas al TableView
                lstInvolucradoDetalle.setItems(listaInvolucrados);
                // Establecer el formato de las celdas del TableView para que muestren el nombre y apellido de cada persona
                lstInvolucradoDetalle.setCellFactory(param -> new TextFieldListCell<>(new StringConverter<Persona>() {
                    @Override
                    public String toString(Persona persona) {
                        return persona != null ? persona.getApellido() + " " + persona.getNombre() : "";
                    }

                    @Override
                    public Persona fromString(String string) {
                        return null;
                    }
                }));
            } else {
                // Mostrar un mensaje de error si no se selecciona un expediente o si no hay personas involucradas
                ventana.mostrarError("Debe seleccionar un expediente con personas involucradas para ver los detalles.");
            }
        } else {
            // Mostrar un mensaje de error si no se selecciona un expediente
            ventana.mostrarError("Debe seleccionar un expediente para ver los detalles.");
        }
    }


    private void rellenarTabla() {
        // Obtener todos los expedientes de la base de datos a través del servicio
        List<Expediente> expedientes = expedienteServicio.obtenerTodos();

        // Convertir la lista de expedientes en una ObservableList
        ObservableList<Expediente> listaExpedientes = FXCollections.observableArrayList(expedientes);

        // Asignar la lista de expediente al TableView
        tvExpedientes.setItems(listaExpedientes);
        tvExpedienteDetalle.setItems(listaExpedientes);
    }

    @FXML
    public void eliminarExpediente(ActionEvent event) {
        // Obtener el expediente seleccionado en la tabla
        Expediente expedienteSeleccionado = tvExpedienteDetalle.getSelectionModel().getSelectedItem();

        // Verificar si hay algun expediente seleccionado
        if (expedienteSeleccionado == null) {
            ventana.mostrarError("Debe seleccionar un expediente de la tabla para eliminarlo");
            return;
        }

        // Mostrar un cuadro de diálogo de confirmación
        Alert alertConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmacion.setTitle("Confirmar");
        alertConfirmacion.setHeaderText("¿Estás seguro de eliminar este expediente?");
        alertConfirmacion.setContentText("Se eliminará permanentemente al expediente seleccionado.");

        Optional<ButtonType> resultado = alertConfirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            try {
                // Eliminar el expediente seleccionado
                expedienteServicio = new ExpedienteServicio(repositorio);

                // Eliminar el expediente
                expedienteServicio.eliminarExpediente(expedienteSeleccionado);

                // Actualizar la tabla
                rellenarTabla();
            } catch (Exception e) {
                ventana.mostrarError("Error al eliminar el expediente: tiene Reuniones pendientes");

            }
        }
        initialize();
    }

    @FXML
    public void marcarCerrado(ActionEvent event) throws Exception {
        // Obtener expediente seleccionado en el TableView
        Expediente expedienteSeleccionado = tvExpedienteDetalle.getSelectionModel().getSelectedItem();

        // Verificar si hay algun expediente seleccionado
        if (expedienteSeleccionado == null) {
            ventana.mostrarError("Debe seleccionar un expediente para marcarlo como cerrado.");
            return;
        }
        if (expedienteSeleccionado.getEstado() == true) {

            // Mostrar un Alert de confirmación para verificar si se desea marcar el expediente como cerrado
            Optional<ButtonType> resultado = ventana.mostrarConfirmacion("¿Deseas marcar este expediente como cerrado?", "Esto es reversible");
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                // Cambiar el estado del expediente a cerrado
                expedienteSeleccionado.setEstado(false);
                expedienteServicio.editarExpediente(expedienteSeleccionado);
                initialize();

            }
        } else {
            ventana.mostrarError("El expediente ya se encuentra cerrado");
        }
    }

    @FXML
    public void verAcciones(ActionEvent event) {
        Expediente expedienteSeleccionado = tvExpedienteDetalle.getSelectionModel().getSelectedItem();
        if (expedienteSeleccionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/accionXexpediente-view.fxml"));
                Parent root = loader.load();
                AccionController accionController = loader.getController();
                accionController.setExpediente(expedienteSeleccionado); // Pasar la reunión
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                // Cerrar la ventana actual
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                ventana.mostrarError("Error al cargar la ventana de acciones.");
                e.printStackTrace();
            }
        } else {
            ventana.mostrarError("Debe seleccionar una reunión para ver las acciones.");
        }
    }

    //Limpiar todos los inputs
    @FXML
    public void limpiarCampos() {
        // Limpiar los campos de texto y áreas de texto
        txtTitulo.clear();
        taNota.clear();

        // Limpiar la selección de ComboBox y ListView
        cmbIniciantes.getSelectionModel().clearSelection();
        cmbInvolucrados.getSelectionModel().clearSelection();
        lstInvolucrados.getItems().clear();

        // Limpiar la fecha de ingreso
        fechaHoy = LocalDate.now();
        lblFechaIngreso.setText(fechaHoy.toString());
    }

    @FXML
    public void agregarInvolucrado() {
        Persona involucradoSeleccionado = cmbInvolucrados.getValue();
        if (involucradoSeleccionado != null) {
            if (!listaInvolucrados.contains(involucradoSeleccionado)) {
                listaInvolucrados.add(involucradoSeleccionado);
                lstInvolucrados.setItems(listaInvolucrados);

                // Configurar el CellFactory del ListView después de agregar un nuevo involucrado
                lstInvolucrados.setCellFactory(param -> new TextFieldListCell<>(new StringConverter<Persona>() {
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
                ventana.mostrarError("El involucrado ya se encuentra agregado.");
            }
        } else {
            ventana.mostrarError("Debe seleccionar un involucrado.");
        }
    }

    @FXML
    public void quitarInvolucrado() {
        Persona involucradoSeleccionado = lstInvolucrados.getSelectionModel().getSelectedItem();
        if (involucradoSeleccionado != null) {
            listaInvolucrados.remove(involucradoSeleccionado);
            lstInvolucrados.setItems(listaInvolucrados);
        } else {
            ventana.mostrarError("Debe seleccionar un involucrado para quitar.");
        }
    }

    @FXML
    public void cargarExpediente(ActionEvent event) throws Exception {

        //Instancias un expediente
        Expediente expediente = new Expediente();
        //Establece el estado del expediente en "Abierto"
        expediente.setEstado(true);
        //verificar que los campos nota y titulo tengan contenido
        if (!taNota.getText().isEmpty() && !txtTitulo.getText().isEmpty()) {
            // Verificar longitud del título
            if (txtTitulo.getText().length() < 3) {
                ventana.mostrarError("Error al cargar expediente, el título debe tener al menos 3 caracteres.");
                return;
            }

            // Verificar caracteres del título
            if (!txtTitulo.getText().matches("[a-zA-Z0-9\\s]+")) {
                ventana.mostrarError("Error al cargar expediente, el título no debe contener caracteres especiales ni números.");
                return;
            }

            // Verificar longitud de la nota
            if (taNota.getText().length() < 10) {
                ventana.mostrarError("Error al cargar expediente, la nota debe tener al menos 10 caracteres.");
                return;
            }

            //Establecer el titulo
            expediente.setTitulo(txtTitulo.getText());
            //Establecer la nota
            expediente.setNota(taNota.getText());
        } else {
            ventana.mostrarError("Error al cargar expediente, los campos 'título' o 'nota' están vacíos.");
            return;
        }
        if (cmbIniciantes.getSelectionModel().getSelectedItem() != null) {
            expediente.setIniciante(cmbIniciantes.getSelectionModel().getSelectedItem());

        } else {
            ventana.mostrarError("Error al cargar expediente, no selecciono un iniciante.");
            return;
        }
        if (fechaHoy.isAfter(LocalDate.now()) || fechaHoy.isBefore(LocalDate.now())) {
            ventana.mostrarError("Error al cargar expediente, la fecha ingresada no es válida.");
            return;
        } else {
            expediente.setFechaIngreso(fechaHoy);
        }
        if (listaInvolucrados.isEmpty()) {
            ventana.mostrarError("Error al cargar expediente, no selecciono ningún involucrado.");
            return;
        } else {
            expediente.setInvolucrados(listaInvolucrados);
        }

        if (expedienteExiste(expediente.getId())) {
            if (!expediente.getEstado()) {
                ventana.mostrarError("Error al cargar expediente, el expediente ya se encuentra cerrado.");
                return;
            }
            this.expedienteServicio.editarExpediente(expediente);
        } else {
            // Guardar el expediente en la base de datos
            this.expedienteServicio.agregarExpediente(expediente);

        }
        limpiarCampos();
        ventana.mostrarExito("El expediente fue cargado con existo!");
        initialize();
    }


    public boolean expedienteExiste(Integer id) {
        // Llama al método del servicio de expediente  para buscar el expediente por su Id
        Expediente expedienteExistente = expedienteServicio.buscarPorId(id);

        // Si el expediente existe (es diferente de null), devuelve true; de lo contrario, devuelve false
        return expedienteExistente != null;
    }
}

