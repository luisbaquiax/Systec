/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.dataBase.modelo;

import com.systecwepapp.dataBase.ConeccionDB;
import com.systecwepapp.entidad.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class ProductoDB {

    private static final String INSERT = "INSERT INTO producto(codigo, nombre, tipo, precio_unitario) VALUES(?,?,?,?)";
    private static final String UPDATE = "UPDATE producto SET nombre = ?, tipo = ?, precio_unitario = ? WHERE codigo = ?";
    private static final String DELETE = "DELET FROM producto WHERE codigo = ?";

    private static final String TODOS_PRODUCTOS = "SELECT producto.codigo, producto.nombre, producto.tipo, producto.precio_unitario, inventario.cantidad_existente\n"
            + "FROM producto\n"
            + "RIGHT JOIN inventario\n"
            + "ON producto.codigo = inventario.codigo_producto";
    private static final String ONLY_PRODUCT_EXISTENTE = "SELECT producto.codigo, producto.nombre, producto.tipo, producto.precio_unitario, inventario.cantidad_existente\n"
            + "FROM producto\n"
            + "RIGHT JOIN inventario\n"
            + "ON producto.codigo = inventario.codigo_producto WHERE producto.codigo = ? AND inventario.cantidad_existente > 0";
    private static final String PRODUCTO = "SELECT producto.codigo, producto.nombre, producto.tipo, producto.precio_unitario, inventario.cantidad_existente\n"
            + "FROM producto\n"
            + "RIGHT JOIN inventario\n"
            + "ON producto.codigo = inventario.codigo_producto WHERE producto.codigo = ?";

    public void agregarProducto(Producto producto) {
        Connection coneccion = null;
        PreparedStatement preS = null;
        try {
            coneccion = ConeccionDB.getConeccion();
            preS = coneccion.prepareStatement(INSERT);
            preS.setString(1, producto.getCodigo());
            preS.setString(2, producto.getNombre());
            preS.setString(3, producto.getTipo());
            preS.setDouble(4, producto.getPrecioUnitario());

            preS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void actualizarProducto(Producto producto) {
        Connection coneccion = null;
        PreparedStatement preS = null;
        try {
            coneccion = ConeccionDB.getConeccion();
            preS = coneccion.prepareStatement(UPDATE);
            preS.setString(1, producto.getNombre());
            preS.setString(2, producto.getTipo());
            preS.setDouble(3, producto.getPrecioUnitario());
            preS.setString(4, producto.getCodigo());

            preS.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Delete a Producto
     *
     * @param codigo
     */
    public void eliminarProducto(String codigo) {
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
     * query: <br><br>SELECT producto.codigo, producto.nombre, producto.tipo,
     * producto.precio_unitario, inventario.cantidad_existente FROM producto
     * RIGHT JOIN inventario ON producto.codigo = inventario.codigo_producto;
     *
     * @return
     */
    public List<Producto> getProductosTodaInformacion() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Producto producto = null;

        List<Producto> productos = new ArrayList<>();

        try {

            conn = ConeccionDB.getConeccion();
            statement = conn.prepareStatement(TODOS_PRODUCTOS);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                producto = new Producto(
                        resultSet.getString("codigo"),
                        resultSet.getString("nombre"),
                        resultSet.getString("tipo"),
                        resultSet.getDouble("precio_unitario"),
                        resultSet.getInt("cantidad_existente")
                );
                productos.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }

    /**
     *
     * @param codigoProducto
     * @return
     */
    public Producto getProductoTodaInformacion(String codigoProducto) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Producto producto = null;

        try {

            conn = ConeccionDB.getConeccion();
            statement = conn.prepareStatement(ONLY_PRODUCT_EXISTENTE);
            statement.setString(1, codigoProducto);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                producto = new Producto(
                        resultSet.getString("codigo"),
                        resultSet.getString("nombre"),
                        resultSet.getString("tipo"),
                        resultSet.getDouble("precio_unitario"),
                        resultSet.getInt("cantidad_existente")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }

    /**
     *
     * @param codigoProducto
     * @return
     */
    public Producto getProducto(String codigoProducto) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Producto producto = null;

        try {

            conn = ConeccionDB.getConeccion();
            statement = conn.prepareStatement(PRODUCTO);
            statement.setString(1, codigoProducto);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                producto = new Producto(
                        resultSet.getString("codigo"),
                        resultSet.getString("nombre"),
                        resultSet.getString("tipo"),
                        resultSet.getDouble("precio_unitario"),
                        resultSet.getInt("cantidad_existente")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }

}
