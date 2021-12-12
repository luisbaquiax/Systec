/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.servleteControlador;

import com.systecwepapp.dataBase.modelo.FacturaDB;
import com.systecwepapp.dataBase.modelo.InventarioDB;
import com.systecwepapp.dataBase.modelo.ProductoDB;
import com.systecwepapp.dataBase.modelo.VentaDB;
import com.systecwepapp.entidad.Factura;
import com.systecwepapp.entidad.Inventario;
import com.systecwepapp.entidad.Producto;
import com.systecwepapp.entidad.Venta;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
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
@WebServlet(name = "ControlVentas", urlPatterns = {"/ControlVentas"})
public class ControlVentas extends HttpServlet {

    private ProductoDB productoDB;
    private VentaDB ventaDB;
    private FacturaDB facturaDB;
    private InventarioDB inventarioDB;

    public ControlVentas() {
        this.productoDB = new ProductoDB();
        this.ventaDB = new VentaDB();
        this.facturaDB = new FacturaDB();
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
            case "nuevaVenta":
                redirigirHacerNuevaVenta(request, response);
                break;
            case "realizarVenta":
                realizarVenta(request, response);
                break;
            case "ventas":
                verVentas(request, response);
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
            case "agregarProductoFactura":
                agregarProductoAlaFactura(request, response);
                break;
            default:
        }
    }

    private void redirigirHacerNuevaVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Producto> products = this.productoDB.getProductosTodaInformacion();
        List<Factura> productsFacturas = new ArrayList<>();

        request.getSession().setAttribute("productosFactura", productsFacturas);
        request.getSession().setAttribute("total", totalAPagar(productsFacturas));
        request.getSession().setAttribute("productos", products);

        response.sendRedirect(request.getContextPath() + "/JSP/venta.jsp");
    }

    private void agregarProductoAlaFactura(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Factura> productsFacturas = (List<Factura>) request.getSession().getAttribute("productosFactura");
        String codigoProducto = request.getParameter("codigoProducto");
        int cantidad = Integer.parseInt(request.getParameter("cantidadProducto"));

        Producto buscado = productoBuscado(codigoProducto, this.productoDB.getProductosTodaInformacion());

        agregarAlaFactura(buscado, cantidad, productsFacturas);

        request.getSession().setAttribute("total", totalAPagar(productsFacturas));

        response.sendRedirect(request.getContextPath() + "/JSP/venta.jsp");
    }

    private Producto productoBuscado(String codigo, List<Producto> products) {
        for (Producto product : products) {
            if (codigo.equals(product.getCodigo())) {
                return product;
            }
        }
        return null;
    }

    private void agregarAlaFactura(Producto producto, int cantidad, List<Factura> produFacturas) {
        if (!yaExisteEnFactura(producto.getCodigo(), produFacturas)) {
            produFacturas.add(new Factura(LocalDate.now().toString(), producto.getPrecioUnitario(),
                    producto.getPrecioUnitario(), cantidad, producto.getCodigo(), "user"));
        } else {
            for (Factura produFactura : produFacturas) {
                if (producto.getCodigo().equals(produFactura.getCodigoProducto())) {
                    produFactura.setCantidadProductos(produFactura.getCantidadProductos() + cantidad);
                    produFactura.setTotalPago(produFactura.getCantidadProductos() * produFactura.getPrecioUnitario());
                }
            }
        }
    }

    private boolean yaExisteEnFactura(String codigo, List<Factura> produFacturas) {
        for (Factura produFactura : produFacturas) {
            if (codigo.equals(produFactura.getCodigoProducto())) {
                return true;
            }
        }
        return false;
    }

    private double totalAPagar(List<Factura> productsFacturas) {
        double total = 0;
        for (Factura productsFactura : productsFacturas) {
            total += productsFactura.getTotalPago();
        }
        return total;
    }

    private boolean aunExisteProductosDB(Producto producto, List<Producto> productosDb) {
        for (Producto productoDB : productosDb) {
            if (productoDB.getCantidadExistente() >= producto.getCantidadExistente()) {
                return true;
            }
        }
        return false;
    }

    private void realizarVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Factura> productsFacturas = (List<Factura>) request.getSession().getAttribute("productosFactura");

        this.ventaDB.agregarVenta();
        int idVenta = this.ventaDB.getUltimaVentaIngresado();

        for (Factura productsFactura : productsFacturas) {
            productsFactura.setIdVenta(idVenta);
            this.facturaDB.agregarProductoFactura(productsFactura);
            this.inventarioDB.actualizarInventario(
                    new Inventario(
                            productsFactura.getCodigoProducto(),
                            productoBuscado(productsFactura.getCodigoProducto(), this.productoDB.getProductosTodaInformacion())
                                    .getCantidadExistente() - productsFactura.getCantidadProductos()));
        }
        verVentas(request, response);
    }

    private void verVentas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Venta> ventas = this.ventaDB.getVentas();
        request.getSession().setAttribute("ventas", ventas);
        response.sendRedirect(request.getContextPath() + "/JSP/ventas.jsp");
    }
}
