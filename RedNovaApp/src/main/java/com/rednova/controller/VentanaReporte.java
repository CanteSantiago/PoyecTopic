package com.rednova.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

public class VentanaReporte {

    public void mostrarReporte() {
        Stage escenarioReporte = new Stage();
        escenarioReporte.setTitle("Reporte de Ventas (Consulta Multiple)");

        // Tabla para visualizar los datos del reporte
        TableView<ReporteVenta> tablaReporte = new TableView<>();

        // Definicion de columnas de la tabla
        TableColumn<ReporteVenta, String> colProducto = new TableColumn<>("Producto");
        colProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));

        TableColumn<ReporteVenta, Integer> colCantidad = new TableColumn<>("Cantidad");
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        TableColumn<ReporteVenta, Double> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioAplicado"));

        TableColumn<ReporteVenta, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaCompleta"));

        tablaReporte.getColumns().addAll(colProducto, colCantidad, colPrecio, colFecha);

        // Cargar informacion de la base de datos
        cargarDatos(tablaReporte);

        VBox contenedor = new VBox(tablaReporte);
        escenarioReporte.setScene(new Scene(contenedor, 550, 400));
        escenarioReporte.show();
    }

    private void cargarDatos(TableView<ReporteVenta> tabla) {
        // Consulta SQL utilizando JOIN para relacionar ventas, detalle y productos
        String consultaJoin = "SELECT p.nombreProducto, dv.cantidad, dv.precioAplicado, v.Dia, v.Mes, v.Anio " +
                              "FROM Venta v " +
                              "JOIN DetalleVenta dv ON v.idVenta = dv.idVenta " +
                              "JOIN Producto p ON dv.idProducto = p.idProducto";

        ObservableList<ReporteVenta> listaVentas = FXCollections.observableArrayList();

        // Conexion a base de datos y ejecucion de consulta
        try (Connection conexionBaseDatos = com.rednova.util.Conexion.conectar();
             PreparedStatement sentenciaPreparada = conexionBaseDatos.prepareStatement(consultaJoin);
             ResultSet resultadoConsulta = sentenciaPreparada.executeQuery()) {

            // Recorrer resultados y agregarlos a la lista
            while (resultadoConsulta.next()) {
                String fecha = resultadoConsulta.getString("Dia") + "/" + 
                               resultadoConsulta.getString("Mes") + "/" + 
                               resultadoConsulta.getString("Anio");
                
                listaVentas.add(new ReporteVenta(
                    resultadoConsulta.getString("nombreProducto"),
                    resultadoConsulta.getInt("cantidad"),
                    resultadoConsulta.getDouble("precioAplicado"),
                    fecha
                ));
            }
            tabla.setItems(listaVentas);
        } catch (SQLException excepcionSql) {
            excepcionSql.printStackTrace();
        }
    }
}