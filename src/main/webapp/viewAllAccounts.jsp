<%@ page import="java.util.List" %>
<%@ page import="bankapp.CustomerDAO1" %>
<%@ page import="bankapp.Customer1" %>

<%
CustomerDAO1 customerDAO = new CustomerDAO1();
    List<Customer1> customers = customerDAO.getAllCustomers();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 90%;
            margin: 0 auto;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        a {
            color: #3498db;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        .actions a {
            margin-right: 10px;
        }
        .actions a.delete {
            color: #e74c3c;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Customer Management</h2>
        <table>
            <thead>
                <tr>
                    <th>Account No</th>
                    <th>Full Name</th>
                    <th>Address</th>
                    <th>Mobile No</th>
                    <th>Email</th>
                    <th>Type of Account</th>
                    <th>DOB</th>
                    <th>Id Proof</th>
                    <th>Id Number</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                for (Customer1 customer : customers) {
                %>
                    <tr>
                        <td><%= customer.getCustomerAccNo() %></td>
                        <td><%= customer.getCustomerFullName() %></td>
                        <td><%= customer.getCustomerAddress() %></td>
                        <td><%= customer.getCustomerMobileNo() %></td>
                        <td><%= customer.getCustomerEmailid() %></td>
                        <td><%= customer.getCustomerTypeofAcc() %></td>
                        <td><%= customer.getCustomerDOB() %></td>
                        <td><%= customer.getId_Proof() %></td>
                        <td><%= customer.getId_Number() %></td>
                        <td class="actions">
                            <a href="editCustomer.jsp?CustomerAccNo=<%= customer.getCustomerAccNo() %>">Edit</a>
                            <a href="DeleteCutomerServlet?CustomerAccNo=<%= customer.getCustomerAccNo() %>" class="delete" onclick="return confirm('Are you sure?');">Delete</a>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
