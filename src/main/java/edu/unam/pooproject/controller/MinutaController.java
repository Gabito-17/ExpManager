package edu.unam.pooproject.controller;

import edu.unam.pooproject.Services.ExpedienteServicio;
import edu.unam.pooproject.Services.MinutaServicio;
import edu.unam.pooproject.Services.ReunionServicio;
import edu.unam.pooproject.Services.VentanaEmergente;
import edu.unam.pooproject.db.Conexion;
import edu.unam.pooproject.modelo.Minuta;
import edu.unam.pooproject.modelo.Reunion;
import edu.unam.pooproject.repositorio.Repositorio;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class MinutaController extends NavegacionController {
    private Repositorio repositorio;
    private MinutaServicio minutaServicio;
    private ExpedienteServicio expedienteServicio;
    private ReunionServicio reunionServicio;

    @FXML
    private Button btnCargar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Label lbIdReunion;

    @FXML
    private Label lbIdMinuta;

    @FXML
    private Label lbIdExpediente;

    @FXML
    private Label lbReunionFecha;

    @FXML
    private TableColumn<Minuta, Integer> colId;

    @FXML
    private TableColumn<Minuta, String> colExpediente;

    @FXML
    private TableColumn<Minuta, String> colTema;

    @FXML
    private TableColumn<Minuta, String> colResumen;

    @FXML
    private TextArea taResumen;

    @FXML
    private TableView<Minuta> tvMinutasDeLaReunion;

    @FXML
    private TextField txtTema;
    private VentanaEmergente ventanaEmergente = new VentanaEmergente();
    private Minuta minutaSeleccionada = null;
    private Integer idReunion;
    private String fechaReunion;

    public void initialize() {
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.minutaServicio = new MinutaServicio(this.repositorio);
        this.expedienteServicio = new ExpedienteServicio(this.repositorio);
        this.reunionServicio = new ReunionServicio(this.repositorio);
        lbIdReunion.setText(String.valueOf(idReunion));

        // Configurar las propiedades de las columnas
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colExpediente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExpediente().toString()));
        colTema.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTema().toString()));
        colResumen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getResumen().toString()));
        // Bloquear inputs
        bloquearInputs(true);
    }

    @FXML
    void cargarMinuta(ActionEvent event) {
        if (minutaSeleccionada != null) {
            String tema = txtTema.getText();
            String resumen = taResumen.getText();

            // Validar que los campos no estén vacíos antes de guardar
            if (tema.isEmpty() || resumen.isEmpty()) {
                ventanaEmergente.mostrarError("Campos vacío, por favor, llena todos los campos antes de cargar la minuta.");
                return;
            }
            ventanaEmergente.mostrarConfirmacion("¿Esta seguro que desea guardar la minuta? Una vez guardada no podra ser modificada " + minutaSeleccionada.getId(), "La minuta se guardó exitosamente.");

            // Actualizar la minuta seleccionada
            minutaSeleccionada.setTema(tema);
            minutaSeleccionada.setResumen(resumen);

            // Guardar cambios en la base de datos
            minutaServicio.editarMinuta(minutaSeleccionada);

            // Bloquear inputs
            bloquearInputs(true);
        } else {
            // Mostrar mensaje de que no se puede editar una minuta ya cargada
            ventanaEmergente.mostrarError("No se puede editar la minuta ya cargada.");
        }
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        // Limpiar campos
        txtTema.clear();
        taResumen.clear();
    }

    public void setFechaReunion(String reunionFecha) {
        this.fechaReunion = reunionFecha;
    }

    public void setReunionId(int reunionId) {
        this.idReunion = reunionId;
        rellenarTabla(reunionId);
    }

    private void rellenarTabla(int reunionId) {
        Reunion reunion = reunionServicio.buscarPorId(reunionId);
        setFechaReunion(reunion.getFecha().toString());
        if (reunion != null) {
            List<Minuta> minutas = reunion.getMinutas();
            ObservableList<Minuta> listaMinutas = FXCollections.observableArrayList(minutas);
            tvMinutasDeLaReunion.setItems(listaMinutas);
            System.out.println(listaMinutas);
        }
    }

    @FXML
    private void seleccionarMinuta(MouseEvent event) {
        // Obtener la minuta seleccionada
        if (tvMinutasDeLaReunion.getSelectionModel().getSelectedItem() != null) {
            minutaSeleccionada = tvMinutasDeLaReunion.getSelectionModel().getSelectedItem();
            lbIdMinuta.setText(String.valueOf(minutaSeleccionada.getId()));
            lbReunionFecha.setText(fechaReunion);
            txtTema.setText(minutaSeleccionada.getTema());
            taResumen.setText(minutaSeleccionada.getResumen());
            if (minutaSeleccionada.getTema().isEmpty() && minutaSeleccionada.getResumen().isEmpty()) {
                // Habilitar inputs para cargar minuta
                bloquearInputs(false);
            } else {
                // Bloquear inputs
                bloquearInputs(true);
            }
        }
    }

    private void bloquearInputs(boolean bloquear) {
        // Bloquear o desbloquear inputs y botones
        txtTema.setDisable(bloquear);
        taResumen.setDisable(bloquear);
        btnCargar.setDisable(bloquear);
        btnLimpiar.setDisable(bloquear);
    }
}
