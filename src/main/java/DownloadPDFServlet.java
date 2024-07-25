import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DownloadPDFServlet")
public class DownloadPDFServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerAccNo = request.getParameter("customerAccNo");

        // Set the content type and headers for PDF download
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=transactions.pdf");

        // Initialize PDF document
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Add title
            document.add(new Paragraph("Transaction History"));
            document.add(new Paragraph("Account Number: " + customerAccNo));

            // Add table
            PdfPTable table = new PdfPTable(4); // 4 columns
            table.addCell("Transaction ID");
            table.addCell("Date");
            table.addCell("Type");
            table.addCell("Amount");

            // Fetch transactions from database
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapp", "root", "Kavya1510");
                String sql = "SELECT * FROM transactions WHERE CustomerAccNo = ? ORDER BY TransactionDate DESC LIMIT 10";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, customerAccNo);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    table.addCell(String.valueOf(rs.getInt("TransactionId")));
                    table.addCell(rs.getTimestamp("TransactionDate").toString());
                    table.addCell(rs.getString("TransactionType"));
                    table.addCell(String.valueOf(rs.getDouble("Amount")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
                try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
                try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
            }

            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
