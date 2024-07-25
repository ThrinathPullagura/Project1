<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        
    </style>
</head>
<body>
    <div class="header">
        <h1>Banking System Admin Dashboard</h1>
    </div>
    <div class="sidebar">
        <a href="adminManageusers.html">Manage Users</a>
        <a href="adminTransactions.jsp">Transactions</a>
        <a href="settings.jsp">Reset Password</a>
        <a href="Index.html">Logout</a>
    </div>
    <div class="content">
        <div class="main-content">
            <div class="header">
                <span>Admin</span>
            </div>
            <div class="admin-info container">
                <%
                    String fullName = (String) session.getAttribute("adminFullName");
                    String email = (String) session.getAttribute("adminEmailid");
                    String phone = (String) session.getAttribute("adminPhoneno");
                    String info = (String) session.getAttribute("adminInfo");
                %>
                <h2>Welcome, <%= fullName %></h2>
                <p><strong>Name:</strong> <%= fullName %></p>
                <p><strong>Email:</strong> <%= email %></p>
                <p><strong>Phone:</strong> <%= phone %></p>
                <p><strong>Info:</strong> <%= info %></p>
            </div>
        </div>
    </div>
</body>
</html>
