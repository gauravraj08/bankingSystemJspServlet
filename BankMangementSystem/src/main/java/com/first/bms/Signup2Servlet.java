package com.first.bms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Statement;



@WebServlet("/Signup2Servlet")
public class Signup2Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String formno = request.getParameter("formno");
        String religion = request.getParameter("religion");
        String category = request.getParameter("category");
        String income = request.getParameter("income");
        String education = request.getParameter("education");
        String occupation = request.getParameter("occupation");
        String pan = request.getParameter("pan");
        String aadhar = request.getParameter("aadhar");
        String seniorCitizen = request.getParameter("seniorCitizen");
        String existingCustomer = request.getParameter("existingCustomer");

        // Validate form data
        if (formno.isEmpty() || religion.isEmpty() || category.isEmpty() || income.isEmpty() || education.isEmpty() || occupation.isEmpty() || pan.isEmpty() || aadhar.isEmpty() || seniorCitizen.isEmpty() || existingCustomer.isEmpty()) {
            response.sendRedirect("signup2.jsp?error=empty");
            return;
        }
       


        // Insert data into database
        try {
        	Connection c = DatabaseConnection.getConnection();
        	Statement statement = c.createStatement();
            String query = "INSERT INTO Signuptwo VALUES('" + formno + "', '" + religion + "', '" + category + "','" + income + "','" + education + "','" + occupation + "','" + pan + "','" + aadhar + "','" + seniorCitizen + "','" + existingCustomer + "')";
            statement.executeUpdate(query);
            response.sendRedirect("signup3.jsp"); // Redirect to next page
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("signup2.jsp?error=db");
        }
    }
}
