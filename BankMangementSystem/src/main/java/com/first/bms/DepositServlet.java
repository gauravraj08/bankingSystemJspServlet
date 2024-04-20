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

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pin = (String) request.getSession().getAttribute("pin");
        String amount = request.getParameter("amount");
        

        if (pin == null || amount == null || amount.isEmpty()) {
            // Handle invalid input
            response.sendRedirect("deposit.jsp");
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

            // Update total balance with the deposited amount
            int depositedAmount = Integer.parseInt(amount);
            totalBalance += depositedAmount;

            // Update total balance in the database
            PreparedStatement pstmtUpdateBalance = conn.prepareStatement("UPDATE balance SET totalBalance = ? WHERE pin = ?");
            pstmtUpdateBalance.setInt(1, totalBalance);
            pstmtUpdateBalance.setString(2, pin);
            pstmtUpdateBalance.executeUpdate();
            pstmtUpdateBalance.close();

            // Insert deposit transaction
            Date date = new Date();
            PreparedStatement pstmtInsertTransaction = conn.prepareStatement("INSERT INTO bank (pin, date, type, amount) VALUES (?, ?, 'Deposit', ?)");
            pstmtInsertTransaction.setString(1, pin);
            pstmtInsertTransaction.setDate(2, new java.sql.Date(date.getTime())); // Assuming your database column is of type DATE
            pstmtInsertTransaction.setInt(3, depositedAmount);
            pstmtInsertTransaction.executeUpdate();
            pstmtInsertTransaction.close();

            conn.commit(); // Commit transaction
            conn.setAutoCommit(true); // Reset auto-commit mode

            conn.close();

            // Redirect to appropriate page based on transaction history
            if (hasNoTransactionHistory(pin)) {
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("main_class.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error
            response.sendRedirect("deposit.jsp");
        }
    }

    private boolean hasNoTransactionHistory(String pin) {
        try {
            Connection c = DatabaseConnection.getConnection();
           
            PreparedStatement pstmt = c.prepareStatement("SELECT COUNT(*) AS count FROM bank WHERE pin = ?");
            pstmt.setString(1, pin);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                return count == 0;
            }
            rs.close();
            pstmt.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
