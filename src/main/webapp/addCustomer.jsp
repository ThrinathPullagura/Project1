<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
    <h2>Add New Customer</h2>
    <form action="addCustomerServlet" method="post">
        <label for="CustomerFullName">Full Name:</label>
        <input type="text" id="CustomerFullName" name="CustomerFullName" required><br>
        <label for="CustomerAddress">Address:</label>
        <input type="text" id="CustomerAddress" name="CustomerAddress" required><br>
        <label for="CustomerMobileNo">Mobile No:</label>
        <input type="text" id="CustomerMobileNo" name="CustomerMobileNo" required><br>
        <label for="CustomerEmailid">Email:</label>
        <input type="email" id="CustomerEmailid" name="CustomerEmailid" required><br>
        <label for="CustomerTypeofAcc">Type of Account:</label>
        <input type="text" id="CustomerTypeofAcc" name="CustomerTypeofAcc" required><br>
        <label for="CustomerDOB">DOB:</label>
        <input type="date" id="CustomerDOB" name="CustomerDOB" required><br>
        <label for="Id_Proof">ID Proof:</label>
        <input type="text" id="Id_Proof" name="Id_Proof" required><br>
        <label for="Id_Number">ID Number:</label>
        <input type="text" id="Id_Number" name="Id_Number" required><br>
        <input type="submit" value="Add Customer">
    </form>
</body>
</html>
