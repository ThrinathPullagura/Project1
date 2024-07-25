import java.io.IOException;
import java.util.List;

import bankapp.BankTransaction; // Updated to reflect the class name
import bankapp.TransactionDAO; // Ensure this is the correct DAO class
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ViewTransactionsServlet")
public class ViewTransactionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewTransactionsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accNo = (String) session.getAttribute("customerAccNo");

        TransactionDAO dao = new TransactionDAO();
        List<BankTransaction> transactions = dao.getLast10Transactions(accNo);

        // Debugging statement
        System.out.println("Setting transactions attribute: " + transactions);

        request.setAttribute("transactions", transactions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminTransactions.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
