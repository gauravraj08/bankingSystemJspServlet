package com.first.bms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CloseAccountServlet")
public class CloseAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String pin = (String) request.getSession().getAttribute("pin");

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            String query = "SELECT totalBalance FROM balance WHERE pin = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, pin);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                int totalBalance = resultSet.getInt("totalBalance");
                if (totalBalance == 0) {
                    PreparedStatement deleteLoginStmt = conn.prepareStatement("DELETE FROM login WHERE pin = ?");
                    deleteLoginStmt.setString(1, pin);
                    deleteLoginStmt.executeUpdate();
                    
                    PreparedStatement deleteBankStmt = conn.prepareStatement("DELETE FROM bank WHERE pin = ?");
                    deleteBankStmt.setString(1, pin);
                    deleteBankStmt.executeUpdate();
                    
                    PreparedStatement deleteSignupStmt = conn.prepareStatement("DELETE FROM signupthree WHERE pin = ?");
                    deleteSignupStmt.setString(1, pin);
                    deleteSignupStmt.executeUpdate();
                    
                    PreparedStatement deleteBalanceStmt = conn.prepareStatement("DELETE FROM balance WHERE pin = ?");
                    deleteBalanceStmt.setString(1, pin);
                    deleteBalanceStmt.executeUpdate();
                    
                    response.sendRedirect("accountClosed.jsp");
                } else {
                    response.sendRedirect("cannotClose.jsp");
                }
            } else {
                // Account not found in the balance table
                response.sendRedirect("error.jsp");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error.jsp");
        } finally {
            // Close resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
