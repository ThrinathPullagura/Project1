<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update User Details</title>
</head>
<body>
    <h1>Update User Details</h1>
    <form action="UpdateUserServlet" method="post">
        <input type="hidden" name="accountNo" value="${param.accountNo}" />
        
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" value="${param.fullName}" required /><br /><br />

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${param.address}" required /><br /><br />

        <label for="mobileNo">Mobile Number:</label>
        <input type="text" id="mobileNo" name="mobileNo" value="${param.mobileNo}" required /><br /><br />

        <label for="emailId">Email ID:</label>
        <input type="email" id="emailId" name="emailId" value="${param.emailId}" required /><br /><br />

        <label for="typeOfAcc">Type of Account:</label>
        <input type="text" id="typeOfAcc" name="typeOfAcc" value="${param.typeOfAcc}" required /><br /><br />

        <label for="dob">Date of Birth:</label>
        <input type="date" id="dob" name="dob" value="${param.dob}" required /><br /><br />

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${param.password}" required /><br /><br />

        <input type="submit" value="Update Details" />
    </form>
</body>
</html>
