package com.first.bms;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/menuServlet")
public class AdminMenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("addAccount")) {
            response.sendRedirect("signup.jsp");
        } else if (action.equals("displayList")) {
            response.sendRedirect("displayDataServlet");
        } else if (action.equals("exit")) {
            response.sendRedirect("index.jsp");
        }
    }
}
