/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.dataBase.modelo;

import com.systecwepapp.dataBase.ConeccionDB;
import com.systecwepapp.entidad.Factura;
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
public class FacturaDB {

    private static final String INSERT = "INSERT INTO factura(fecha, total_pago, precio_unitario, cantidad_productos, codigo_producto, usuario, id_venta) VALUES(?,?,?,?,?,?,?)";
    private static final String SELECT = "SELECT * FROM factura";
    private static final String GET_FACTURA_BY_IDVENTA = "SELECT * FROM factura WHERE id_venta = ?";

    /**
     * Agrega un producto a la Factura de una venta
     *
     * @param factura
     */
    public void agregarProductoFactura(Factura factura) {

        Connection coneccion = null;
        PreparedStatement preS = null;
        try {
            coneccion = ConeccionDB.getConeccion();
            preS = coneccion.prepareStatement(INSERT);
            preS.setString(1, factura.getFecha());
            preS.setDouble(2, factura.getTotalPago());
            preS.setDouble(3, factura.getPrecioUnitario());
            preS.setInt(4, factura.getCantidadProductos());
            preS.setString(5, factura.getCodigoProducto());
            preS.setString(6, factura.getUsuario());
            preS.setInt(7, factura.getIdVenta());

            preS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     *
     *
     * @return Lista de productos en factura
     */
    public List<Factura> getFacturaProductos() {
        Connection conn = null;
        PreparedStatement preS = null;
        ResultSet resultSet = null;

        Factura factura = null;
        List<Factura> facturasProductos = new ArrayList<>();

        try {
            conn = ConeccionDB.getConeccion();
            preS = conn.prepareStatement(SELECT);
            resultSet = preS.executeQuery();

            while (resultSet.next()) {
                factura = getFacturaProduct(resultSet);
                facturasProductos.add(factura);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return facturasProductos;
    }

    /**
     *
     *
     * @param idVenta
     * @return Lista de productos por ID_VENTA
     */
    public List<Factura> getFacturaPorIdVenta(int idVenta) {
        Connection conn = null;
        PreparedStatement preS = null;
        ResultSet resultSet = null;

        Factura factura = null;
        List<Factura> facturasProductos = new ArrayList<>();

        try {
            conn = ConeccionDB.getConeccion();
            preS = conn.prepareStatement(GET_FACTURA_BY_IDVENTA);
            preS.setInt(1, idVenta);
            resultSet = preS.executeQuery();

            while (resultSet.next()) {
                factura = getFacturaProduct(resultSet);
                facturasProductos.add(factura);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return facturasProductos;
    }

    /**
     *
     * @param resultSet
     * @return Retorna un objeto de tipo Factura
     * @throws SQLException
     */
    private Factura getFacturaProduct(ResultSet resultSet) throws SQLException {
        return new Factura(
                resultSet.getInt("id"),
                resultSet.getInt("id_venta"),
                resultSet.getString("fecha"),
                resultSet.getDouble("total_pago"),
                resultSet.getDouble("precio_unitario"),
                resultSet.getInt("cantidad_productos"),
                resultSet.getString("codigo_producto"),
                resultSet.getString("usuario"));
    }
}
