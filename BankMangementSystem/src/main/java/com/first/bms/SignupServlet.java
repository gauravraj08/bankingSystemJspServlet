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

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formno = request.getParameter("formno");
        String name = request.getParameter("name");
        String fname = request.getParameter("fname");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String marital = request.getParameter("marital");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String pincode = request.getParameter("pincode");
        String state = request.getParameter("state");

        try {
            
             Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO signup (form_no, name, father_name, DOB, gender, email, marital_status, address, city, pincode, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
             pstmt.setString(1, formno);
             pstmt.setString(2, name);
             pstmt.setString(3, fname);
             pstmt.setString(4, dob);
             pstmt.setString(5, gender);
             pstmt.setString(6, email);
             pstmt.setString(7, marital);
             pstmt.setString(8, address);
             pstmt.setString(9, city);
             pstmt.setString(10, pincode);
             pstmt.setString(11, state);
            
             pstmt.executeUpdate();
            
            // Redirect to success page after successful form submission
            response.sendRedirect("signup2.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            // Redirect to error page in case of an exception
            response.sendRedirect("error.jsp");
        }
    }
}
