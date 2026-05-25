package com.rednova.controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller: VentanaRenta
 * Gestiona el monitoreo de hardware universitario.
 * Incluye lógica de visualización de estados (Disponible/En Uso).
 */
public class VentanaRenta {
    public void mostrar() {
        Stage stage = new Stage();
        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: #1A1A1D;"); // Estilo Dark RedNova
        root.setPadding(new Insets(20));

        // Título del módulo
        Label titulo = new Label("MONITOR DE EQUIPOS (RENTA TECNOLÓGICA)");
        titulo.setStyle("-fx-text-fill: #C3073F; -fx-font-size: 20px; -fx-font-weight: bold;");
        
        // Etiqueta informativa del estado del sistema
        Label info = new Label("Verde: Disponible | Rojo: En Uso | Amarillo: Mantenimiento");
        info.setStyle("-fx-text-fill: white;");

        // Layout de la ventana
        root.getChildren().addAll(titulo, info);
        stage.setScene(new Scene(root, 450, 300));
        stage.setTitle("Renta Tecnológica");
        stage.show();
    }
}