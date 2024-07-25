package bankapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO1 {
    private static final String URL = "jdbc:mysql://localhost:3306/bankapp";
    private static final String USER = "root";
    private static final String PASSWORD = "Kavya1510";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void addCustomer(Customer1 customer) {
        String sql = "INSERT INTO customer "
        		+ "(CustomerPassword, CustomerFullName, CustomerAddress, CustomerMobileNo, CustomerEmailid, CustomerTypeofAcc, CustomerDOB, Id_Proof, Id_Number, CustomerBalance) "
        		+ "VALUES ('P123', ?, ?, ?, ?, ?, ?, ?, ?, 1000)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getCustomerFullName());
            stmt.setString(2, customer.getCustomerAddress());
            stmt.setString(3, customer.getCustomerMobileNo());
            stmt.setString(4, customer.getCustomerEmailid());
            stmt.setString(5, customer.getCustomerTypeofAcc());
            stmt.setString(6, customer.getCustomerDOB());
            stmt.setString(7, customer.getId_Proof());
            stmt.setString(8, customer.getId_Number());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer1> getAllCustomers() {
        List<Customer1> customers = new ArrayList<>();
        String sql = "SELECT CustomerAccNo, CustomerFullName, CustomerAddress, CustomerMobileNo, CustomerEmailid, CustomerTypeofAcc, CustomerDOB, Id_Proof, Id_Number FROM customer";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Customer1 customer = new Customer1();
                customer.setCustomerAccNo(rs.getString("CustomerAccNo"));
                customer.setCustomerFullName(rs.getString("CustomerFullName"));
                customer.setCustomerAddress(rs.getString("CustomerAddress"));
                customer.setCustomerMobileNo(rs.getString("CustomerMobileNo"));
                customer.setCustomerEmailid(rs.getString("CustomerEmailid"));
                customer.setCustomerTypeofAcc(rs.getString("CustomerTypeofAcc"));
                customer.setCustomerDOB(rs.getString("CustomerDOB"));
                customer.setId_Proof(rs.getString("Id_Proof"));
                customer.setId_Number(rs.getString("Id_Number"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public Customer1 getCustomerByAccNo(String accNo) {
        Customer1 customer = null;
        String sql = "SELECT CustomerAccNo, CustomerFullName, CustomerAddress, CustomerMobileNo, CustomerEmailid, CustomerTypeofAcc, CustomerDOB, Id_Proof, Id_Number FROM customer WHERE CustomerAccNo = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accNo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer1();
                    customer.setCustomerAccNo(rs.getString("CustomerAccNo"));
                    customer.setCustomerFullName(rs.getString("CustomerFullName"));
                    customer.setCustomerAddress(rs.getString("CustomerAddress"));
                    customer.setCustomerMobileNo(rs.getString("CustomerMobileNo"));
                    customer.setCustomerEmailid(rs.getString("CustomerEmailid"));
                    customer.setCustomerTypeofAcc(rs.getString("CustomerTypeofAcc"));
                    customer.setCustomerDOB(rs.getString("CustomerDOB"));
                    customer.setId_Proof(rs.getString("Id_Proof"));
                    customer.setId_Number(rs.getString("Id_Number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void updateCustomer(Customer1 customer) {
        String sql = "UPDATE customer SET CustomerFullName = ?, CustomerAddress = ?, CustomerMobileNo = ?, CustomerEmailid = ?, CustomerTypeofAcc = ?, CustomerDOB = ?, Id_Proof = ?, Id_Number = ? WHERE CustomerAccNo = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getCustomerFullName());
            stmt.setString(2, customer.getCustomerAddress());
            stmt.setString(3, customer.getCustomerMobileNo());
            stmt.setString(4, customer.getCustomerEmailid());
            stmt.setString(5, customer.getCustomerTypeofAcc());
            stmt.setString(6, customer.getCustomerDOB());
            stmt.setString(7, customer.getId_Proof());
            stmt.setString(8, customer.getId_Number());
            stmt.setString(9, customer.getCustomerAccNo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(String accNo) {
        String sql = "DELETE FROM customer WHERE CustomerAccNo = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accNo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
