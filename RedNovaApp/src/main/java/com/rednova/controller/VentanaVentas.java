package com.rednova.controller;

import com.rednova.dao.VentaDAO;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VentanaVentas {
    private final String styleInput = "-fx-background-color: #2F3136; -fx-text-fill: white; -fx-border-color: #C3073F; -fx-padding: 5;";

    public void mostrar() {
        Stage stage = new Stage();
        VBox root = new VBox(15);
        root.setStyle("-fx-background-color: #1A1A1D;");
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        TextField txtUsuarioId = new TextField(); txtUsuarioId.setPromptText("ID Usuario"); txtUsuarioId.setStyle(styleInput);
        TextField txtProductoId = new TextField(); txtProductoId.setPromptText("ID Producto"); txtProductoId.setStyle(styleInput);
        TextField txtCantidad = new TextField(); txtCantidad.setPromptText("Cantidad"); txtCantidad.setStyle(styleInput);
        TextField txtPrecio = new TextField(); txtPrecio.setPromptText("Precio Unitario"); txtPrecio.setStyle(styleInput);

        Button btnRegistrar = new Button("Registrar Venta");
        btnRegistrar.setStyle("-fx-background-color: #C3073F; -fx-text-fill: white; -fx-font-weight: bold;");

        btnRegistrar.setOnAction(e -> {
            try {
                int uId = Integer.parseInt(txtUsuarioId.getText());
                int pId = Integer.parseInt(txtProductoId.getText());
                int cant = Integer.parseInt(txtCantidad.getText());
                double precio = Double.parseDouble(txtPrecio.getText());
                double total = cant * precio;

                new VentaDAO().registrarVentaCompleta(uId, total, total, "Efectivo", pId, cant, precio);
                
                new Alert(Alert.AlertType.INFORMATION, "Venta realizada con éxito. ¡Puntos sumados!").show();
                stage.close();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Error: Revisa los campos numéricos").show();
            }
        });

        root.getChildren().addAll(new Label("NUEVA VENTA"), txtUsuarioId, txtProductoId, txtCantidad, txtPrecio, btnRegistrar);
        stage.setScene(new Scene(root, 300, 400));
        stage.show();
    }
}