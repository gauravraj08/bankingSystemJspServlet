<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Balance Enquiry - Bank Management System</title>
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

        .balance-container {
            text-align: center;
            margin-bottom: 20px;
        }

        .balance {
            font-size: 20px;
            font-weight: bold;
            color: #333;
        }

        button {
            width: 98%;
            padding: 10px;
            border: none;
            margin:5px;
            border-radius: 5px;
            background-color: #65BFBF;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #4D9F9F;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Balance Enquiry - Bank Management System</h2>
        <div class="balance-container">
            <p>Your Current Balance is:</p>
            <p id="currentBalance" class="balance">Loading...</p>
        </div>
        <button onclick="window.location.href='main_class.jsp'">Back</button>
    </div>
    <script>
        // Function to fetch current balance using AJAX
        function getCurrentBalance() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'BalanceEnquiryServlet', true);
            xhr.onload = function () {
                if (xhr.status >= 200 && xhr.status < 300) {
                    var currentBalance = xhr.responseText;
                    document.getElementById('currentBalance').textContent = 'Rs ' + currentBalance;
                } else {
                    console.error('Failed to fetch current balance');
                }
            };
            xhr.send();
        }

        // Function to redirect to main_class.jsp
        function redirectToMain() {
            window.location.href = 'main_class.jsp';
        }

        // Call the function to fetch current balance when the page loads
        window.onload = function() {
            getCurrentBalance();
        };
    </script>
</body>
</html>
