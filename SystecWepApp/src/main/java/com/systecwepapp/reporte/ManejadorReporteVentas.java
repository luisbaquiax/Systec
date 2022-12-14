/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.reporte;

import com.systecwepapp.dataBase.modelo.FacturaDB;
import com.systecwepapp.dataBase.modelo.VentaDB;
import com.systecwepapp.entidad.Factura;
import com.systecwepapp.entidad.Venta;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

    /**
     *
     * @param ventas
     * @param ruta
     */
    public void escribirReporteCSV(List<Venta> ventas, String ruta) {
        String contenido = "Identificador, Codigo producto, Cantidad, Precio Unitario, Subtotal\n";
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

    /**
     *
     * @param ventas
     * @param ruta
     * @throws FileNotFoundException
     */
    public void writeReportExcel(List<Venta> ventas, String ruta) throws FileNotFoundException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet spreadsheet = workbook.createSheet(" Productos registrados ");

        XSSFRow row;

        HashMap<Integer, Object> mapaCompras = new HashMap<>();
        double total = 0;
        int id = 1;
        mapaCompras.put(id,
                new Object[]{"No", "Código de producto", "Cantidad ", "Precio unitario ", "Subtotal"});

        for (Venta v : ventas) {
            List<Factura> listadoProuctos = this.facturaDB.getFacturaPorIdVenta(v.getId());
            for (Factura l : listadoProuctos) {
                id++;
                mapaCompras.put(id,
                        new Object[]{
                            v.getId() + "",
                            l.getCodigoProducto(),
                            l.getCantidadProductos() + "",
                            l.getPrecioUnitario() + "",
                            l.getTotalPago() + ""});
            }
            id++;
            mapaCompras.put(id, new Object[]{
                "",
                "",
                "",
                "Subtotal",
                totalAPagar(listadoProuctos) + ""});
            total += totalAPagar(listadoProuctos);
        }
        id++;
        mapaCompras.put(id, new Object[]{
            "",
            "",
            "",
            "TOTAL",
            total + ""});
        Set<Integer> keyid = mapaCompras.keySet();

        int rowid = 0;

        for (int key : keyid) {
            System.out.println(key);
            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = (Object[]) mapaCompras.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        FileOutputStream out = new FileOutputStream(
                new File(ruta));

        try {
            workbook.write(out);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ManejadorReporteVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double totalAPagar(List<Factura> productsFacturas) {
        double total = 0;
        for (Factura productsFactura : productsFacturas) {
            total += productsFactura.getTotalPago();
        }
        return total;
    }
}
