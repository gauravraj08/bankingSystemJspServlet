package com.first.bms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String admin = request.getParameter("admin");
        String password = request.getParameter("password");

        // Check if the admin password matches
        if (admin.equals("admin") && password.equals("passthis")) {
            // Redirect to the menu page
            response.sendRedirect("adminMenu.jsp");
        } else {
            response.sendRedirect("adminLogin.jsp?error=1");
        }
    }
}
