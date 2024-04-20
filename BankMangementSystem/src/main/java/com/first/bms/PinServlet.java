package com.first.bms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PinServlet")
public class PinServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPin = request.getParameter("newPin");
        String confirmPin = request.getParameter("confirmPin");
        String pin = (String) request.getSession().getAttribute("pin");

        if (!newPin.equals(confirmPin)) {
            request.setAttribute("errorMessage", "Entered PIN does not match");
            request.getRequestDispatcher("pin.jsp").forward(request, response);
            return;
        }

        try {
            updatePin(pin, newPin);
            request.getSession().setAttribute("pin", newPin);
            response.sendRedirect("main_class.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error changing PIN");
            request.getRequestDispatcher("pin.jsp").forward(request, response);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to pin.jsp
        response.sendRedirect("pin.jsp");
    }

    private void updatePin(String oldPin, String newPin) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "UPDATE login SET pin = ? WHERE pin = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newPin);
            pstmt.setString(2, oldPin);
            pstmt.executeUpdate();

            query = "UPDATE bank SET pin = ? WHERE pin = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newPin);
            pstmt.setString(2, oldPin);
            pstmt.executeUpdate();

            query = "UPDATE signupthree SET pin = ? WHERE pin = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newPin);
            pstmt.setString(2, oldPin);
            pstmt.executeUpdate();
            
            query = "UPDATE balance SET pin = ? WHERE pin = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newPin);
            pstmt.setString(2, oldPin);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
