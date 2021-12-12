/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.dataBase.modelo;

import com.systecwepapp.dataBase.ConeccionDB;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class PRUEBAS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            ConeccionDB.getConeccion();
            ProductoDB productoDB = new ProductoDB();
            VentaDB ventaDB = new VentaDB();
            System.out.println(productoDB.getProductoTodaInformacion("HUAWEI-Y5-2019").toString());
            System.out.println("ultimo " +ventaDB.getUltimaVentaIngresado());
        } catch (SQLException ex) {
            Logger.getLogger(PRUEBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
