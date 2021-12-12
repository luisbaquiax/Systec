/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.dataBase.modelo;

import com.sun.org.apache.xpath.internal.Arg;
import com.systecwepapp.dataBase.ConeccionDB;
import com.systecwepapp.entidad.Venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */
public class VentaDB {

    private static final String INSERT = "INSERT INTO venta VALUES()";
    private static final String ULTIMO = "SELECT MAX(id) AS ultimo FROM venta";
    private static final String VENTAS = "SELECT * FROM systec.venta ORDER BY id DESC";

    public void agregarVenta() {
        Connection coneccion = null;
        PreparedStatement preS = null;
        try {
            coneccion = ConeccionDB.getConeccion();
            preS = coneccion.prepareStatement(INSERT);
            preS.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int getUltimaVentaIngresado() {
        Connection coneccion = null;
        PreparedStatement preS = null;
        ResultSet resultSet = null;
        int ultimo = 0;
        try {
            coneccion = ConeccionDB.getConeccion();
            preS = coneccion.prepareStatement(ULTIMO);
            resultSet = preS.executeQuery();

            while (resultSet.next()) {
                ultimo = resultSet.getInt("ultimo");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ultimo;
    }

    /**
     *
     * @return
     */
    public List<Venta> getVentas() {
        Connection coneccion = null;
        PreparedStatement preS = null;
        ResultSet resultSet = null;
        Venta venta = null;
        List<Venta> ventas = new ArrayList<>();
        try {
            coneccion = ConeccionDB.getConeccion();
            preS = coneccion.prepareStatement(VENTAS);
            resultSet = preS.executeQuery();

            while (resultSet.next()) {
                venta = new Venta(resultSet.getInt("id"));
                ventas.add(venta);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ventas;
    }
}
