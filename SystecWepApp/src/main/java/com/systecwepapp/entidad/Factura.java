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
public class Factura {

    private int id;
    private int idVenta;
    private String fecha;
    private double totalPago;
    private double precioUnitario;
    private int cantidadProductos;
    private String codigoProducto;
    private String usuario;

    public Factura() {
    }

    /**
     * Recuperar una factura
     *
     * @param id
     * @param idVenta
     * @param fecha
     * @param totalPago
     * @param cantidadProductos
     * @param codigoProducto
     * @param usuario
     */
    public Factura(int id, int idVenta, String fecha, double totalPago, int cantidadProductos, String codigoProducto, String usuario) {
        this.id = id;
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.totalPago = totalPago;
        this.cantidadProductos = cantidadProductos;
        this.codigoProducto = codigoProducto;
        this.usuario = usuario;
    }

    /**
     * Generate a new Factura and save data in the database
     *
     * @param idVenta
     * @param fecha
     * @param totalPago
     * @param cantidadProductos
     * @param codigoProducto
     * @param usuario
     */
    public Factura(int idVenta, String fecha, double totalPago, int cantidadProductos, String codigoProducto, String usuario) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.totalPago = totalPago;
        this.cantidadProductos = cantidadProductos;
        this.codigoProducto = codigoProducto;
        this.usuario = usuario;
    }

    public Factura(String fecha, double totalPago, double precioUnitario, int cantidadProductos, String codigoProducto, String usuario) {
        this.fecha = fecha;
        this.totalPago = totalPago;
        this.precioUnitario = precioUnitario;
        this.cantidadProductos = cantidadProductos;
        this.codigoProducto = codigoProducto;
        this.usuario = usuario;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the idVenta
     */
    public int getIdVenta() {
        return idVenta;
    }

    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the totalPago
     */
    public double getTotalPago() {
        return totalPago;
    }

    /**
     * @param totalPago the totalPago to set
     */
    public void setTotalPago(double totalPago) {
        this.totalPago = totalPago;
    }

    /**
     * @return the cantidadProductos
     */
    public int getCantidadProductos() {
        return cantidadProductos;
    }

    /**
     * @param cantidadProductos the cantidadProductos to set
     */
    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    /**
     * @return the codigoProducto
     */
    public String getCodigoProducto() {
        return codigoProducto;
    }

    /**
     * @param codigoProducto the codigoProducto to set
     */
    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

}
