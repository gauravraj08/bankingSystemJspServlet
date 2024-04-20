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

@WebServlet("/WithdrawalServlet")
public class WithdrawalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pin = (String) request.getSession().getAttribute("pin");
        String amount = request.getParameter("amount");

        if (pin == null || amount == null || amount.isEmpty()) {
            // Handle invalid input
            response.sendRedirect("Withdrawal.jsp");
            return;
        }

        try {
            Connection conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction
            
            // Fetch current total balance
            PreparedStatement pstmtBalance = conn.prepareStatement("SELECT totalBalance FROM balance WHERE pin = ?");
            pstmtBalance.setString(1, pin);
            ResultSet rsBalance = pstmtBalance.executeQuery();
            int totalBalance = 0;
            if (rsBalance.next()) {
                totalBalance = rsBalance.getInt("totalBalance");
            }
            rsBalance.close();
            pstmtBalance.close();

            // Update total balance with the withdrawn amount
            int withdrawnAmount = Integer.parseInt(amount);
            if (totalBalance < withdrawnAmount) {
                // Insufficient balance
                conn.rollback(); // Rollback transaction
                conn.setAutoCommit(true); // Reset auto-commit mode
                conn.close();
                response.sendRedirect("withdrawal.jsp?error=InsufficientBalance");
                return;
            }
            totalBalance -= withdrawnAmount;

            // Update total balance in the database
            PreparedStatement pstmtUpdateBalance = conn.prepareStatement("UPDATE balance SET totalBalance = ? WHERE pin = ?");
            pstmtUpdateBalance.setInt(1, totalBalance);
            pstmtUpdateBalance.setString(2, pin);
            pstmtUpdateBalance.executeUpdate();
            pstmtUpdateBalance.close();

            // Insert withdrawal transaction
            Date date = new Date();
            PreparedStatement pstmtInsertTransaction = conn.prepareStatement("INSERT INTO bank (pin, date, type, amount) VALUES (?, ?, 'Withdrawal', ?)");
            pstmtInsertTransaction.setString(1, pin);
            pstmtInsertTransaction.setDate(2, new java.sql.Date(date.getTime())); // Assuming your database column is of type DATE
            pstmtInsertTransaction.setInt(3, withdrawnAmount);
            pstmtInsertTransaction.executeUpdate();
            pstmtInsertTransaction.close();

            conn.commit(); // Commit transaction
            conn.setAutoCommit(true); // Reset auto-commit mode

            conn.close();
            
            response.sendRedirect("main_class.jsp");
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error
            response.sendRedirect("Withdrawal.jsp");
        }
    }
}
