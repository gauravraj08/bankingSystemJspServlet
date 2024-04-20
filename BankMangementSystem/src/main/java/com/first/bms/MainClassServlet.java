package com.first.bms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/MainClassServlet")
public class MainClassServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "DEPOSIT":
                response.sendRedirect("deposit.jsp");
                break;
            case "WITHDRAWL":
                response.sendRedirect("withdrawal.jsp");
                break;
            case "FASTCASH":
                response.sendRedirect("fastCash.jsp");
                break;
            case "MINISTATEMENT":
                response.sendRedirect("mini.jsp");
                break;
            case "PINCHANGE":
                response.sendRedirect("pin.jsp");
                break;
            case "BALANCEENQUIRY":
                response.sendRedirect("balanceEnquiry.jsp");
                break;
            case "EXIT":
            	request.getSession().invalidate();
            	response.sendRedirect("index.jsp");
                break;
            case "CLOSEACCOUNT":
                response.sendRedirect("closeAccount.jsp");
                break;
            default:
                response.sendRedirect("main_Class.jsp");
                break;
        }
    }

}
