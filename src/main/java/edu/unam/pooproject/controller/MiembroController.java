package edu.unam.pooproject.controller;

import edu.unam.pooproject.Services.Enrutador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MiembroController {

    //Informacion del miembro
    @FXML
    TextField txtDniMiembro;
    @FXML
    TextField txtNombreMiembro;
    @FXML
    TextField txtApellidoMiembro;
    @FXML
    TextField txtEmailMiembro;
    @FXML
    Label lblResultado;
    @FXML
    TextArea txtTexto;

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


    //Acciones del formulario
    //cargar (o modificar) un registro
    //verifica el id, si ya existe debe modificarlo, de lo contrario crea uno nuevo
    @FXML
    public void cargarRegistro(ActionEvent event) {

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

}
