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
public class Usuario implements Serializable{

    private String codigo;
    private String pass;

    public Usuario() {
    }

    /**
     * Create a new user, and search a user in the date-base
     *
     * @param codigo
     * @param pass
     */
    public Usuario(String codigo, String pass) {
        this.codigo = codigo;
        this.pass = pass;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

}
