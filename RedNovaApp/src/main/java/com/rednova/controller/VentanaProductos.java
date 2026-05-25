package com.rednova.controller;

import com.rednova.dao.ProductoDAO;
import com.rednova.model.Producto;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaProductos {
    private TextField txtId = new TextField();
    private TextField txtNombre = new TextField();
    private TextField txtCategoria = new TextField();
    private TextField txtPrecio = new TextField();
    private TextField txtCosto = new TextField();
    private TextField txtStock = new TextField();
    private TextField txtMinimo = new TextField();

    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Gestión de Inventario");
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #1A1A1D;");

        // UI
        txtId.setPromptText("ID Producto (para buscar)");
        txtNombre.setPromptText("Nombre");
        txtCategoria.setPromptText("Categoría");
        txtPrecio.setPromptText("Precio");
        txtCosto.setPromptText("Costo");
        txtStock.setPromptText("Stock Actual");
        txtMinimo.setPromptText("Stock Mínimo");

        Button btnBuscar = new Button("Buscar");
        Button btnGuardar = new Button("Guardar Nuevo");
        Button btnActualizar = new Button("Actualizar");
        Button btnEliminar = new Button("Eliminar");

        // Lógica de Buscar
        btnBuscar.setOnAction(e -> {
            try {
                Producto p = new ProductoDAO().buscarPorId(Integer.parseInt(txtId.getText()));
                if(p != null) {
                    txtNombre.setText(p.getNombre());
                    txtCategoria.setText(p.getCategoria());
                    txtPrecio.setText(String.valueOf(p.getPrecio()));
                    txtCosto.setText(String.valueOf(p.getCosto()));
                    txtStock.setText(String.valueOf(p.getStockActual()));
                    txtMinimo.setText(String.valueOf(p.getStockMinimo()));
                } else { new Alert(Alert.AlertType.WARNING, "No encontrado").show(); }
            } catch (Exception ex) { new Alert(Alert.AlertType.ERROR, "ID debe ser numérico").show(); }
        });

        // Lógica de Guardar
        btnGuardar.setOnAction(e -> {
            try {
                Producto p = new Producto(0, txtNombre.getText(), txtCategoria.getText(), 
                             Double.parseDouble(txtPrecio.getText()), Double.parseDouble(txtCosto.getText()), 
                             Integer.parseInt(txtStock.getText()), Integer.parseInt(txtMinimo.getText()));
                new ProductoDAO().insertar(p);
                new Alert(Alert.AlertType.INFORMATION, "Producto Guardado").show();
            } catch (Exception ex) { new Alert(Alert.AlertType.ERROR, "Error en los datos").show(); }
        });

        // Lógica de Eliminar
        btnEliminar.setOnAction(e -> {
            try {
                new ProductoDAO().eliminar(Integer.parseInt(txtId.getText()));
                new Alert(Alert.AlertType.INFORMATION, "Producto Eliminado").show();
            } catch (Exception ex) { new Alert(Alert.AlertType.ERROR, "Error al eliminar").show(); }
        });

        root.getChildren().addAll(new Label("INVENTARIO"), txtId, btnBuscar, txtNombre, txtCategoria, txtPrecio, txtCosto, txtStock, txtMinimo, btnGuardar, btnActualizar, btnEliminar);
        stage.setScene(new Scene(root, 350, 550));
        stage.show();
    }
}