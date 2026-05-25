package com.rednova.model;

public class Producto {
    // Atributos de la clase
    private int id;
    private String nombre, categoria;
    private double precio, costo;
    private int stockActual, stockMinimo;

    // Constructor para inicializar el objeto
    public Producto(int id, String nombre, String categoria, double precio, double costo, int stockActual, int stockMinimo) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.costo = costo;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
    }

    // Getters para acceder a los atributos
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public double getCosto() { return costo; }
    public int getStockActual() { return stockActual; }
    public int getStockMinimo() { return stockMinimo; }
}