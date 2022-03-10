/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.dataBase.modelo;

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
    private static final String VENTAS = "SELECT * FROM venta ORDER BY id DESC";
    private static final String VENTAS_ENTRE_FECHAS = ""
            + "SELECT venta.id \n"
            + "FROM venta\n"
            + "RIGHT JOIN factura\n"
            + "ON factura.id_venta = venta.id\n"
            + "WHERE factura.fecha BETWEEN  ? AND ?";

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

    /**
     *
     * @return ID de la última venta ingresado
     */
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
     * SELECT * FROM venta ORDER BY id DESC
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

    /**
     *
     * query:
     * <br>SELECT venta.id FROM venta
     * <br>RIGHT JOIN factura
     * <br>ON factura.id_venta = venta.id
     * <br>WHERE factura.fecha BETWEEN ? AND ?
     *
     * @param fecha1
     * @param fecha2
     * @return
     */
    public List<Venta> getVentasPorFecha(String fecha1, String fecha2) throws SQLException {
        Connection coneccion = null;
        PreparedStatement preS = null;
        ResultSet resultSet = null;
        Venta venta = null;
        List<Venta> ventas = new ArrayList<>();
        coneccion = ConeccionDB.getConeccion();
        preS = coneccion.prepareStatement(VENTAS_ENTRE_FECHAS);
        preS.setString(1, fecha1);
        preS.setString(2, fecha2);
        resultSet = preS.executeQuery();

        while (resultSet.next()) {
            venta = new Venta(resultSet.getInt("id"));
            if (!exits(ventas, venta)) {
                ventas.add(venta);
            }
        }

        return ventas;
    }

    private boolean exits(List<Venta> ventas, Venta venta2) {
        for (Venta venta : ventas) {
            if (venta.getId() == venta2.getId()) {
                return true;
            }
        }
        return false;
    }
}
