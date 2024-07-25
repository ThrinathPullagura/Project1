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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/ResetAdminPasswordServlet")
public class ResetAdminPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "New passwords do not match.");
            request.getRequestDispatcher("settings.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        String adminFullName = (String) session.getAttribute("adminFullName");

        if (adminFullName == null) {
            response.getWriter().write("Session expired. Please log in again.");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            
            Thread.sleep(2000);

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapp", "root", "Kavya1510");

            
            String verifySql = "SELECT Password FROM admin WHERE FullName = ?";
            stmt = conn.prepareStatement(verifySql);
            stmt.setString(1, adminFullName);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String currentPassword = rs.getString("Password");
                if (currentPassword.equals(oldPassword)) {
                    // Update password
                    String updateSql = "UPDATE admin SET Password = ? WHERE FullName = ?";
                    stmt = conn.prepareStatement(updateSql);
                    stmt.setString(1, newPassword);
                    stmt.setString(2, adminFullName);
                    int rowsUpdated = stmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        response.getWriter().write("Password successfully updated.");
                    } else {
                        response.getWriter().write("Password update failed.");
                    }
                } else {
                    request.setAttribute("error", "Old password is incorrect.");
                    request.getRequestDispatcher("settings.jsp").forward(request, response);
                }
            } else {
                response.getWriter().write("Account not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
