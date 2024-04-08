package edu.unam.pooproject.controller;

import edu.unam.pooproject.Services.Enrutador;
import edu.unam.pooproject.Services.PersonaServicio;
import edu.unam.pooproject.db.Conexion;
import edu.unam.pooproject.modelo.Persona;
import edu.unam.pooproject.repositorio.Repositorio;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PersonaController {
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private LocalDate fechaHoy;
    private Repositorio repositorio;
    private PersonaServicio personaServicio;
    @FXML
    private TextField txtDniPersona;
    @FXML
    private TextField txtNombrePersona;
    @FXML
    private TextField txtApellidoPersona;
    @FXML
    private TextField txtEmailPersona;
    @FXML
    private DatePicker fechaNacimiento;
    @FXML
    private CheckBox esMiembro;

    @FXML
    private TableView<Persona> tvPersonas;

    @FXML
    private TableColumn<Persona, String> colDni;

    @FXML
    private TableColumn<Persona, String> colNombre;

    @FXML
    private TableColumn<Persona, String> colApellido;

    @FXML
    private TableColumn<Persona, String> colEmail;

    @FXML
    private TableColumn<Persona, LocalDate> colFechaNacimiento;

    @FXML
    private TableColumn<Persona, Boolean> colEsMiembro;

    @FXML
    public void initialize() {
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.personaServicio = new PersonaServicio(this.repositorio);

        // Configurar las columnas del TableView para que muestren los datos de Persona
        colDni.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDni()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colEsMiembro.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isEsMiembro()));
        colFechaNacimiento.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaNacimiento()));
        // Cargar todas las personas de la base de datos y mostrarlas en el TableView
        rellenarTabla();
    }

    private void rellenarTabla() {
        // Obtener todas las personas de la base de datos a través del servicio
        List<Persona> personas = personaServicio.obtenerTodos();

        // Convertir la lista de personas en una ObservableList
        ObservableList<Persona> listaPersonas = FXCollections.observableArrayList(personas);

        // Asignar la lista de personas al TableView
        tvPersonas.setItems(listaPersonas);
    }

    @FXML
    public void editarPersona(ActionEvent event) throws Exception {
        // Obtener la persona seleccionada en el TableView
        Persona personaSeleccionada = tvPersonas.getSelectionModel().getSelectedItem();

        // Verificar si hay alguna persona seleccionada
        if (personaSeleccionada == null) {
            mostrarError("Selecciona una persona para editar.");
            return;
        }

        // Mostrar un Alert de confirmación para verificar si se desea editar la persona
        Alert alertConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmacion.setTitle("Confirmar");
        alertConfirmacion.setHeaderText("¿Deseas editar a esta persona?");
        alertConfirmacion.setContentText("Se editarán los datos de la persona seleccionada. \n Al finalizar la edicion presiones 'Cargar' ");

        Optional<ButtonType> resultado = alertConfirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Cargar los datos de la persona seleccionada en los campos correspondientes
            txtDniPersona.setText(personaSeleccionada.getDni());
            txtNombrePersona.setText(personaSeleccionada.getNombre());
            txtApellidoPersona.setText(personaSeleccionada.getApellido());
            txtEmailPersona.setText(personaSeleccionada.getEmail());
            fechaNacimiento.setValue(personaSeleccionada.getFechaNacimiento());
            esMiembro.setSelected(personaSeleccionada.isEsMiembro());
        }
    }


    @FXML
    public void cargarPersona(ActionEvent event) throws Exception {
        //Instancia una persona
        Persona persona = new Persona();
        // Verificar DNI
        if (!verificarDNI()) {
            return; // Detener la carga si el DNI no es válido
        }
        //asignar DNI
        persona.setDni(txtDniPersona.getText().trim());

        // Verificar correo electrónico
        if (!verificarCorreo()) {
            return; // Detener la carga si el correo electrónico no es válido
        }
        // Asignar correo electrónico
        persona.setEmail(txtEmailPersona.getText().trim());

        // Verificar fecha de nacimiento
        if (!verificarFecha()) {
            return; // Detener la carga si la fecha de nacimiento no es válida
        }
        // Asignar fecha de nacimiento
        persona.setFechaNacimiento(fechaNacimiento.getValue());
        if (!verificarNombre()) {
            return; // Detener la carga si el nombre no es valido
        }
        // Asignar nombre
        persona.setNombre(txtNombrePersona.getText().trim());
        if (!verificarApellido()) {
            return; // Detener la carga si el nombre no es valido
        }
        // Asignar apellido
        persona.setApellido(txtApellidoPersona.getText().trim());
        // Asignar apellido
        if (esMiembro.isSelected()) {
            persona.setRol(true);
        } else {
            persona.setRol(false);
        }
        if (personaExiste(txtDniPersona.getText().trim())) {
            this.personaServicio.editarPersona(persona);
        } else {
            this.personaServicio.agregarPersona(persona);
        }
        limpiarCampos();
    }

    @FXML
    public void eliminarPersona(ActionEvent event) {
        // Obtener la persona seleccionada en la tabla
        Persona personaSeleccionada = tvPersonas.getSelectionModel().getSelectedItem();

        // Verificar si hay alguna persona seleccionada
        if (personaSeleccionada == null) {
            mostrarError("Selecciona una persona para eliminar.");
            return;
        }
        if (personaServicio.esIniciante(personaSeleccionada)) {
            mostrarError("La persona está asociada a expedientes y no puede ser eliminada.");
            return;
        }

        // Mostrar un cuadro de diálogo de confirmación
        Alert alertConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmacion.setTitle("Confirmar");
        alertConfirmacion.setHeaderText("¿Estás seguro de eliminar a esta persona?");
        alertConfirmacion.setContentText("Se eliminará permanentemente a la persona seleccionada.");

        Optional<ButtonType> resultado = alertConfirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            try {
                // Eliminar la persona seleccionada
                PersonaServicio servicio = new PersonaServicio(repositorio);
                servicio.eliminarPersona(personaSeleccionada);

                // Actualizar la tabla
                rellenarTabla();
            } catch (javax.persistence.RollbackException e) {
                Throwable causa = e.getCause();
                if (causa instanceof org.hibernate.exception.ConstraintViolationException) {
                    mostrarError("La persona está asociada a expedientes y no puede ser eliminada.");
                } else {
                    mostrarError("Error al eliminar la persona: " + e.getMessage());
                }
            } catch (Exception e) {
                mostrarError("Error al eliminar la persona: " + e.getMessage());
            }
        }
    }

    public boolean personaExiste(String dni) {
        // Llama al método del servicio de persona para buscar a la persona por su DNI
        Persona personaExistente = personaServicio.buscarPorDni(dni);

        // Si la persona existe (es diferente de null), devuelve true; de lo contrario, devuelve false
        return personaExistente != null;
    }

    public void limpiarCampos() {
        txtDniPersona.clear();
        txtNombrePersona.clear();
        txtApellidoPersona.clear();
        txtEmailPersona.clear();
        fechaNacimiento.setValue(null);
        esMiembro.setSelected(false);
    }

    public boolean verificarFecha() {
        LocalDate fechaSeleccionada = fechaNacimiento.getValue();
        if (fechaSeleccionada != null) {
            if (fechaSeleccionada.isBefore(LocalDate.now().minusYears(18))) {

                return true;
            } else {
                mostrarError("Debe ser mayor de edad");
                return false;
            }
        } else {
            mostrarError("SeleccionarFechaNacimiento");
            return false;
        }
    }


    // Validar el correo electrónico
    private boolean validarCorreo(String correo) {
        Matcher matcher = EMAIL_PATTERN.matcher(correo);
        return matcher.matches();
    }

    private boolean verificarDNI() {
        String dni = txtDniPersona.getText().trim();
        if (!dni.isEmpty()) {
            if (dni.matches("\\d{8}")) { // Verificar que tenga exactamente 8 dígitos numéricos
                return true;
            } else {
                mostrarError("El DNI debe contener exactamente 8 dígitos numéricos.");
                return false;
            }
        } else {
            mostrarError("Por favor ingrese un DNI.");
            return false;
        }
    }

    public boolean verificarCorreo() {
        String correo = txtEmailPersona.getText().trim();
        if (!correo.isEmpty()) {
            if (validarCorreo(correo)) {
                return true;
            } else {
                mostrarError("El correo electrónico ingresado no es válido.");
                return false;
            }
        } else {
            mostrarError("Por favor ingrese un correo electrónico.");
            return false;
        }
    }

    @FXML

    public boolean verificarEsMiembro() {
        if (esMiembro.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar");
            alert.setHeaderText("¿Desea marcar a esta persona como miembro?");
            alert.setContentText("Si marca esta opción, la persona será considerada un miembro.");

            // Obtener respuesta del usuario
            ButtonType botonSi = new ButtonType("Sí");
            ButtonType botonNo = new ButtonType("No");
            alert.getButtonTypes().setAll(botonSi, botonNo);

            Optional<ButtonType> resultado = alert.showAndWait();
            if (resultado.isPresent() && resultado.get() == botonSi) {
                return true;
            } else {
                // Si el usuario elige "No", desmarcar el CheckBox
                esMiembro.setSelected(false);
                return false;
            }
        }
        return false;
    }

    private boolean verificarNombre() {
        String nombre = txtNombrePersona.getText().trim();
        if (!nombre.isEmpty()) {
            if (nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) { // Solo permite letras, espacios y letras acentuadas
                return true;
            } else {
                mostrarError("El nombre no puede contener símbolos ni números.");
                return false;
            }
        } else {
            mostrarError("Por favor ingrese un nombre.");
            return false;
        }
    }

    private boolean verificarApellido() {
        String apellido = txtApellidoPersona.getText().trim();
        if (!apellido.isEmpty()) {
            if (apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) { // Solo permite letras, espacios y letras acentuadas
                return true;
            } else {
                mostrarError("El apellido no puede contener símbolos ni números.");
                return false;
            }
        } else {
            mostrarError("Por favor ingrese un apellido.");
            return false;
        }
    }

    private void mostrarError(String mensaje) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText(mensaje);
        alertError.showAndWait();
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
}

