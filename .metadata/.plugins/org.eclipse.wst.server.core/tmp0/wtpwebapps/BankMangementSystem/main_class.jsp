<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Class - Bank Management System</title>
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
            flex-direction: column;
            align-items: center;
        }

        .button-row {
            display: flex;
            justify-content: space-between;
            width: 100%;
            margin-bottom: 10px;
            margin:10px;
        }

        .button {
            width: calc(50% - 10px);
            margin:10px;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #65BFBF;
            color: #fff;
            font-size: 18px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #4D9F9F;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Main Menu - Bank Management System</h2>
        <div class="button-container">
            <div class="button-row">
                <a href="MainClassServlet?action=DEPOSIT" class="button">Deposit</a>
                <a href="MainClassServlet?action=PINCHANGE" class="button">Change PIN</a>
            </div>
            <div class="button-row">
                <a href="MainClassServlet?action=WITHDRAWL" class="button">Withdraw</a>
                <a href="MainClassServlet?action=MINISTATEMENT" class="button">Mini Statement</a>
            </div>
            <div class="button-row">
                <a href="MainClassServlet?action=BALANCEENQUIRY" class="button">Balance Enquiry</a>
                <a href="MainClassServlet?action=CLOSEACCOUNT" class="button">Close Account</a>
            </div>
            <div class="button-row">
                <a href="MainClassServlet?action=FASTCASH" class="button">Fast Cash</a>
                <a href="MainClassServlet?action=EXIT" class="button">Exit</a>
            </div>
        </div>
    </div>
</body>
</html>
