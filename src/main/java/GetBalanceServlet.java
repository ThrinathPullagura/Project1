
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankapp.DatabaseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GetBalanceServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("customerAccNo") != null) {
            String customerAccNo = (String) session.getAttribute("customerAccNo");
            double balance = getBalanceFromDatabase(customerAccNo); // Implement this method

            // Create JSON response manually
            String jsonResponse = String.format("{\"balance\": %.2f}", balance);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter out = response.getWriter();
            out.print(jsonResponse);
            out.flush();
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated.");
        }
    }

	private double getBalanceFromDatabase(String accountNo) {
        double balance = 0.0;
        String sql = "SELECT CustomerBalance FROM customer WHERE CustomerAccNo = ?";
        
        try (Connection conn = DatabaseUtil.getConnection(); // Get database connection
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, accountNo); // Set the account number in the query
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                balance = rs.getDouble("CustomerBalance");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
        
        return balance;
    }
}
