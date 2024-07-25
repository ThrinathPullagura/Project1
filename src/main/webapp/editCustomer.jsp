<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="bankapp.CustomerDAO1" %>
<%@ page import="bankapp.Customer1" %>

<%
String customerAccNo = request.getParameter("CustomerAccNo");
    CustomerDAO1 customerDAO = new CustomerDAO1();
    Customer1 customer = customerDAO.getCustomerByAccNo(customerAccNo);
%>

<html>
<head>
    <title>Edit Customer</title>
</head>
<body>
    <h2>Edit Customer</h2>
    <form action="EditCustomerServlet" method="post">
        <input type="hidden" name="CustomerAccNo" value="<%= customer.getCustomerAccNo() %>">
        <label for="CustomerFullName">Full Name:</label>
        <input type="text" id="CustomerFullName" name="CustomerFullName" value="<%= customer.getCustomerFullName() %>" required><br>
        <label for="CustomerAddress">Address:</label>
        <input type="text" id="CustomerAddress" name="CustomerAddress" value="<%= customer.getCustomerAddress() %>" required><br>
        <label for="CustomerMobileNo">Mobile No:</label>
        <input type="text" id="CustomerMobileNo" name="CustomerMobileNo" value="<%= customer.getCustomerMobileNo() %>" required><br>
        <label for="CustomerEmailid">Email:</label>
        <input type="email" id="CustomerEmailid" name="CustomerEmailid" value="<%= customer.getCustomerEmailid() %>" required><br>
        <label for="CustomerTypeofAcc">Type of Account:</label>
        <input type="text" id="CustomerTypeofAcc" name="CustomerTypeofAcc" value="<%= customer.getCustomerTypeofAcc() %>" required><br>
        <label for="CustomerDOB">DOB:</label>
        <input type="date" id="CustomerDOB" name="CustomerDOB" value="<%= customer.getCustomerDOB() %>" required><br>
        <label for="Id_Proof">ID Proof:</label>
        <input type="text" id="Id_Proof" name="Id_Proof" value="<%= customer.getId_Proof() %>" required><br>
        <label for="Id_Number">ID Number:</label>
        <input type="text" id="Id_Number" name="Id_Number" value="<%= customer.getId_Number() %>" required><br>
        <input type="submit" value="Update Customer">
    </form>
</body>
</html>
