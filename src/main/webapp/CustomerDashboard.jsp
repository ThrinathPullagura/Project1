<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        /* Additional inline styles if needed */
    </style>
    <script>
        // Your JavaScript remains unchanged
    </script>
</head>
<body>
    <div id="overlay"></div>
    <div class="container">
        <h1>Welcome, <%= session.getAttribute("customerName") %></h1>
        <h2>Account Details</h2>
        <div class="account-details">
            <p>Account Holder: <%= session.getAttribute("customerName") %></p>
            <p>Account Number: <%= session.getAttribute("customerAccNo") %></p>
            <p id="customerBalance">Balance: <%= session.getAttribute("customerBalance") %></p>
        </div>
        <div class="button-group">
            <button onclick="showDialog('Deposit')">Deposit</button>
            <button onclick="showDialog('Withdraw')">Withdraw</button>
            <button onclick="showDialog('ViewBalance')">View Balance</button>
            <button onclick="showDialog('ResetPassword')">Reset Password</button>
            <button onclick="window.location.href='ViewTransactions.jsp'">View Transactions</button>
            <button onclick="showDialog('CloseAccount')">Close Account</button>
            <a href="Index.html">Logout</a>
        </div>
    </div>

    <div id="amountDialog" class="dialog">
        <form action="TransactionServlet" method="post" onsubmit="return validateAmount()">
            <input type="hidden" name="action" id="actionType" value="">
            <label for="amount">Enter Amount:</label>
            <input type="number" name="amount" step="0.01" required>
            <input type="submit" value="Submit">
            <button type="button" onclick="closeDialog()">Cancel</button>
        </form>
    </div>

    <div id="balanceDialog" class="dialog">
        <p id="balanceAmount">Balance: </p>
        <button type="button" onclick="closeDialog()">Close</button>
    </div>

    <div id="confirmCloseDialog" class="dialog">
        <p>Are you sure you want to close your account? This action cannot be undone.</p>
        <button type="button" onclick="confirmClosure()">Confirm</button>
        <button type="button" onclick="closeDialog()">Cancel</button>
    </div>

    <div id="resetPasswordDialog" class="dialog">
        <form>
            <label for="oldPassword">Old Password:</label>
            <input type="password" id="oldPassword" name="oldPassword" required>
            <label for="newPassword">New Password:</label>
            <input type="password" id="newPassword" name="newPassword" required>
            <label for="confirmPassword">Confirm New Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            <button type="button" onclick="resetPassword()">Submit</button>
            <button type="button" onclick="closeDialog()">Cancel</button>
        </form>
    </div>
</body>
</html>