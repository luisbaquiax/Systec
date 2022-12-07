/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.reporte;

import com.systecwepapp.entidad.Compra;
import java.util.List;

/**
 *
 * @author Luis
 */
public class ReporteRegistroProductos {

    private ManejadorArchivo manejadorArchivo;

    public ReporteRegistroProductos() {
        this.manejadorArchivo = new ManejadorArchivo();
    }

    public void escribirReporeProductosRegistrados(List<Compra> compras, String ruta) {
        String csv = "CODIGO, CODIGO DE PRODUCTO, FECHA, UNIDADES\n";
        for (Compra c : compras) {
            csv += c.getId() + ", " + c.getProducto() + ", " + c.getFecha() + "," + c.getUnidades() + "\n";
        }
        this.manejadorArchivo.escribirArchivodeTexto(ruta, csv);
    }
}
