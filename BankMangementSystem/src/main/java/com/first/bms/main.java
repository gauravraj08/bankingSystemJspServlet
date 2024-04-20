package com.first.bms;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirect")
public class main extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType = request.getParameter("userType");
        if (userType.equals("admin")) {
            response.sendRedirect("admin.jsp");
        } else if (userType.equals("customer")) {
            response.sendRedirect("login.jsp");
        } else {
            // Handle invalid user type
            response.sendRedirect("index.jsp");
        }
    }
}
