package io.github.franxo177.pry_agendacontactos.controller;

import io.github.franxo177.pry_agendacontactos.model.Contacto;
import io.github.franxo177.pry_agendacontactos.repository.ElementoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrincipalController {

    @FXML
    private TextField txtNombre, txtApellido, txtTelefono;

    @FXML
    private TableView<Contacto> tablaContactos;

    @FXML
    private TableColumn<Contacto, String> colNombre,colApellido,colTelefono;

    @FXML
    private Label lblMensaje;

    private final ElementoRepository repo = new ElementoRepository();
    private final ObservableList<Contacto> datos = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tablaContactos.setItems(datos);
        refrescar();
    }

    @FXML
    private void agregar(ActionEvent actionEvent) {
        try {
            String nombre = txtNombre.getText().trim(), apellido = txtApellido.getText().trim(), telefono = txtTelefono.getText().trim();
            Contacto nuevo;
            nuevo = new Contacto(nombre, apellido, telefono);
            repo.agregar(nuevo);
            refrescar();
            lblMensaje.setText("Contacto agregado con exito!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            lblMensaje.setText(e.getMessage());
        }
    }

    @FXML
    private void eliminar(ActionEvent actionEvent) {
        Contacto elegido = tablaContactos.getSelectionModel().getSelectedItem();
        if (elegido == null) {
            lblMensaje.setText("Seleccione una fila");
            return;
        }
        try {
            repo.eliminar(elegido.getTelefono());
            refrescar();
            lblMensaje.setText("Contacto eliminado con exito!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            lblMensaje.setText(e.getMessage());
        }
    }

    private void refrescar() {
        datos.setAll(repo.listar());
    }
}
