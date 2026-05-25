package com.rednova.controller;

import com.rednova.dao.UsuarioDAO;
import com.rednova.model.Usuario;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;

public class VentanaUsuarios {
    private TextField txtId = new TextField();
    private TextField txtNumControl = new TextField();
    private TextField txtCorreo = new TextField();
    private TextField txtNombre = new TextField();
    private TextField txtTipo = new TextField(); // Estudiante, Docente, etc.

    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Registro de Usuarios");
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #1A1A1D;");

        txtId.setPromptText("ID Usuario (para buscar/editar)");
        txtNumControl.setPromptText("Número de Control");
        txtCorreo.setPromptText("Correo Institucional");
        txtNombre.setPromptText("Nombre Completo");
        txtTipo.setPromptText("Tipo (Estudiante/Docente)");

        Button btnRegistrar = new Button("Registrar Nuevo");
        Button btnBuscar = new Button("Buscar por ID");

        btnRegistrar.setOnAction(e -> {
            try {
                // El constructor asume: id, control, correo, nombre, tipo, fechaActual, puntos, nivel
                Usuario u = new Usuario(0, txtNumControl.getText(), txtCorreo.getText(), 
                                        txtNombre.getText(), txtTipo.getText(), LocalDate.now(), 0, "Bronce");
                new UsuarioDAO().registrar(u);
                new Alert(Alert.AlertType.INFORMATION, "Usuario registrado con éxito").show();
            } catch (Exception ex) { 
                new Alert(Alert.AlertType.ERROR, "Error: " + ex.getMessage()).show(); 
            }
        });

        root.getChildren().addAll(new Label("USUARIOS"), txtId, btnBuscar, txtNumControl, txtCorreo, txtNombre, txtTipo, btnRegistrar);
        stage.setScene(new Scene(root, 350, 450));
        stage.show();
    }
}