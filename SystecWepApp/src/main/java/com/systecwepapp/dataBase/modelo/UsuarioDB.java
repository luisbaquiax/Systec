/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.dataBase.modelo;

import com.systecwepapp.dataBase.ConeccionDB;
import com.systecwepapp.entidad.Usuario;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class UsuarioDB {

    private static final String INSERT_USER = "INSERT INT usuario(codigo, password) VALUES(?,?)";
    private static final String SEARCH_USER = "SELECT * FROM usuario WHERE codigo = ? AND password = ?";

    public void registrarNuevoUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = ConeccionDB.getConeccion();
            statement = conn.prepareStatement(INSERT_USER);
            statement.setString(1, usuario.getCodigo());
            statement.setString(2, usuario.getPass());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario buscarUsuario(String codigo, String password) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Usuario usuario = null;

        try {
            conn = ConeccionDB.getConeccion();
            statement = conn.prepareStatement(SEARCH_USER);
            statement.setString(1, codigo);
            statement.setString(2, password);
            
            resultSet = statement.executeQuery();
            while (resultSet.next()) {                
                usuario = new Usuario(resultSet.getString("codigo"),resultSet.getString("password"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
}
