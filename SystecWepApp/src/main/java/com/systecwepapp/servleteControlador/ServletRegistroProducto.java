/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.servleteControlador;

import com.systecwepapp.dataBase.modelo.CompraDB;
import com.systecwepapp.dataBase.modelo.InventarioDB;
import com.systecwepapp.dataBase.modelo.ProductoDB;
import com.systecwepapp.entidad.Compra;
import com.systecwepapp.entidad.Inventario;
import com.systecwepapp.entidad.Producto;
import com.systecwepapp.reporte.ReporteRegistroProductos;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis
 */
@WebServlet(name = "ServletRegistroProducto", urlPatterns = {"/ServletRegistroProducto"})
public class ServletRegistroProducto extends HttpServlet {

    private ProductoDB productoDB;
    private CompraDB compraDB;
    private InventarioDB inventarioDB;
    private ControlProductos controlProductos;
    private List<Compra> compras;
    private ReporteRegistroProductos registroProductos;

    public ServletRegistroProducto() {
        this.productoDB = new ProductoDB();
        this.compraDB = new CompraDB();
        this.inventarioDB = new InventarioDB();
        this.controlProductos = new ControlProductos();
        this.compras = new ArrayList<>();
        this.registroProductos = new ReporteRegistroProductos();
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
            case "registrarProducto":
                registrarProducto(request, response);
                break;
            case "descargarRegistros":
                descargarRegistros(request, response);
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
            case "registro":
                registrar(request, response);
                break;
            case "consulta":
                consultarRegistroProductos(request, response);
                break;
            default:
        }
    }

    private void registrarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getSession().setAttribute("productos", this.productoDB.getProductosTodaInformacion());
            this.compras = this.compraDB.getCompras();
            request.getSession().setAttribute("registros", compras);
            response.sendRedirect(request.getContextPath() + "/JSP/compra.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(ServletRegistroProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String codigoProducto = request.getParameter("codigoProducto");
            int unidades = Integer.parseInt(request.getParameter("unidades"));

            request.getSession().setAttribute("registroProduct", "Producto registrado exitosamente.");

            this.compraDB.insert(new Compra(codigoProducto, LocalDate.now().toString(), unidades));

            Producto buscado = this.productoDB.getProducto(codigoProducto);
            buscado.setCantidadExistente(buscado.getCantidadExistente() + unidades);
            this.inventarioDB.actualizarInventario(new Inventario(codigoProducto, buscado.getCantidadExistente()));
        } catch (SQLException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
            request.getSession().setAttribute("registroProduct", "No se pudo hacer el registro.");
        }
        registrarProducto(request, response);
    }

    private void consultarRegistroProductos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String fech1 = request.getParameter("fecha1");
            String fech2 = request.getParameter("fecha2");
            this.compras = this.compraDB.getCompras(fech1, fech2);
            request.getSession().setAttribute("registros", compras);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/JSP/compra.jsp");
    }

    private void descargarRegistros(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ruta = "registrados.xlsm";
        System.out.println("hola registrados");
        compras = (List<Compra>) request.getSession().getAttribute("registros");
        this.registroProductos.escribirReporeProductosRegistrados(compras, ruta);
        response.sendRedirect(request.getContextPath() + "/DowloadRegistroProductos?rutaRegistros=" + ruta);
    }

}
