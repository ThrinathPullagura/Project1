package bankapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/bankapp";
    private static final String USER = "root";
    private static final String PASSWORD = "Kavya1510";

    private Connection getConnection() throws SQLException 
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void depositAmount(String accNo, double amount) {
        try (Connection con = getConnection()) {
            String query = "UPDATE customer SET CustomerBalance = CustomerBalance + ? WHERE CustomerAccNo = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setDouble(1, amount);
                pst.setString(2, accNo);
                pst.executeUpdate();
                
                // Log transaction
                logTransaction(accNo, amount, "Deposit");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void withdrawAmount(String accNo, double amount) {
        try (Connection con = getConnection()) {
            // Check current balance
            String checkQuery = "SELECT CustomerBalance FROM customer WHERE CustomerAccNo = ?";
            try (PreparedStatement pst = con.prepareStatement(checkQuery)) {
                pst.setString(1, accNo);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    double currentBalance = rs.getDouble("CustomerBalance");
                    if (currentBalance >= amount) {
                        // Proceed with withdrawal
                        String updateQuery = "UPDATE customer SET CustomerBalance = CustomerBalance - ? WHERE CustomerAccNo = ?";
                        try (PreparedStatement updatePst = con.prepareStatement(updateQuery)) {
                            updatePst.setDouble(1, amount);
                            updatePst.setString(2, accNo);
                            updatePst.executeUpdate();
                            
                            // Log transaction
                            logTransaction(accNo, amount, "Withdraw");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getLast10Transactions(String accNo) {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection con = getConnection()) {
            String query = "SELECT TransactionId, CustomerAccNo, TransactionDate, TransactionType, Amount " +
                           "FROM transactions WHERE CustomerAccNo = ? " +
                           "ORDER BY TransactionDate DESC LIMIT 10";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, accNo);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    transactions.add(new Transaction(
                        rs.getString("TransactionId"),
                        rs.getDouble("Amount"),
                        rs.getString("TransactionType"),
                        rs.getDate("TransactionDate")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Debugging statement
        System.out.println("Retrieved transactions: " + transactions);
        return transactions;
    }

    public boolean closeAccount(String accNo) {
        try (Connection con = getConnection()) {
            // Check balance
            String checkQuery = "SELECT CustomerBalance FROM customer WHERE CustomerAccNo = ?";
            try (PreparedStatement pst = con.prepareStatement(checkQuery)) {
                pst.setString(1, accNo);
                ResultSet rs = pst.executeQuery();
                if (rs.next() && rs.getDouble("CustomerBalance") == 0) {
                    // Proceed with account closure
                    String deleteQuery = "DELETE FROM customer WHERE CustomerAccNo = ?";
                    try (PreparedStatement deletePst = con.prepareStatement(deleteQuery)) {
                        deletePst.setString(1, accNo);
                        deletePst.executeUpdate();
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void logTransaction(String accNo, double amount, String type) {
        try (Connection con = getConnection()) {
            String query = "INSERT INTO transactions (CustomerAccNo, amount, transactionType, transactionDate) VALUES (?, ?, ?, NOW())";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, accNo);
                pst.setDouble(2, amount);
                pst.setString(3, type);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public Object getBalance(String accNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deposit(String accNo, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean withdraw(String accNo, double amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
