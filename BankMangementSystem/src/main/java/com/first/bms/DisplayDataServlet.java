package com.first.bms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayDataServlet")
public class DisplayDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(DisplayDataServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to the database
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                LOGGER.log(Level.SEVERE, "Failed to establish a connection to the database.");
                return;
            }

            // Retrieve data from the database
            String query = "SELECT * FROM signup";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            List<Customer> customerList = new ArrayList<>();
            while (rs.next()) {
                // Construct customer object
                Customer customer = new Customer();
                customer.setName(rs.getString("name"));
                customer.setDOB(rs.getString("DOB"));
                customer.setEmail(rs.getString("email"));
                customerList.add(customer);
            }

            if (customerList.isEmpty()) {
                LOGGER.log(Level.WARNING, "No customers found in the database.");
            } else {
                request.setAttribute("customerList", customerList);
                request.getRequestDispatcher("displayData.jsp").forward(request, response);
            }
        } catch (Exception e) {
            
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("An error occurred while processing your request.");
        } finally {
            // Close connections
            try { if (rs != null) rs.close(); } catch (Exception e) { /* ignored */ }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { /* ignored */ }
            try { if (conn != null) conn.close(); } catch (Exception e) { /* ignored */ }
        }
    }
}
