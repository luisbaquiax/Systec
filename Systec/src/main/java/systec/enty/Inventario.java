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
public class Inventario implements Serializable {

    private int id;
    private String codigoProducto;
    private int cantidadExistente;

    /**
     * Insert a new producto in the DB
     *
     * @param id
     * @param codigoProducto
     * @param cantidadExistente
     */
    public Inventario(int id, String codigoProducto, int cantidadExistente) {
        this.id = id;
        this.codigoProducto = codigoProducto;
        this.cantidadExistente = cantidadExistente;
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
