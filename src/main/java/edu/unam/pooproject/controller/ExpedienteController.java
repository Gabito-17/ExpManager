package edu.unam.pooproject.controller;

import edu.unam.pooproject.Services.Enrutador;
import edu.unam.pooproject.Services.ExpedienteServicio;
import edu.unam.pooproject.Services.PersonaServicio;
import edu.unam.pooproject.db.Conexion;
import edu.unam.pooproject.modelo.Expediente;
import edu.unam.pooproject.modelo.Persona;
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
import java.util.Optional;


public class ExpedienteController {
    private LocalDate fechaHoy;
    private Repositorio repositorio;
    private PersonaServicio personaServicio;
    private ExpedienteServicio expedienteServicio;
    //Informacion del expediente
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

    private void rellenarTabla() {
        // Obtener todas las personas de la base de datos a través del servicio
        List<Expediente> expedientes = expedienteServicio.obtenerTodos();

        // Convertir la lista de personas en una ObservableList
        ObservableList<Expediente> listaExpedientes = FXCollections.observableArrayList(expedientes);

        // Asignar la lista de personas al TableView
        tvExpedientes.setItems(listaExpedientes);
    }

    @FXML
    public void eliminarExpediente(ActionEvent event) {
        // Obtener el expediente seleccionado en la tabla
        Expediente expedienteSeleccionado = tvExpedientes.getSelectionModel().getSelectedItem();

        // Verificar si hay algun expediente seleccionado
        if (expedienteSeleccionado == null) {
            mostrarError("Selecciona un expediente para eliminar.");
            return;
        }

        // Mostrar un cuadro de diálogo de confirmación
        Alert alertConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmacion.setTitle("Confirmar");
        alertConfirmacion.setHeaderText("¿Estás seguro de eliminar este expediente?");
        alertConfirmacion.setContentText("Se eliminará permanentemente al expediente seleccionado.");

        Optional<ButtonType> resultado = alertConfirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Eliminar el expediente seleccionado
            ExpedienteServicio servicio = new ExpedienteServicio(repositorio);
            servicio.eliminarExpediente(expedienteSeleccionado);

            // Actualizar la tabla
            rellenarTabla();
        }
    }

    @FXML
    public void editarExpediente(ActionEvent event) throws Exception {
        // Obtener expediente seleccionado en el TableView
        Expediente expedienteSeleccionado = tvExpedientes.getSelectionModel().getSelectedItem();

        // Verificar si hay algun expediente seleccionado
        if (expedienteSeleccionado == null) {
            mostrarError("Selecciona un expediente para editar.");
            return;
        }

        // Mostrar un Alert de confirmación para verificar si se desea editar el expediente
        Alert alertConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmacion.setTitle("Confirmar");
        alertConfirmacion.setHeaderText("¿Deseas editar este expediente?");
        alertConfirmacion.setContentText("Se editarán los datos del expediente seleccionado.\n Al finalizar la edicion presiones 'Cargar' ");

        Optional<ButtonType> resultado = alertConfirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Cargar los datos del expediente seleccionado en los campos correspondientes
            txtTitulo.setText(expedienteSeleccionado.getTitulo());
            lblFechaIngreso.setText(expedienteSeleccionado.getFechaIngreso().toString());
            taNota.setText(expedienteSeleccionado.getNota());
            cmbIniciantes.getSelectionModel().select(expedienteSeleccionado.getIniciante());
            listaInvolucrados.clear();
            lstInvolucrados.setItems(listaInvolucrados);
            lblFechaIngreso.setText(expedienteSeleccionado.getFechaIngreso().toString());
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
                mostrarError("El involucrado ya se encuentra agregado.");
            }
        } else {
            mostrarError("Debe seleccionar un involucrado.");
        }
    }

    @FXML
    public void quitarInvolucrado() {
        Persona involucradoSeleccionado = lstInvolucrados.getSelectionModel().getSelectedItem();
        if (involucradoSeleccionado != null) {
            listaInvolucrados.remove(involucradoSeleccionado);
            lstInvolucrados.setItems(listaInvolucrados);
        } else {
            mostrarError("Debe seleccionar un involucrado para quitar.");
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
                mostrarError("Error al cargar expediente, el título debe tener al menos 3 caracteres.");
                return;
            }

            // Verificar caracteres del título
            if (!txtTitulo.getText().matches("[a-zA-Z\\s]+")) {
                mostrarError("Error al cargar expediente, el título no debe contener caracteres especiales ni números.");
                return;
            }

            // Verificar longitud de la nota
            if (taNota.getText().length() < 10) {
                mostrarError("Error al cargar expediente, la nota debe tener al menos 10 caracteres.");
                return;
            }

            //Establecer el titulo
            expediente.setTitulo(txtTitulo.getText());
            //Establecer la nota
            expediente.setNota(taNota.getText());
        } else {
            mostrarError("Error al cargar expediente, los campos 'título' o 'nota' están vacíos.");
            return;
        }
        if (cmbIniciantes.getSelectionModel().getSelectedItem() != null) {
            expediente.setIniciante(cmbIniciantes.getSelectionModel().getSelectedItem());

        } else {
            mostrarError("Error al cargar expediente, no selecciono un iniciante.");
            return;
        }
        if (fechaHoy.isAfter(LocalDate.now()) || fechaHoy.isBefore(LocalDate.now())) {
            mostrarError("Error al cargar expediente, la fecha ingresada no es válida.");
            return;
        } else {
            expediente.setFechaIngreso(fechaHoy);
        }
        if (listaInvolucrados.isEmpty()) {
            mostrarError("Error al cargar expediente, no selecciono ningún involucrado.");
            return;
        } else {
            expediente.setInvolucrados(listaInvolucrados);
        }

        if (expedienteExiste(expediente.getId())) {
            this.expedienteServicio.editarExpediente(expediente);
        } else {
            this.expedienteServicio.agregarExpediente(expediente);
        }
        limpiarCampos();
    }

    public boolean expedienteExiste(Integer id) {
        // Llama al método del servicio de persona para buscar a la persona por su DNI
        Expediente expedienteExistente = expedienteServicio.buscarPorId(id);

        // Si la persona existe (es diferente de null), devuelve true; de lo contrario, devuelve false
        return expedienteExistente != null;
    }

    //Ubicar en ventana "Inicio"
    @FXML
    public void menuMiembros(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/miembros-view.fxml");
    }

    //Ubicar en ventana "Expedientes"
    @FXML
    public void menuExpedientes(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/expediente-view.fxml");
    }

    //Ubicar en ventana "Miembros"
    @FXML
    public void menuPersona(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/personas-view.fxml");
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

    private void mostrarError(String s) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText((String) null);
        alertError.setContentText(s);
        alertError.showAndWait();
    }
}

