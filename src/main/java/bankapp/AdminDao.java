package bankapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/bankapp";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Kavya1510";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public class AdminDAO {
        public Admin getAdminByUsername(String username) {
            Admin admin = null;
            String sql = "SELECT * FROM admin WHERE FullName = ?";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    admin = new Admin();
                    admin.setFullName(rs.getString("FullName"));
                    admin.setEmailid(rs.getString("Emailid"));
                    admin.setPhoneno(rs.getString("phoneno"));
                    admin.setInfo(rs.getString("Info"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return admin;
        }

        public boolean validateAdmin(String username, String password) {
            String sql = "SELECT * FROM admin WHERE FullName = ? AND Password = ?";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
}
}
