<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <title>Banking System</title>
    <link rel="stylesheet" type="text/css" href="css/adminMenu.css">
    <link rel="stylesheet" type="text/css" href="css/popup.css"> <!-- Include the CSS file for the popup -->
    <script>
        function closePopup() {
            var popup = document.getElementById("popup");
            popup.style.opacity = "0";
            setTimeout(function() {
                popup.style.display = "none";
            }, 500); // Adjust the duration of the transition effect
        }
    </script>
</head>

<body>
    <div class="card">
        <h1>Banking System</h1>
        <form action="menuServlet" method="post">
            <button type="submit" name="action" value="addAccount">Add Account</button><br><br>
            <button type="submit" name="action" value="displayList">Display Account List</button><br><br>
            <button type="submit" name="action" value="exit">Exit</button><br><br>
        </form>
    </div>
    <% if(request.getAttribute("cardNumber") != null && request.getAttribute("pin") != null) { %>
    <div id="popup" class="popup show"> <!-- Apply the 'popup' class to the div and show it -->
        <div class="card"> <!-- Wrap the popup content in a card format -->
            <p>Card Number: <%= request.getAttribute("cardNumber") %></p>
            <p>PIN: <%= request.getAttribute("pin") %></p>
            <button onclick="closePopup()">OK</button> <!-- Add an OK button -->
        </div>
    </div>
    <% } %>
</body>
</html>
