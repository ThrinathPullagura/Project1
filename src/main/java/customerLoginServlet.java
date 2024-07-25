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

@WebServlet("/customerLoginServlet")
public class customerLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username22");
        String password = request.getParameter("password22");

        boolean valid = validateCredentials(username, password);

        if (valid) {
        	   HttpSession session = request.getSession();

        	   session.setAttribute("customerAccNo",getCustomerAccNo(username, password) );
        	   session.setAttribute("customerBalance",getCustomerBalance(username, password) );
        	   session.setAttribute("customerName",getCustomerName(username, password) );
            response.sendRedirect("CustomerDashboard.jsp");
        } else {
            response.sendRedirect("failure.html");
        }
    }

    private boolean validateCredentials(String username, String password) {
        boolean isValid = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/bankapp";
        String dbUsername = "root";
        String dbPassword = "Kavya1510"; // Replace with your actual database password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            pst = conn.prepareStatement("SELECT * FROM customer WHERE CustomerFullName = ? AND  CustomerPassword = ?");
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                isValid = true;
             
                System.out.println("  TRUE returned ");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isValid;
    }
    
    private static String getCustomerAccNo(String username, String password) {
        boolean isValid = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/banking";
        String dbUsername = "root";
        String dbPassword = "Kasi@9563"; // Replace with your actual database password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            pst = conn.prepareStatement("SELECT CustomerAccNo FROM customer WHERE CustomerFullName = ? AND  CustomerPassword = ?");
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
               return rs.getString(1);
             
                //System.out.println("  TRUE returned ");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    
    private static String getCustomerName(String username, String password) {
        boolean isValid = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/bankapp";
        String dbUsername = "root";
        String dbPassword = "Kavya1510"; // Replace with your actual database password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            pst = conn.prepareStatement("SELECT CustomerFullname FROM customer WHERE CustomerFullName = ? AND  CustomerPassword = ?");
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
               return rs.getString(1);
             
                //System.out.println("  TRUE returned ");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    
    private static String getCustomerBalance(String username, String password) {
        boolean isValid = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/bankapp";
        String dbUsername = "root";
        String dbPassword = "Kavya1510"; // Replace with your actual database password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            pst = conn.prepareStatement("SELECT CustomerBalance FROM customer WHERE CustomerFullName = ? AND  CustomerPassword = ?");
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
               return rs.getString(1);
             
                //System.out.println("  TRUE returned ");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
