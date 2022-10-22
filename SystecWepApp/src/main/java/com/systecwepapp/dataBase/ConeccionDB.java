/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author luis
 */
public class ConeccionDB {

    private static final String USER = "systec";
    private static final String PASSWORD = "adminSystec124@";
    private static final String URL = "jdbc:mysql://localhost:3306/systec?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    private static ConeccionDB conexionSingleton = null;

    private static Connection CONECCION = null;

    private ConeccionDB() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            CONECCION = DriverManager.getConnection(URL, USER, PASSWORD);
            //conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("conexion exitosa");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConeccion() throws SQLException {
        if (conexionSingleton == null) {
            conexionSingleton = new ConeccionDB();
        }
        return CONECCION;
    }

}
