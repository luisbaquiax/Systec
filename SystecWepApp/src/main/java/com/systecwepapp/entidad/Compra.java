/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.entidad;

import java.io.Serializable;

/**
 *
 * @author Luis
 */
public class Compra implements Serializable {

    private int id;
    private String producto;
    private String fecha;
    private int unidades;

    /**
     *
     * @param id
     * @param producto
     * @param fecha
     * @param unidades
     */
    public Compra(int id, String producto, String fecha, int unidades) {
        this.id = id;
        this.producto = producto;
        this.fecha = fecha;
        this.unidades = unidades;
    }

    /**
     *
     * @param producto
     * @param fecha
     * @param unidades
     */
    public Compra(String producto, String fecha, int unidades) {
        this.producto = producto;
        this.fecha = fecha;
        this.unidades = unidades;
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
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
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
     * @return the unidades
     */
    public int getUnidades() {
        return unidades;
    }

    /**
     * @param unidades the unidades to set
     */
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

}
