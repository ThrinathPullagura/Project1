import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        HttpSession session = request.getSession();
        String customerAccNo = (String) session.getAttribute("customerAccNo");

        // Database connection and update
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapp", "root", "Kavya1510");

            // Verify old password
            String verifySql = "SELECT CustomerPassword FROM customer WHERE CustomerAccNo = ?";
            stmt = conn.prepareStatement(verifySql);
            stmt.setString(1, customerAccNo);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                String currentPassword = rs.getString("CustomerPassword");
                if (currentPassword.equals(oldPassword)) {
                    // Update password
                    String updateSql = "UPDATE customer SET CustomerPassword = ? WHERE CustomerAccNo = ?";
                    stmt = conn.prepareStatement(updateSql);
                    stmt.setString(1, newPassword);
                    stmt.setString(2, customerAccNo);
                    int rowsUpdated = stmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        response.getWriter().write("Password successfully updated.");
                    } else {
                        response.getWriter().write("Password update failed.");
                    }
                } else {
                    response.getWriter().write("Old password is incorrect.");
                }
            } else {
                response.getWriter().write("Account not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred: " + e.getMessage());
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
