package bankapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionListDAO {
    public List<CustomerTransaction> getAllTransactions() {
        List<CustomerTransaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CustomerTransaction transaction = new CustomerTransaction();
                transaction.setTransactionId(rs.getInt("TransactionId"));
                transaction.setCustomerAccNo(rs.getInt("CustomerAccNo"));
                transaction.setTransactionDate(rs.getTimestamp("TransactionDate"));
                transaction.setTransactionType(rs.getString("TransactionType"));
                transaction.setAmount(rs.getDouble("Amount"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
