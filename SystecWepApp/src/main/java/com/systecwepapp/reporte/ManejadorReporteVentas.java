/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.reporte;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.systecwepapp.dataBase.modelo.FacturaDB;
import com.systecwepapp.dataBase.modelo.VentaDB;
import com.systecwepapp.entidad.Factura;
import com.systecwepapp.entidad.Venta;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
public class ManejadorReporteVentas {

    private VentaDB ventaDB;
    private FacturaDB facturaDB;
    private ManejadorArchivo manejadorArchivo;

    
    public ManejadorReporteVentas() {
        this.ventaDB = new VentaDB();
        this.facturaDB = new FacturaDB();
        this.manejadorArchivo = new ManejadorArchivo();
    }

    public void escribirReporteCSV(List<Venta> ventas, String ruta) {
        String contenido = "Identificador, Código producto, Cantidad, Precio Unitario, Subtotal\n";
        double total = 0;
        for (Venta venta : ventas) {
            contenido += venta.getId();
            List<Factura> listadoProuctos = this.facturaDB.getFacturaPorIdVenta(venta.getId());
            for (Factura listadoProucto : listadoProuctos) {
                contenido += "," + listadoProucto.getCodigoProducto()
                        + "," + listadoProucto.getCantidadProductos()
                        + "," + listadoProucto.getPrecioUnitario()
                        + "," + listadoProucto.getTotalPago() + "\n";

            }
            contenido += ",,,SUBTOTAL," + totalAPagar(listadoProuctos) + "\n";
            total += totalAPagar(listadoProuctos);
        }
        contenido += ",,,TOTAL," + total + "\n";
        this.manejadorArchivo.escribirArchivodeTexto(ruta, contenido);

    }

    public double totalAPagar(List<Factura> productsFacturas) {
        double total = 0;
        for (Factura productsFactura : productsFacturas) {
            total += productsFactura.getTotalPago();
        }
        return total;
    }
}
