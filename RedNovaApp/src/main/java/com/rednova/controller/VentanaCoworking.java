package com.rednova.controller;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Controller: VentanaCoworking
 * Gestiona la reserva de espacios físicos (individuales, lounge, grupales).
 * Implementa validaciones para asegurar que las reservas tengan datos coherentes.
 */
public class VentanaCoworking {
    private final String styleFondo = "-fx-background-color: #1A1A1D;";
    private final String styleInput = "-fx-background-color: #2F3136; -fx-text-fill: white; -fx-border-color: #C3073F;";

    public void mostrar() {
        Stage stage = new Stage();
        VBox root = new VBox(15);
        root.setStyle(styleFondo);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Campos de reserva según las instrucciones
        TextField txtUsuario = new TextField(); txtUsuario.setPromptText("ID del Usuario");
        txtUsuario.setStyle(styleInput);
        
        DatePicker fechaReserva = new DatePicker(); // Selector de fecha
        fechaReserva.setPromptText("Fecha de Reserva");
        
        TextField txtPersonas = new TextField(); txtPersonas.setPromptText("Número de personas");
        txtPersonas.setStyle(styleInput);

        Button btnReservar = new Button("Confirmar Reserva");
        btnReservar.setStyle("-fx-background-color: #C3073F; -fx-text-fill: white; -fx-font-weight: bold;");

        btnReservar.setOnAction(e -> {
            // VALIDACIÓN: Verificar que los campos no estén vacíos
            if(txtUsuario.getText().isEmpty() || txtPersonas.getText().isEmpty() || fechaReserva.getValue() == null) {
                new Alert(Alert.AlertType.ERROR, "Dato Incorrecto: Llene todos los campos de reserva").show();
                return;
            }

            try {
                // VALIDACIÓN: El número de personas debe ser un número entero
                int num = Integer.parseInt(txtPersonas.getText());
                if(num <= 0) throw new Exception(); // No permitir 0 o negativos

                new Alert(Alert.AlertType.INFORMATION, "Reserva agendada para el día: " + fechaReserva.getValue()).show();
                stage.close();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Dato Incorrecto: Ingrese un número de personas válido").show();
            }
        });

        Button btnRegresar = new Button("Regresar");
        btnRegresar.setOnAction(e -> stage.close());

        root.getChildren().addAll(new Label("RESERVA DE ESPACIOS COWORKING"), txtUsuario, fechaReserva, txtPersonas, btnReservar, btnRegresar);
        stage.setScene(new Scene(root, 400, 400));
        stage.setTitle("Coworking - RedNova");
        stage.show();
    }
}