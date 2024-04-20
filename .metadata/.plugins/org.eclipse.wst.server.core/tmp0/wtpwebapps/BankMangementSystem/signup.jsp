
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Account Application Form</title>
<link rel="stylesheet" type="text/css" href="css/signup.css">

</head>
<body>
<div>
    <h1>NEW ACCOUNT APPLICATION FORM</h1>
    <form action="SignupServlet" method="post">
    <div class="input-group">
        <label>APPLICATION FORM NO.</label>
        <input type="text" name="formno" required><br><br>
    </div>
    <div class="input-group">
        <label>Name:</label>
        <input type="text" name="name" required><br><br>
    </div>
    <div class="input-group">
        <label>Father's Name:</label>
        <input type="text" name="fname" required><br><br>
    </div>
    <div class="input-group">
        <label>Date of Birth:</label>
        <input type="date" name="dob" required><br><br>
    </div>
    <div class="input-group">
        <label>Gender:</label>
        <input type="radio" name="gender" value="Male"> Male
        <input type="radio" name="gender" value="Female"> Female<br><br>
    </div>
    <div class="input-group">
        <label>Email Address:</label>
        <input type="email" name="email" required><br><br>
    </div>
    <div class="input-group">
        <label>Marital Status:</label>
        <select name="marital">
            <option value="Married">Married</option>
            <option value="Unmarried">Unmarried</option>
            <option value="Other">Other</option>
        </select><br><br>
    </div>
    <div class="input-group">
        <label>Address:</label>
        <input type="text" name="address" required><br><br>
    </div>
    <div class="input-group">
        <label>City:</label>
        <input type="text" name="city" required><br><br>
    </div>
    <div class="input-group">
        <label>Pin Code:</label>
        <input type="text" name="pincode" required><br><br>
     </div>
     <div class="input-group">
        <label>State:</label>
        <input type="text" name="state" required><br><br>
     </div>
        <button type="submit" class="button-30" role="button">Next</button>
    </form>
</div>
</body>
</html>
