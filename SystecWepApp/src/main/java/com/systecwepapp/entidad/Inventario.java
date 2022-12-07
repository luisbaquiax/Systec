/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.entidad;

import java.io.Serializable;

/**
 *
 * @author luis
 */
public class Inventario implements Serializable {

    private String codigoProducto;
    private int cantidadExistente;

    public Inventario() {
    }

    /**
     * Create a new Inventario
     *
     * @param codigoProducto
     * @param cantidadExistente
     */
    public Inventario(String codigoProducto, int cantidadExistente) {
        this.codigoProducto = codigoProducto;
        this.cantidadExistente = cantidadExistente;
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
     * @return the cantidadExistente
     */
    public int getCantidadExistente() {
        return cantidadExistente;
    }

    /**
     * @param cantidadExistente the cantidadExistente to set
     */
    public void setCantidadExistente(int cantidadExistente) {
        this.cantidadExistente = cantidadExistente;
    }

}
