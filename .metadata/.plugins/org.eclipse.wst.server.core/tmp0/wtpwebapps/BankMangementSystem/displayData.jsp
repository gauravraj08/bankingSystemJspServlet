<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="com.first.bms.Customer" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer List</title>
    <style>
        body {
            background-color: purple;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
            color: black; /* Text color */
        }
        .container button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .container button:hover {
            background-color: #45a049;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Customer List</h1>
        <ul>
            <% 
                List<Customer> customerList = (List<Customer>) request.getAttribute("customerList");
                if (customerList != null && !customerList.isEmpty()) {
                    for (Customer customer : customerList) {
            %>
                <li>
                    Name: <%= customer.getName() %>, 
                    DOB: <%= customer.getDOB() %>, 
                    Email: <%= customer.getEmail() %>
                </li>
            <%
                    }
                } else {
            %>
                <p>No customers found.</p>
            <%
                }
            %>
        </ul>
        <button onclick="window.location.href = 'adminMenu.jsp';">Go to Admin Menu</button>
    </div>
</body>
</html>
