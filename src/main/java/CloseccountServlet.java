import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CloseccountServlet")
public class CloseccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String customerAccNo = (String) session.getAttribute("customerAccNo");

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/bankapp";
        String dbUsername = "root";
        String dbPassword = "Kavya1510"; // Replace with your actual database password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);

            // Check balance
            String checkBalanceSql = "SELECT CustomerBalance FROM customer WHERE CustomerAccNo = ?";
            pst = conn.prepareStatement(checkBalanceSql);
            pst.setString(1, customerAccNo);
            rs = pst.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("CustomerBalance");
                if (balance == 0) {
                    // Delete account
                    String deleteAccountSql = "DELETE FROM customer WHERE CustomerAccNo = ?";
                    pst = conn.prepareStatement(deleteAccountSql);
                    pst.setString(1, customerAccNo);
                    int rowsAffected = pst.executeUpdate();
                    if (rowsAffected > 0) {
                        session.invalidate(); // Invalidate session
                        response.getWriter().write("Account closed successfully.");
                    } else {
                        response.getWriter().write("Failed to close account.");
                    }
                } else {
                    response.getWriter().write("Cannot close account. Balance must be zero.");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred while closing the account.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
