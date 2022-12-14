/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.reporte;

import com.systecwepapp.entidad.Compra;
import com.systecwepapp.entidad.Producto;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

    public void escribirExcelProductos(List<Producto> productos, String ruta) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet spreadsheet = workbook.createSheet(" Productos registrados ");

        XSSFRow row;

        Map<String, Object[]> mapaCompras = new TreeMap<String, Object[]>();
        mapaCompras.put("1",
                new Object[]{"Codigo", "Nombre", "Cantidad ", "Precio unitario "});
        int id = 2;
        for (Producto p : productos) {
            mapaCompras.put(
                    String.valueOf(id++),
                    new Object[]{
                        p.getCodigo(),
                        p.getNombre(),
                        p.getCantidadExistente() + "",
                        p.getPrecioUnitario() + ""});
        }
        Set<String> keyid = mapaCompras.keySet();

        int rowid = 0;

        for (String key : keyid) {

            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = mapaCompras.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        FileOutputStream out = new FileOutputStream(
                new File(ruta));

        workbook.write(out);
        out.close();
    }
}
