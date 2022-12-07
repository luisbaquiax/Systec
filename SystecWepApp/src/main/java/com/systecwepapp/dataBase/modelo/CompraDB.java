/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.dataBase.modelo;

import com.systecwepapp.dataBase.ConeccionDB;
import com.systecwepapp.entidad.Compra;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis
 */
public class CompraDB {

    private static final String INSERT = "INSERT INTO compra(producto,fecha,unidades) VALUES(?,?,?)";
    private static final String UPDATE = "UPDATE compra SET unidades=? WHERE producto=?";
    private static final String SELECT = "SELECT * FROM compra";
    private Connection conn = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    /**
     *
     * @param compra
     * @throws SQLException
     */
    public void insert(Compra compra) throws SQLException {
        conn = ConeccionDB.getConeccion();
        statement = conn.prepareStatement(INSERT);
        statement.setString(1, compra.getProducto());
        statement.setString(2, compra.getFecha());
        statement.setInt(3, compra.getUnidades());
        statement.executeUpdate();
    }

    /**
     *
     * @param compra
     * @throws SQLException
     */
    public void update(Compra compra) throws SQLException {
        conn = ConeccionDB.getConeccion();
        statement = conn.prepareStatement(UPDATE);
        statement.setInt(1, compra.getUnidades());
        statement.setString(2, compra.getProducto());
        statement.executeUpdate();
    }

    /**
     *
     * @return @throws SQLException
     */
    public List<Compra> getCompras() throws SQLException {
        Compra compra = null;
        List<Compra> compras = new ArrayList<>();
        conn = ConeccionDB.getConeccion();
        statement = conn.prepareStatement(SELECT);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            compra = new Compra(
                    resultSet.getInt("id"),
                    resultSet.getString("producto"),
                    resultSet.getString("fecha"),
                    resultSet.getInt("unidades"));
            compras.add(compra);
        }
        return compras;
    }
}
