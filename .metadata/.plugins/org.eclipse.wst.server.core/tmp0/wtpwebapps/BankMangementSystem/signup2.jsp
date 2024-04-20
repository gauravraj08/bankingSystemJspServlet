<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bank Management System - Signup2</title>
<link rel="stylesheet" type="text/css" href="css/signup2.css">
<script>
    function validateForm() {
        var aadhar = document.getElementById("aadhar").value;
        if (aadhar.length !== 12) {
            alert("Aadhar number must be 12 digits.");
            return false;
        }
        return true;
    }
</script>
</head>
<body>
    <div class="container">
        <form action="Signup2Servlet" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="formno" value="<%= request.getParameter("formno") %>">
            <label for="religion">Religion:</label>
            <select id="religion" name="religion">
                <option value="Hindu">Hindu</option>
                <option value="Muslim">Muslim</option>
                <option value="Sikh">Sikh</option>
                <option value="Christian">Christian</option>
                <option value="Other">Other</option>
            </select><br><br>
            <label for="category">Category:</label>
            <select id="category" name="category">
                <option value="General">General</option>
                <option value="OBC">OBC</option>
                <option value="SC">SC</option>
                <option value="ST">ST</option>
                <option value="Other">Other</option>
            </select><br><br>
            <label for="income">Income:</label>
            <select id="income" name="income">
                <option value="Null">Null</option>
                <option value="<1,50,000">&lt;1,50,000</option>
                <option value="<2,50,000">&lt;2,50,000</option>
                <option value="5,00,000">5,00,000</option>
                <option value="Uptp 10,00,000">Uptp 10,00,000</option>
                <option value="Above 10,00,000">Above 10,00,000</option>
            </select><br><br>
            <label for="education">Education:</label>
            <select id="education" name="education">
                <option value="Non-Graduate">Non-Graduate</option>
                <option value="Graduate">Graduate</option>
                <option value="Post-Graduate">Post-Graduate</option>
                <option value="Doctrate">Doctrate</option>
                <option value="Others">Others</option>
            </select><br><br>
            <label for="occupation">Occupation:</label>
            <select id="occupation" name="occupation">
                <option value="Salaried">Salaried</option>
                <option value="Self-Employed">Self-Employed</option>
                <option value="Business">Business</option>
                <option value="Student">Student</option>
                <option value="Retired">Retired</option>
                <option value="Other">Other</option>
            </select><br><br>
            <label for="pan">PAN Number:</label>
            <input type="text" id="pan" name="pan"><br><br>
            <label for="aadhar">Aadhar Number:</label>
            <input type="text" id="aadhar" name="aadhar"><br><br>
            <label for="seniorCitizen">Senior Citizen:</label>
            <input type="radio" id="yes" name="seniorCitizen" value="Yes">
            <label for="yes">Yes</label>
            <input type="radio" id="no" name="seniorCitizen" value="No">
            <label for="no">No</label><br><br>
            <label for="existingCustomer">Existing Customer:</label>
            <input type="radio" id="yes" name="existingCustomer" value="Yes">
            <label for="yes">Yes</label>
            <input type="radio" id="no" name="existingCustomer" value="No">
            <label for="no">No</label><br><br>
            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
