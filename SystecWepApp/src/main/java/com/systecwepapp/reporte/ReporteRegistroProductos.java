/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.reporte;

import com.systecwepapp.entidad.Compra;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public void writeExcel(List<Compra> compras, String ruta) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet spreadsheet = workbook.createSheet(" Productos registrados ");

        XSSFRow row;

        Map<String, Object[]> mapaCompras = new TreeMap<String, Object[]>();
        mapaCompras.put("1",
                new Object[]{"#", "Codigo de producto", "Fecha ", "Unidades "});
        for (Compra compra : compras) {
            mapaCompras.put(
                    String.valueOf(compra.getId() + 1),
                    new Object[]{
                        compra.getId() + "",
                        compra.getProducto(),
                        compra.getFecha(),
                        compra.getUnidades() + ""});
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
