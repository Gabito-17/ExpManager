package edu.unam.pooproject.controller;

import edu.unam.pooproject.Services.ExpedienteServicio;
import edu.unam.pooproject.Services.MinutaServicio;
import edu.unam.pooproject.Services.ReunionServicio;
import edu.unam.pooproject.db.Conexion;
import edu.unam.pooproject.modelo.Expediente;
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
    private Button editarMinuta;

    @FXML
    private Label lbIdReunion; // Este label contendrá el ID de la reunión

    @FXML
    private TableColumn<Minuta, Integer> colId;

    @FXML
    private TableColumn<Reunion, String> colFecha;

    @FXML
    private TableColumn<Expediente, String> colExpediente;

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

    private Minuta minutaSeleccionada = null;

    public void initialize() {
        // Inicializar servicios
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.minutaServicio = new MinutaServicio(this.repositorio);
        this.expedienteServicio = new ExpedienteServicio(this.repositorio);
        this.reunionServicio = new ReunionServicio(this.repositorio);

        // Configurar las propiedades de las columnas
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        //colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReunion().getFecha().toString()));
        //colExpediente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExpedientes().get(0).getTitulo()));
        colTema.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTema()));
        colResumen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getResumen()));

        // Inicializar la tabla con las minutas de la reunión actual
        rellenarTabla();

        // Bloquear inputs y botones
        bloquearInputs(true);

        // Añadir evento de selección de fila en la tabla
        tvMinutasDeLaReunion.setOnMouseClicked(this::seleccionarMinuta);
    }

    @FXML
    void cargarMinuta(ActionEvent event) {
        if (minutaSeleccionada != null) {
            // Obtener valores de los inputs
            String tema = txtTema.getText();
            String resumen = taResumen.getText();

            // Validar que los campos no estén vacíos antes de guardar
            if (tema.isEmpty() || resumen.isEmpty()) {
                System.out.println("Los campos Tema y Resumen no pueden estar vacíos.");
                return;
            }

            // Actualizar la minuta seleccionada
            minutaSeleccionada.setTema(tema);
            minutaSeleccionada.setResumen(resumen);

            // Guardar cambios en la base de datos
            minutaServicio.editarMinuta(minutaSeleccionada);

            // Actualizar la tabla
            rellenarTabla();

            // Bloquear inputs
            bloquearInputs(true);
        }
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        // Limpiar campos
        txtTema.clear();
        taResumen.clear();
    }

    @FXML
    void editarMinuta(ActionEvent event) {
        if (minutaSeleccionada != null) {
            // Validar si los campos de la minuta están vacíos
            if (minutaSeleccionada.getTema().isEmpty() && minutaSeleccionada.getResumen().isEmpty()) {
                // Habilitar inputs para cargar minuta
                bloquearInputs(false);
            } else {
                // Mostrar mensaje de que no se puede editar una minuta ya cargada
                System.out.println("La minuta ya ha sido cargada y no puede ser editada.");
            }
        }
    }

    void rellenarTabla() {
        // Obtener la reunión seleccionada a través del ID de reunión proporcionado
        int idReunion = Integer.parseInt(lbIdReunion.getText());

        // Buscar la reunión en la base de datos
        Reunion reunionSeleccionada = reunionServicio.buscarPorId(idReunion);

        if (reunionSeleccionada != null) {
            // Obtener todas las minutas de la reunión actual
            List<Minuta> minutas = reunionSeleccionada.getMinutas();

            // Convertir la lista de minutas en una ObservableList
            ObservableList<Minuta> listaMinutas = FXCollections.observableArrayList(minutas);

            // Asignar la lista de minutas al TableView
            tvMinutasDeLaReunion.setItems(listaMinutas);
        } else {
            System.out.println("Reunión no encontrada.");
        }
    }

    private void seleccionarMinuta(MouseEvent event) {
        // Obtener la minuta seleccionada
        minutaSeleccionada = tvMinutasDeLaReunion.getSelectionModel().getSelectedItem();

        if (minutaSeleccionada != null) {
            // Mostrar los detalles de la minuta en los inputs
            txtTema.setText(minutaSeleccionada.getTema());
            taResumen.setText(minutaSeleccionada.getResumen());

            // Bloquear inputs si la minuta ya contiene información
            bloquearInputs(!minutaSeleccionada.getTema().isEmpty() || !minutaSeleccionada.getResumen().isEmpty());
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
