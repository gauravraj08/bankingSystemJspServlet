<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Account Details</title>
    <link rel="stylesheet" type="text/css" href="css/signup3.css">
</head>
<body>
    <h1>Account Details</h1>
    <form action="Signup3Servlet" method="post">
        <label for="accountType">Account Type:</label>
        <br>
        <input type="radio" id="savingAccount" name="accountType" value="Saving Account">
        <label for="savingAccount">Saving Account</label>
        <br>
        <input type="radio" id="fixedDepositAccount" name="accountType" value="Fixed Deposit Account">
        <label for="fixedDepositAccount">Fixed Deposit Account</label>
        <br>
        <input type="radio" id="currentAccount" name="accountType" value="Current Account">
        <label for="currentAccount">Current Account</label>
        <br>
        <input type="radio" id="recurringDepositAccount" name="accountType" value="Recurring Deposit Account">
        <label for="recurringDepositAccount">Recurring Deposit Account</label>
        <br><br>
        
        <label for="cardNumber">Card Number : <span class='span'>(It would appear on atm card/cheque Book and Statements)</span></label>
        <br><br>
        <label>1409-96XX-XXXX-XXXX</label>
        <br><br>

        <label for="pin">PIN : <span class='span'>(4-digit Password)</span></label>
        <br><br>
        <label>XXXX</label>
        <br><br>

        <label for="servicesRequired">Services Required:</label>
        <br>
        <input type="checkbox" id="atmCard" name="atmCard" value="ATM CARD">
        <label for="atmCard">ATM CARD</label>
        <br>
        <input type="checkbox" id="internetBanking" name="internetBanking" value="Internet Banking">
        <label for="internetBanking">Internet Banking</label>
        <br>
        <input type="checkbox" id="mobileBanking" name="mobileBanking" value="Mobile Banking">
        <label for="mobileBanking">Mobile Banking</label>
        <br>
        <input type="checkbox" id="emailAlerts" name="emailAlerts" value="EMAIL Alerts">
        <label for="emailAlerts">EMAIL Alerts</label>
        <br>
        <input type="checkbox" id="chequeBook" name="chequeBook" value="Cheque Book">
        <label for="chequeBook">Cheque Book</label>
        <br>
        <input type="checkbox" id="eStatement" name="eStatement" value="E-Statement">
        <label for="eStatement">E-Statement</label>
        <br><br>
        <input type="checkbox" id="declaration" name="declaration" checked>
<label for="declaration">I hereby declare that the above entered details are correct to the best of my knowledge.</label>
<br><br>
        <input type="submit" value="Submit">
        <input type="reset" value="Cancel">
    </form>
    
</body>
</html>
