/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.reporte;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.systecwepapp.dataBase.modelo.FacturaDB;
import com.systecwepapp.dataBase.modelo.VentaDB;
import com.systecwepapp.entidad.Factura;
import com.systecwepapp.entidad.Venta;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.RequestContext;

/**
 *
 * @author luis
 */
public class ManejadorReporteVentasPDF {

    private FacturaDB facturaDB;
    private ManejadorReporteVentas manejadorReporteVentas;

    public ManejadorReporteVentasPDF() {
        this.facturaDB = new FacturaDB();
        this.manejadorReporteVentas = new ManejadorReporteVentas();
    }

    private void writeHeader(PdfPTable pdfTable) {
        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.darkGray);
        cell.setPadding(6f);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("No. Venta", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Código producto", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Cantidad", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Precio unitario", font));
        pdfTable.addCell(cell);
        cell.setPhrase(new Phrase("Subtotal", font));
        pdfTable.addCell(cell);
    }

    private void writeData(PdfPTable pdfTable, List<Venta> ventas) {
        double total = 0;
        for (Venta venta : ventas) {
            List<Factura> listadoProuctos = this.facturaDB.getFacturaPorIdVenta(venta.getId());
            for (Factura listadoProucto : listadoProuctos) {
                pdfTable.addCell(venta.getId() + "");
                pdfTable.addCell(listadoProucto.getCodigoProducto());
                pdfTable.addCell(listadoProucto.getCantidadProductos() + "");
                pdfTable.addCell(listadoProucto.getPrecioUnitario() + "");
                pdfTable.addCell(listadoProucto.getTotalPago() + "");
            }
            pdfTable.addCell("");
            pdfTable.addCell("");
            pdfTable.addCell("");
            pdfTable.addCell("Total");
            pdfTable.addCell(manejadorReporteVentas.totalAPagar(listadoProuctos) + "");
            total += manejadorReporteVentas.totalAPagar(listadoProuctos);
        }
        pdfTable.addCell("");
        pdfTable.addCell("");
        pdfTable.addCell("");
        pdfTable.addCell("Total");
        pdfTable.addCell(total + "");
    }

    public void exportarPDF(HttpServletResponse response, List<Venta> ventas) {
        try {
            Document doc = new Document(PageSize.A4);
            PdfWriter.getInstance(doc, response.getOutputStream());
            doc.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setSize(20);
            font.setColor(Color.BLUE);

            Paragraph paragraph = new Paragraph("Ventas", font);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(paragraph);

            agregarImagen(doc);
            PdfPTable pdfPTable = new PdfPTable(5);
            pdfPTable.setWidthPercentage(100f);
            pdfPTable.setWidths(new float[]{6.0f, 6.0f, 6.0f, 6.0f, 6.0f});
            pdfPTable.setSpacingBefore(30);
            writeHeader(pdfPTable);
            writeData(pdfPTable, ventas);

            doc.add(pdfPTable);
            doc.close();
        } catch (DocumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void agregarImagen(Document doc) {

        try {
            Image imagen = Image.getInstance(getClass().getResource("/img/systec.png"));
            imagen.setAlignment(Image.ALIGN_LEFT);
            imagen.setAbsolutePosition(30, 750);
            imagen.scaleAbsoluteHeight(95f);
            imagen.scaleAbsoluteWidth(95);
            doc.add(imagen);
        } catch (BadElementException | IOException ex) {
            Logger.getLogger(ManejadorReporteVentasPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
