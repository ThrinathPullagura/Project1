package bankapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/bankapp";
    private static final String USER = "root";
    private static final String PASSWORD = "Kavya1510";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<BankTransaction> getLast10Transactions(String accNo) {
        List<BankTransaction> transactions = new ArrayList<>();
        try (Connection con = getConnection()) {
            String query = "SELECT TransactionId, CustomerAccNo, TransactionDate, TransactionType, Amount " +
                           "FROM transactions WHERE CustomerAccNo = ? " +
                           "ORDER BY TransactionDate DESC LIMIT 10";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, accNo);
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        transactions.add(new BankTransaction(
                            rs.getString("TransactionId"),
                            rs.getDouble("Amount"),
                            rs.getString("TransactionType"),
                            rs.getDate("TransactionDate")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Debugging statement
        System.out.println("Retrieved transactions: " + transactions);
        return transactions;
    }
}
