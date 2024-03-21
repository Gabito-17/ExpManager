package edu.unam.pooproject.Controller;

import edu.unam.pooproject.Repositorio.ExpedienteRepositorio;
import edu.unam.pooproject.Services.Enrutador;
import edu.unam.pooproject.db.Conexion;
import edu.unam.pooproject.modelo.Expediente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class ExpedienteController {
    //Informacion del expediente
    @FXML
    private TextField idExpediente;
    @FXML
    private TextField idIniciante;
    @FXML
    private DatePicker fechaIngresoExpediente;
    @FXML
    private TextArea textoExpediente;
    @FXML
    private Label nroExpedienteLabel;
    @FXML
    private Label idInicianteLabel;
    @FXML
    private Label resultadoText;


    private ObservableList<String> estadoList = FXCollections.observableArrayList("Alta", "Baja");
    //ComboBox "estado" del expediente
    @FXML
    private ComboBox comboBoxEstado;
    //Cargar y mostrar ComboBoxEstado del expediente
    private ExpedienteRepositorio expedienteRepositorio = new ExpedienteRepositorio(Conexion.getEntityManagerFactory());

    @FXML
    public void mostrarCombo(Event event) {
        comboBoxEstado.setItems(estadoList);
    }

    //Limpiar todos los inputs
    @FXML
    public void limpiarCampos(ActionEvent event) {
        idExpediente.clear();
        idIniciante.clear();
        fechaIngresoExpediente.setValue(null);
        textoExpediente.clear();
    }

    public void guardarExpediente(ActionEvent event) throws Exception {
        String idExp = idExpediente.getText();
        LocalDate fecha = fechaIngresoExpediente.getValue();
        String idInc = idIniciante.getText();

        if (idExp.matches("\\d+")) {
            Expediente expediente = new Expediente(Integer.parseInt(idExp), idIniciante.getText(), textoExpediente.getText(), Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()), true);
            expedienteRepositorio.crearExpediente(expediente);
            resultadoText.setText("Expediente Cargado");

        } else {
            nroExpedienteLabel.setText("Debe ser numero entero");
            System.out.println("El Debe contener solo digitos numericos");
        }
    }

    //Cerrar Sesion y ubicar en ventana "Login"
    @FXML
    public void cerrarSesion(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/login-view.fxml");
    }

    //Ubicar en ventana "Inicio"
    @FXML
    public void menuInicio(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/principal-view.fxml");
    }

    //Ubicar en ventana "Expedientes"
    @FXML
    public void menuExpedientes(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/expediente-view.fxml");
    }

    //Ubicar en ventana "Miembros"
    @FXML
    public void menuMiembros(ActionEvent event) {
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
}

