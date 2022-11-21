/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.reporte;

import com.systecwepapp.entidad.Producto;
import java.util.List;

/**
 *
 * @author Luis
 */
public class ReporteProductos {

    private ManejadorArchivo manejadorArchivo;

    public ReporteProductos() {
        this.manejadorArchivo = new ManejadorArchivo();
    }

    public void escribirReporteProductosCSV(List<Producto> productos, String ruta) {
        String csv = "CODIGO, NOMBRE, CANTIDAD, PRECIO UNITARIO\n";
        for (Producto producto : productos) {
            csv += producto.getCodigo() + ", " + producto.getNombre() + ", " + producto.getCantidadExistente() + "," + producto.getPrecioUnitario() + "\n";
        }
        this.manejadorArchivo.escribirArchivodeTexto(ruta, csv);
    }
}
