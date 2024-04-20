<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Withdrawal - Bank Management System</title>
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

        label {
            display: block;
            margin-bottom: 10px;
            color: #333;
            font-weight: bold;
        }

        input[type="text"] {
            width: 90%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid black;
            border-radius: 5px;
            background-color: #fff;
            color: black;
            font-size: 16px;
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
        <h2>Withdrawal - Bank Management System</h2>
        <form action="WithdrawalServlet" method="post">
            <label for="amount">Enter Amount:</label>
            <input type="text" id="amount" name="amount" required>
            <button type="submit">Withdraw</button>
        </form>
        <form action="MainClassServlet" method="get">
            <button type="submit">Back</button>
        </form>
    </div>
</body>
</html>
