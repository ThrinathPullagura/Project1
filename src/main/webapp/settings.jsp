<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>
    <style>
        
    </style>
</head>
<body>
    <div class="container">
        <h2>Reset Password</h2>
        <form action="ResetAdminPasswordServlet" method="post">
            <div class="form-group">
                <label for="oldPassword">Current Password:</label>
                <input type="password" id="oldPassword" name="oldPassword" required>
            </div>
            <div class="form-group">
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="newPassword" required>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm New Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
            <div class="form-group">
                <button type="submit">Reset Password</button>
            </div>
            <div class="error">
                <% if (request.getAttribute("error") != null) { %>
                    <%= request.getAttribute("error") %>
                <% } %>
            </div>
        </form>
    </div>
</body>
</html>
