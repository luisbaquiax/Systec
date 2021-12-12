/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.servleteControlador;

import com.systecwepapp.dataBase.modelo.InventarioDB;
import com.systecwepapp.dataBase.modelo.ProductoDB;
import com.systecwepapp.entidad.Inventario;
import com.systecwepapp.entidad.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@WebServlet(name = "ControlProductos", urlPatterns = {"/ControlProductos"})
public class ControlProductos extends HttpServlet {

    private ProductoDB productoDB;
    private InventarioDB inventarioDB;

    public ControlProductos() {
        this.productoDB = new ProductoDB();
        this.inventarioDB = new InventarioDB();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tarea = request.getParameter("tarea");
        switch (tarea) {
            case "productos":
                redirigirAProductos(request, response);
                break;
            default:
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tarea = request.getParameter("tarea");
        switch (tarea) {
            case "nuevoProducto":
                crearNuevoProducto(request, response);
                break;
            default:
        }
    }

    private void redirigirAProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Producto> listaProducts = this.productoDB.getProductosTodaInformacion();
        request.getSession().setAttribute("productos", listaProducts);
        response.sendRedirect(request.getContextPath() + "/JSP/productos.jsp");
    }

    private void crearNuevoProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombreProducto");
        double precio = Double.parseDouble(request.getParameter("precioUnitario"));
        int cantidadExistente = Integer.parseInt(request.getParameter("cantidadExistente"));

        this.productoDB.agregarProducto(new Producto(codigo, nombre, "", precio));
        this.inventarioDB.agregarAlInvetario(new Inventario(codigo, cantidadExistente));

        List<Producto> listaProducts = this.productoDB.getProductosTodaInformacion();
        request.getSession().setAttribute("productos", listaProducts);
        response.sendRedirect(request.getContextPath() + "/JSP/productos.jsp");
    }
}
