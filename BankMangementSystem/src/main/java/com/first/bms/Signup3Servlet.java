package com.first.bms;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

@WebServlet("/Signup3Servlet")
public class Signup3Servlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountType = request.getParameter("accountType");
        String cardNumber = generateCardNumber();
        String pin = generatePIN();
        String fac = "";

        if (request.getParameter("atmCard") != null) {
            fac += "ATM CARD ";
        }
        if (request.getParameter("internetBanking") != null) {
            fac += "Internet Banking ";
        }
        if (request.getParameter("mobileBanking") != null) {
            fac += "Mobile Banking ";
        }
        if (request.getParameter("emailAlerts") != null) {
            fac += "EMAIL Alerts ";
        }
        if (request.getParameter("chequeBook") != null) {
            fac += "Cheque Book ";
        }
        if (request.getParameter("eStatement") != null) {
            fac += "E-Statement ";
        }

        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
        	Connection conn = DatabaseConnection.getConnection();
            String insertQuery = "INSERT INTO signupthree (atype, cardno, pin, fac) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, accountType);
            pstmt.setString(2, cardNumber);
            pstmt.setString(3, pin);
            pstmt.setString(4, fac);

            String query2 = "INSERT INTO login ( cardno,pin) VALUES (?, ?)";
            pstmt1 = conn.prepareStatement(query2);
            pstmt1.setString(1, cardNumber);
            pstmt1.setString(2, pin);
            pstmt1.executeUpdate();
            
            String query3 = "INSERT INTO balance ( totalBalance,pin) VALUES (?, ?)";
            pstmt2 = conn.prepareStatement(query3);
            pstmt2.setInt(1, 0);
            pstmt2.setString(2, pin);
            pstmt2.executeUpdate();
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
            	request.setAttribute("cardNumber", cardNumber);
                request.setAttribute("pin", pin);
                request.getRequestDispatcher("adminMenu.jsp").forward(request, response);
            } else {
                throw new SQLException("Failed to insert account details.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String errorMessage = "An error occurred while processing your request. Please try again later.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateCardNumber() {
        Random ran = new Random();
        long first6 = 140996;
        long next10 = (ran.nextLong() % 9000000000L) + 1000000000L;
        return "" + Math.abs(first6) + Math.abs(next10);
    }

    private String generatePIN() {
        Random ran = new Random();
        long first4 = (ran.nextLong() % 9000L) + 1000L;
        return "" + Math.abs(first4);
    }
}
