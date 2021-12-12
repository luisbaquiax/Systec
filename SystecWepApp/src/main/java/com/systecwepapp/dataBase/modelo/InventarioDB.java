/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.dataBase.modelo;

import com.systecwepapp.dataBase.ConeccionDB;
import com.systecwepapp.entidad.Inventario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */
public class InventarioDB {

    private static final String INSERT = "INSERT INTO inventario(codigo_producto, cantidad_existente) VALUES(?,?)";
    private static final String UPDATE = "UPDATE inventario SET cantidad_existente = ? WHERE codigo_producto = ?";
    private static final String DELETE = "DELET FROM inventario WHERE codigo_producto = ?";
    private static final String SELECT = "SELECT * FROM inventario";

    public void agregarAlInvetario(Inventario inventario) {
        Connection coneccion = null;
        PreparedStatement preS = null;
        try {
            coneccion = ConeccionDB.getConeccion();
            preS = coneccion.prepareStatement(INSERT);
            preS.setString(1, inventario.getCodigoProducto());
            preS.setInt(2, inventario.getCantidadExistente());

            preS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void actualizarInventario(Inventario inventario) {
        Connection coneccion = null;
        PreparedStatement preS = null;
        try {
            coneccion = ConeccionDB.getConeccion();
            preS = coneccion.prepareStatement(UPDATE);
            preS.setInt(1, inventario.getCantidadExistente());
            preS.setString(2, inventario.getCodigoProducto());

            preS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Delete from Inventario
     *
     * @param codigo
     */
    public void eliminarDelInventario(String codigo) {
        Connection coneccion = null;
        PreparedStatement preS = null;

        try {
            coneccion = ConeccionDB.getConeccion();
            preS = coneccion.prepareStatement(DELETE);
            preS.setString(1, codigo);

            preS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     *
     * @return
     */
    public List<Inventario> getInventarioProducts() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Inventario listadoInvetario = null;

        List<Inventario> productos = new ArrayList<>();

        try {

            conn = ConeccionDB.getConeccion();
            statement = conn.prepareStatement(SELECT);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                listadoInvetario = new Inventario(
                        resultSet.getString("codigo_producto"),
                        resultSet.getInt("cantidad_existente"));
                productos.add(listadoInvetario);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return productos;
    }
}
