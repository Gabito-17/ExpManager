package edu.unam.pooproject.controller;

import edu.unam.pooproject.Services.*;
import edu.unam.pooproject.db.Conexion;
import edu.unam.pooproject.modelo.Persona;
import edu.unam.pooproject.repositorio.Repositorio;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.Optional;

public class MiembroController {

    //Informacion del miembro
    @FXML
    private Label lblDni;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblApellido;
    @FXML
    private Label lblCantReuniones;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblFechaNacimiento;
    @FXML
    private TextArea txtTexto;
    @FXML
    private TableView<Persona> tvMiembros;
    @FXML
    private TableColumn<Persona, String> colDni;
    @FXML
    private TableColumn<Persona, String> colFechaNacimiento;
    @FXML
    private TableColumn<Persona, String> colApellido;
    @FXML
    private TableColumn<Persona, String> colNombre;
    @FXML
    private TableColumn<Persona, String> colEmail;

    private VentanaEmergente ventana = new VentanaEmergente();
    private Repositorio repositorio;
    private ExpedienteServicio expedienteServicio;
    private PersonaServicio personaServicio;
    private ReunionServicio reunionServicio;

    public void initialize() {
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.personaServicio = new PersonaServicio(this.repositorio);
        this.reunionServicio = new ReunionServicio(this.repositorio);
        this.expedienteServicio = new ExpedienteServicio(this.repositorio);

        // Configurar las columnas del TableView para que muestren los datos de Persona
        colDni.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDni()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colFechaNacimiento.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaNacimiento().toString()));

        rellenarTabla();

    }

    private void rellenarTabla() {
        // Obtener todas las personas de la base de datos a través del servicio
        List<Persona> miembros = personaServicio.obtenerMiembros();

        // Convertir la lista de personas en una ObservableList
        ObservableList<Persona> listamiembros = FXCollections.observableArrayList(miembros);

        // Asignar la lista de personas al TableView
        tvMiembros.setItems(listamiembros);
    }

    @FXML
    public void quitarMiembro(ActionEvent event) {
        // Obtener el expediente seleccionado en la tabla
        Persona miembroSeleccionado = tvMiembros.getSelectionModel().getSelectedItem();

        // Verificar si hay algun expediente seleccionado
        if (miembroSeleccionado == null) {
            ventana.mostrarError("Debe seleccionar un expediente de la tabla para eliminarlo");
            return;
        }

        // Mostrar un cuadro de diálogo de confirmación
        Alert alertConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmacion.setTitle("Confirmar");
        alertConfirmacion.setHeaderText("¿Estás seguro de quitar a esta persona del consejo?");
        alertConfirmacion.setContentText("Se quitara permanentemente a la persona del consejo.");

        Optional<ButtonType> resultado = alertConfirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Eliminar el expediente seleccionado
            PersonaServicio servicio = new PersonaServicio(repositorio);
            servicio.quitarDelConsejo(miembroSeleccionado);

            // Actualizar la tabla
            rellenarTabla();
        }
    }


    //Ubicar en ventana "Expediente"
    @FXML
    public void menuExpediente(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/expediente-view.fxml");
    }

    //Ubicar en ventana "Miembro"
    @FXML
    public void menuMiembro(ActionEvent event) {
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

    @FXML
    public void menuPersona(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/personas-view.fxml");
    }


    //limpiar campos
    @FXML
    public void limpiarCampos(ActionEvent event) {

    }

    //modificar registro
    //toma el registro seleccionado en la tabla y rellena los campos en la pantalla de carga para poder modificarlo. El id no debe ser modificable
    @FXML
    public void modificar(ActionEvent event) {

    }

    //eliminar registro
    //toma el registro seleccionado en la tabla y pide confirmacion para eliminarlo
    @FXML
    public void eliminar(ActionEvent event) {

    }

    public void verDetalles(ActionEvent event) {
        Persona miembroSeleccionado = tvMiembros.getSelectionModel().getSelectedItem();
        if (miembroSeleccionado != null) {
            lblDni.setText(miembroSeleccionado.getDni());
            lblEmail.setText(miembroSeleccionado.getEmail());
            lblNombre.setText(miembroSeleccionado.getNombre());
            lblApellido.setText(miembroSeleccionado.getApellido());
            lblFechaNacimiento.setText(miembroSeleccionado.getFechaNacimiento().toString());
        } else
            ventana.mostrarError("Debe seleccionar un miembro para ver los detalles del mismo");
    }
}
