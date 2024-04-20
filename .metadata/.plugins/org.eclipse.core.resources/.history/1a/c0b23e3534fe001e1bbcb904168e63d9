<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fast Cash - Bank Management System</title>
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

        .button-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            margin-bottom: 20px;
        }

        button {
            margin: 5px;
            padding: 10px 20px;
            border: none;
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

        button.back-button {
            width: 100%;
            margin-top: 10px;
            background-color: #65BFBF;
        }

        button.back-button:hover {
            background-color: #555;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Fast Cash - Bank Management System</h2>
        <div class="button-container">
            <button onclick="withdraw(100)">Rs. 100</button>
            <button onclick="withdraw(500)">Rs. 500</button>
            <button onclick="withdraw(1000)">Rs. 1000</button>
            <button onclick="withdraw(2000)">Rs. 2000</button>
            <button onclick="withdraw(5000)">Rs. 5000</button>
            <button onclick="withdraw(10000)">Rs. 10000</button>
        </div>
        <button class="back-button" onclick="redirectToMain()">Back</button>
    </div>

    <script>
        function withdraw(amount) {
            if (confirm('Are you sure you want to withdraw Rs ' + amount + '?')) {
                window.location.href = 'FastCashServlet?amount=' + amount;
            }
        }

        function redirectToMain() {
            window.location.href = 'main_class.jsp';
        }
    </script>
</body>
</html>
