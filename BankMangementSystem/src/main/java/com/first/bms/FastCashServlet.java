package com.first.bms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FastCashServlet")
public class FastCashServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pin = (String) request.getSession().getAttribute("pin");
        String amountParam = request.getParameter("amount");

        if (pin == null || amountParam == null || amountParam.isEmpty()) {
            // Handle invalid input
            response.sendRedirect("fastcash.jsp");
            return;
        }

        try {
            int amount = Integer.parseInt(amountParam);
            Connection conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // Fetch current total balance
            int totalBalance = getCurrentBalance(pin, conn);

            // Check if sufficient balance is available
            if (totalBalance < amount) {
                conn.rollback(); // Rollback transaction
                conn.setAutoCommit(true); // Reset auto-commit mode
                conn.close();
                response.sendRedirect("fastcash.jsp");
                return;
            }

            // Update total balance after withdrawal
            int updatedBalance = totalBalance - amount;
            updateBalance(pin, updatedBalance, conn);

            // Insert withdrawal transaction
            Date date = new Date();
            insertTransaction(pin, date, "Withdrawal", amount, conn);

            conn.commit(); // Commit transaction
            conn.setAutoCommit(true); // Reset auto-commit mode
            conn.close();

            // Redirect to appropriate page
            response.sendRedirect("main_class.jsp");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            // Handle database error
            response.sendRedirect("fastcash.jsp");
        }
    }

    private int getCurrentBalance(String pin, Connection conn) throws SQLException {
        int totalBalance = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement("SELECT totalBalance FROM balance WHERE pin = ?");
            pstmt.setString(1, pin);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalBalance = rs.getInt("totalBalance");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }

        return totalBalance;
    }

    private void updateBalance(String pin, int totalBalance, Connection conn) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("UPDATE balance SET totalBalance = ? WHERE pin = ?");
            pstmt.setInt(1, totalBalance);
            pstmt.setString(2, pin);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    private void insertTransaction(String pin, Date date, String transactionType, int amount, Connection conn) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("INSERT INTO bank (pin, date, type, amount) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, pin);
            pstmt.setDate(2, new java.sql.Date(date.getTime()));
            pstmt.setString(3, transactionType);
            pstmt.setInt(4, amount);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
}
