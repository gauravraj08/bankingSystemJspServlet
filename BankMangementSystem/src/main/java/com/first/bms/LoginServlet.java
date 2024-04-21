package com.first.bms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String cardno = request.getParameter("accountNo");
        String pin = request.getParameter("pin");
        
        PreparedStatement statement = null;
        
        
        try {
            Connection c = DatabaseConnection.getConnection();
            String query = "select * from login where cardno = ? and pin = ?";
            statement = c.prepareStatement(query);
            statement.setString(1, cardno);
            statement.setString(2, pin);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                if (hasNoTransactionHistory(pin)) {
                    request.getSession().setAttribute("pin", pin);
                    response.sendRedirect("pin.jsp");
                } else {
                    request.getSession().setAttribute("pin", pin);
                    response.sendRedirect("main_class.jsp");
                }
            } else {
                out.println("<script>alert('Incorrect Card Number or PIN'); window.location='login.jsp';</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean hasNoTransactionHistory(String pin) {
    	
    	PreparedStatement statement = null;
        try {
        	Connection c = DatabaseConnection.getConnection();
        	String query =  "SELECT COUNT(*) AS count FROM bank WHERE pin = '" + pin + "'";
        	statement = c.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                return count == 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
