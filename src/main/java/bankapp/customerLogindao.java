package bankapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class customerLogindao {
	public static void main(String[] args) {
		
	}
    // Database URL, Username, and Password
    private final String jdbcURL = "jdbc:mysql://localhost:3306/banking";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "Kasi@9563";

    // JDBC Driver Name
    private final String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    // SQL Query to check login credentials
    private static final String LOGIN_QUERY = "SELECT * FROM customer WHERE CustomerFullName = ? AND CustomerPassword = ?";

    // Method to validate login credentials
    public boolean validate(String username, String password) {
        boolean status = false;

        try {
            // Load the JDBC driver
            Class.forName(jdbcDriver);

            // Establish the connection
            try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                 PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_QUERY)) {
                
                // Set parameters
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Check if user exists
                status = resultSet.next();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return status;
    }
}
