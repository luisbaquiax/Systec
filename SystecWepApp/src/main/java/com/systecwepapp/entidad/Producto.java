/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.entidad;

/**
 *
 * @author luis
 */
public class Producto {

    private String codigo;
    private String nombre;
    private String tipo;
    private double precioUnitario;
    private int cantidadExistente;

    public Producto() {
    }

    /**
     * Create a new product
     *
     * @param codigo
     * @param nombre
     * @param tipo
     * @param precioUnitario
     */
    public Producto(String codigo, String nombre, String tipo, double precioUnitario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioUnitario = precioUnitario;
    }

    /**
     * Create a new product
     *
     * @param codigo
     * @param nombre
     * @param tipo
     * @param precioUnitario
     * @param cantidadExistente
     */
    public Producto(String codigo, String nombre, String tipo, double precioUnitario, int cantidadExistente) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioUnitario = precioUnitario;
        this.cantidadExistente = cantidadExistente;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", tipo=" + tipo + ", precioUnitario=" + precioUnitario + ", cantidadExistente=" + cantidadExistente + '}';
    }

    public int getCantidadExistente() {
        return cantidadExistente;
    }

    public void setCantidadExistente(int cantidadExistente) {
        this.cantidadExistente = cantidadExistente;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return the precioUnitario
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

}
