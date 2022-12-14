/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systecwepapp.servleteControlador;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 *
 * @author luis
 */
@WebServlet(name = "DescargaVentasExcel", urlPatterns = {"/DescargaVentasExcel"})
public class DescargaVentasExcel extends HttpServlet {

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
        String path = request.getParameter("ruta");
        try (BufferedInputStream fileStream = new BufferedInputStream(new FileInputStream(path))) {
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=reporte.xlsx");
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        }
    }

}
