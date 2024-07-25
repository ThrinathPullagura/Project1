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
        <table>
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Date</th>
                    <th>Type</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    String accNo = session.getAttribute("customerAccNo").toString();
                    Connection conn = null;
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapp", "root", "Kavya1510S");
                        String sql = "SELECT * FROM transactions WHERE CustomerAccNo = ? ORDER BY TransactionDate DESC LIMIT 10";
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1, accNo);
                        rs = stmt.executeQuery();
                        while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("TransactionId") %></td>
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
                <input type="hidden" name="customerAccNo" value="<%= session.getAttribute("customerAccNo") %>">
                <button type="submit">Download PDF</button>
            </form>
            <a href="CustomerDashboard.jsp" class="button-group button">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
