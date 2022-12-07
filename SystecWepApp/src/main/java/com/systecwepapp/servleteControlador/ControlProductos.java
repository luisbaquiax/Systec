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
import com.systecwepapp.entidad.Venta;
import com.systecwepapp.reporte.ReporteProductos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
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
        limpiar(request);
        switch (tarea) {
            case "productos":
                redirigirAProductos(request, response);
                break;
            case "editProduct":
                redirigirEditarProducto(request, response);
                break;
            case "aumentarProducto":
                aumentarPrducto(request, response);
                break;
            case "disminuirProducto":
                disminuirProducto(request, response);
                break;
            case "descargarProductosExcel":
                descargarProductosExcel(request, response);
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
        limpiar(request);
        switch (tarea) {
            case "nuevoProducto":
                crearNuevoProducto(request, response);
                break;
            case "editarProducto":
                editarProducto(request, response);
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

        Producto nuevo = this.productoDB.getProducto(codigo);
        if (nuevo == null) {
            this.productoDB.agregarProducto(new Producto(codigo, nombre, "", precio));
            this.inventarioDB.agregarAlInvetario(new Inventario(codigo, cantidadExistente));

            request.getSession().setAttribute("msjeNuevoProducto", "Producto agregado correctamente!!!");
            request.getSession().setAttribute("productos", this.productoDB.getProductosTodaInformacion());
            response.sendRedirect("JSP/productos.jsp");
        } else if (nuevo.getCodigo().equalsIgnoreCase(codigo)) {
            request.setAttribute("msje", "El codigo del producto ya está en uso.");
            request.getRequestDispatcher("JSP/productos.jsp").forward(request, response);
        }
    }

    private void redirigirEditarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String codigo = request.getParameter("codigo");
        Producto buscado = this.productoDB.getProducto(codigo);

        request.getSession().setAttribute("producto", buscado);
        response.sendRedirect(request.getContextPath() + "/JSP/modalEditProducto.jsp");
    }

    private void aumentarPrducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        Producto buscado = this.productoDB.getProducto(codigo);
        this.inventarioDB.actualizarInventario(new Inventario(buscado.getCodigo(), buscado.getCantidadExistente() + 1));
        redirigirAProductos(request, response);
    }

    private void disminuirProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        Producto buscado = this.productoDB.getProducto(codigo);
        if (buscado.getCantidadExistente() <= 0) {
            request.setAttribute("msje", "Ya no se puede disminuir más.");
            request.getRequestDispatcher("JSP/productos.jsp").forward(request, response);
        } else {
            this.inventarioDB.actualizarInventario(new Inventario(buscado.getCodigo(), buscado.getCantidadExistente() - 1));
            redirigirAProductos(request, response);

        }
    }

    private void editarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cantidadDeExistencia = Integer.parseInt(request.getParameter("cantidadExistente"));
        String codigoProducto = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precioUnitario"));
        this.productoDB.actualizarProducto(
                new Producto(
                        codigoProducto,
                        nombre,
                        "",
                        precio));
        this.inventarioDB.actualizarInventario(new Inventario(codigoProducto, cantidadDeExistencia));
        request.getSession().setAttribute("msjeProducto", "Producto editado exitosamente!!!");
        redirigirAProductos(request, response);
    }

    private void limpiar(HttpServletRequest request) throws ServletException, IOException {
        if (request.getSession().getAttribute("msjeProducto") != null) {
            request.getSession().removeAttribute("msjeProducto");
        }
        if (request.getSession().getAttribute("msjeNuevoProducto") != null) {
            request.getSession().removeAttribute("msjeNuevoProducto");
        }
        if (request.getSession().getAttribute("registroProduct") != null) {
            request.getSession().removeAttribute("registroProduct");
        }
    }

    private void descargarProductosExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ruta = "rutaProductos.csv";
        List<Producto> productos = (List<Producto>) request.getSession().getAttribute("productos");
        ReporteProductos reporteProductos = new ReporteProductos();
        reporteProductos.escribirReporteProductosCSV(productos, ruta);
        
        request.getSession().setAttribute("rutaProductos", ruta);
        response.sendRedirect(request.getContextPath() + "/DescargaListaProductosExcel?rutaProductos=" + ruta);
    }
}
