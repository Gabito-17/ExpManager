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
import java.util.Optional;

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
    private TableColumn<Minuta, String> colTema;

    @FXML
    private TableColumn<Minuta, String> colResumen;
    @FXML
    private TableColumn<Minuta, String> colExpediente;

    @FXML
    private TextArea taResumen;

    @FXML
    private TableView<Minuta> tvMinutasDeLaReunion;

    @FXML
    private TextField txtTema;
    private VentanaEmergente ventanaEmergente = new VentanaEmergente();
    private Minuta minutaSeleccionada = null;
    private Reunion reunion;
    private String fechaReunion;

    public void initialize() {
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.minutaServicio = new MinutaServicio(this.repositorio);
        this.expedienteServicio = new ExpedienteServicio(this.repositorio);
        this.reunionServicio = new ReunionServicio(this.repositorio);
        // Configurar las propiedades de las columnas
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colExpediente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExpediente().getTitulo()));
        colTema.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTema()));
        colResumen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getResumen()));
        tvMinutasDeLaReunion.setOnMouseClicked(this::seleccionarMinuta);
        // Bloquear inputs
        bloquearInputs(true);
        rellenarTabla(reunion);

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
            // Mostrar un cuadro de diálogo de confirmación
            Alert alertConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirmacion.setTitle("Confirmar");
            alertConfirmacion.setHeaderText("¿Estás seguro de cargar esta minuta?");
            alertConfirmacion.setContentText("La minuta cargada no se podra editar.");

            Optional<ButtonType> resultado = alertConfirmacion.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {

                // Actualizar la minuta seleccionada
                minutaSeleccionada.setTema(tema);
                minutaSeleccionada.setResumen(resumen);

                // Guardar cambios en la base de datos
                minutaServicio.editarMinuta(minutaSeleccionada);

                // Bloquear inputs
                bloquearInputs(true);

                initialize();
                limpiarCampos();
            }
        } else {
            // Mostrar mensaje de que no se puede editar una minuta ya cargada
            ventanaEmergente.mostrarError("No se puede editar la minuta ya cargada.");
        }
    }

    @FXML
    void limpiarCampos() {
        // Limpiar campos
        txtTema.clear();
        taResumen.clear();
    }

    public void setFechaReunion(String reunionFecha) {
        this.fechaReunion = reunionFecha;
    }

    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
        if (reunion != null) {
            setFechaReunion(reunion.getFecha().toString());
            lbIdReunion.setText(String.valueOf(reunion.getId()));
            lbReunionFecha.setText(fechaReunion);
            rellenarTabla(reunion);
        }
    }


    private void rellenarTabla(Reunion reunion) {
        if (reunion != null) {
            List<Minuta> minutas = reunion.getMinutas();
            ObservableList<Minuta> listaMinutas = FXCollections.observableArrayList(minutas);
            tvMinutasDeLaReunion.setItems(listaMinutas);
            tvMinutasDeLaReunion.refresh();
        }
    }

    @FXML
    private void seleccionarMinuta(MouseEvent event) {
        // Obtener la minuta seleccionada
        if (tvMinutasDeLaReunion.getSelectionModel().getSelectedItem() != null) {
            minutaSeleccionada = tvMinutasDeLaReunion.getSelectionModel().getSelectedItem();
            lbIdMinuta.setText(String.valueOf(minutaSeleccionada.getId()));
            lbIdExpediente.setText(String.valueOf(minutaSeleccionada.getExpediente().getId()));
            lbReunionFecha.setText(fechaReunion);
            if (minutaSeleccionada.getTema() == null && minutaSeleccionada.getResumen() == null) {
                // Habilitar inputs para cargar minuta
                bloquearInputs(false);
                txtTema.setText(minutaSeleccionada.getTema());
                taResumen.setText(minutaSeleccionada.getResumen());
            } else {
                // Bloquear inputs
                bloquearInputs(true);
                txtTema.setText(minutaSeleccionada.getTema());
                taResumen.setText(minutaSeleccionada.getResumen());
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
