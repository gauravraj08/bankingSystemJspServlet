package com.first.bms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BalanceEnquiryServlet")
public class BalanceEnquiryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pin = (String) request.getSession().getAttribute("pin");

        if (pin == null) {
            // Redirect to login page if PIN is not available in session
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Fetch current balance from the database
            int currentBalance = getCurrentBalance(pin);

            // Write the current balance as plain text in the response
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(String.valueOf(currentBalance));
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private int getCurrentBalance(String pin) throws SQLException {
        int balance = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement("SELECT totalBalance FROM balance WHERE pin = ?");
            pstmt.setString(1, pin);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                balance = rs.getInt("totalBalance");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return balance;
    }
}
