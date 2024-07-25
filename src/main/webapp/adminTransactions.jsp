<%@ page import="java.sql.*, java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Transactions</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        
    </style>
</head>
<body>
    <div class="container">
        <h1>Transaction History</h1>
        <div class="form-container">
            <form action="ViewTransactionsServlet" method="post">
                <input type="text" name="customerAccNo" placeholder="Enter Customer Account No">
                <button type="submit">Get Transactions</button>
            </form>
            <form action="ViewTransactionsServlet" method="post">
                <input type="hidden" name="allTransactions" value="true">
                <button type="submit">View All Transactions</button>
            </form>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Customer Account No</th>
                    <th>Customer Full Name</th>
                    <th>Date</th>
                    <th>Type</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Connection conn = null;
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    String customerAccNo = request.getParameter("customerAccNo");
                    String allTransactions = request.getParameter("allTransactions");

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "Kasi@9563");

                        String sql;
                        if (allTransactions != null && allTransactions.equals("true")) {
                            sql = "SELECT t.*, c.CustomerFullName FROM transactions t JOIN customer c ON t.CustomerAccNo = c.CustomerAccNo ORDER BY t.TransactionDate DESC";
                            stmt = conn.prepareStatement(sql);
                        } else if (customerAccNo != null && !customerAccNo.isEmpty()) {
                            sql = "SELECT t.*, c.CustomerFullName FROM transactions t JOIN customer c ON t.CustomerAccNo = c.CustomerAccNo WHERE t.CustomerAccNo = ? ORDER BY t.TransactionDate DESC";
                            stmt = conn.prepareStatement(sql);
                            stmt.setString(1, customerAccNo);
                        } else {
                            sql = "SELECT t.*, c.CustomerFullName FROM transactions t JOIN customer c ON t.CustomerAccNo = c.CustomerAccNo ORDER BY t.TransactionDate DESC LIMIT 10";
                            stmt = conn.prepareStatement(sql);
                        }

                        rs = stmt.executeQuery();
                        while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("TransactionId") %></td>
                    <td><%= rs.getString("CustomerAccNo") %></td>
                    <td><%= rs.getString("CustomerFullName") %></td>
                    <td><%= rs.getTimestamp("TransactionDate") %></td>
                    <td><%= rs.getString("TransactionType") %></td>
                    <td><%= rs.getDouble("Amount") %></td>
                </tr>
                <%
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                        try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                    }
                %>
            </tbody>
        </table>
        <div class="button-group">
            <form action="DownloadPDFServlet" method="post">
                <input type="hidden" name="customerAccNo" value="<%= customerAccNo %>">
                <button type="submit">Download PDF</button>
            </form>
            <a href="AdminDashboard.jsp" class="button-group button">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
