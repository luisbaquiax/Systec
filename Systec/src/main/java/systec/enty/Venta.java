/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systec.enty;

import java.io.Serializable;

/**
 *
 * @author luis
 */
public class Venta implements Serializable {

    private int id;
    private String codigoProducto;
    private int cantidadProductos;
    private double cantidadPago;

    /**
     * For insert a now producto in the DB
     *
     * @param id
     * @param codigoProducto
     * @param cantidadProductos
     * @param cantidadPago
     */
    public Venta(int id, String codigoProducto, int cantidadProductos, double cantidadPago) {
        this.id = id;
        this.codigoProducto = codigoProducto;
        this.cantidadProductos = cantidadProductos;
        this.cantidadPago = cantidadPago;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the cantidadPago
     */
    public double getCantidadPago() {
        return cantidadPago;
    }

    /**
     * @param cantidadPago the cantidadPago to set
     */
    public void setCantidadPago(double cantidadPago) {
        this.cantidadPago = cantidadPago;
    }

}
