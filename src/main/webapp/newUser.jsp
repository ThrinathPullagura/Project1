<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Registration</title>
    <style>
        
    </style>
</head>
<body>
    <div class="container">
        <h2>Customer Registration</h2>
        <form action="AddCustomerServlet" method="post">
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" required>
            
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>
            
            <label for="mobileNo">Mobile No:</label>
            <input type="text" id="mobileNo" name="mobileNo" required>
            
            <label for="emailId">Email ID:</label>
            <input type="email" id="emailId" name="emailId" required>
            
            <label for="accountType">Type of Account:</label>
            <input type="text" id="accountType" name="accountType" required>
            
            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" required>
            
            <label for="idProof">ID Proof:</label>
            <input type="text" id="idProof" name="idProof" required>
            
            <label for="idNumber">ID Number:</label>
            <input type="text" id="idNumber" name="idNumber" required>
            
            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>
