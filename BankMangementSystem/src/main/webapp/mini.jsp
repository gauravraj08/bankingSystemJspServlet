<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.first.bms.MiniBean" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mini Statement - Bank Management System</title>
    <style>
        body {
            background-color: #65BFBF;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            width: 50%;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #333;
        }

        #transactions {
            width: 100%;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        #balance {
            margin-bottom: 20px;
        }

        .btn {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #65BFBF;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-bottom: 10px;
        }

        .btn:hover {
            background-color: #4D9F9F;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Mini Statement - Bank Management System</h2>
        <% 
            MiniBean miniBean = (MiniBean)request.getAttribute("MiniBean"); // Get MiniBean object from request attribute
            String transactions = miniBean != null ? miniBean.getTransactions() : "";
            String balance = miniBean != null ? miniBean.getBalance() : "";
        %>
        <div id="transactions">
            <table>
                <thead>
                    <tr>
                        <th>S.No</th>
                        <th>Date</th>
                        <th>Type</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        // Split transactions by new line and display each transaction on a new line
                        String[] transactionLines = transactions.split("\\n");
                        for (String line : transactionLines) {
                            // Split each transaction line by whitespace
                            String[] transactionParts = line.split("\\s+");
                            if (transactionParts.length >= 4) { // Ensure that the array has enough elements
                    %>
                        <tr>
                            <td><%= transactionParts[0] %></td>
                            <td><%= transactionParts[1] %></td>
                            <td><%= transactionParts[2] %></td>
                            <td><%= transactionParts[3] %></td>
                        </tr>
                    <% 
                            }
                        } 
                    %>
                </tbody>
            </table>
        </div>
        <div id="balance">
            <%= balance %>
        </div>
        <form action="MiniStatementServlet" method="post">
            <input type="hidden" name="pin" value="<%= request.getParameter("pin") %>"/>
            <button class="btn" type="submit" name="action" value="web">Check Statement</button>
            <button class="btn" type="submit" name="action" value="pdf">Download PDF</button>
        </form>
    </div>
</body>
</html>
