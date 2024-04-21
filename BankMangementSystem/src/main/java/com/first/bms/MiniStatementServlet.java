package com.first.bms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/MiniStatementServlet")
public class MiniStatementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pin = (String) request.getSession().getAttribute("pin");
        MiniBean miniBean = new MiniBean(pin);

        // Determine action
        String action = request.getParameter("action");
        if (action != null && action.equals("pdf")) {
            // Generate PDF
            ByteArrayOutputStream baosPDF = generatePDF(miniBean);

            // Set response headers for PDF download
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"mini_statement.pdf\"");

            // Write PDF to response output stream
            response.getOutputStream().write(baosPDF.toByteArray());
        } else {
            // Set MiniBean object in request attribute for web display
            request.setAttribute("MiniBean", miniBean);
            // Forward to JSP for web display
            request.getRequestDispatcher("mini.jsp").forward(request, response);
        }
    }

    private ByteArrayOutputStream generatePDF(MiniBean miniBean) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();
            
            // Add title
            document.add(new Paragraph("Mini Statement - Bank Management System"));
            document.add(new Paragraph("\n"));

            // Add transactions
            document.add(new Paragraph("Transactions:"));
            document.add(new Paragraph(miniBean.getTransactions()));
            document.add(new Paragraph("\n"));

            // Add balance
            document.add(new Paragraph("Balance:"));
            document.add(new Paragraph(miniBean.getBalance()));

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        
        return baos;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests by forwarding to doPost
        doPost(request, response);
    }
}
