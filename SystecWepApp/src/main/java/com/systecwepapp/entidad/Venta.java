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
public class Venta implements Serializable {

    private int id;

    public Venta(int id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

}
