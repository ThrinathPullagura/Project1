

import java.io.IOException;

import bankapp.Customer1;
import bankapp.CustomerDAO1;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditCustomerServlet
 */
public class EditCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerAccNo = request.getParameter("CustomerAccNo");
        String fullName = request.getParameter("CustomerFullName");
        String address = request.getParameter("CustomerAddress");
        String mobileNo = request.getParameter("CustomerMobileNo");
        String email = request.getParameter("CustomerEmailid");
        String accountType = request.getParameter("CustomerTypeofAcc");
        String dob = request.getParameter("CustomerDOB");
        String idProof = request.getParameter("Id_Proof");
        String idNumber = request.getParameter("Id_Number");

        Customer1 customer = new Customer1();
        customer.setCustomerAccNo(customerAccNo);
        customer.setCustomerFullName(fullName);
        customer.setCustomerAddress(address);
        customer.setCustomerMobileNo(mobileNo);
        customer.setCustomerEmailid(email);
        customer.setCustomerTypeofAcc(accountType);
        customer.setCustomerDOB(dob);
        customer.setId_Proof(idProof);
        customer.setId_Number(idNumber);

        CustomerDAO1 customerDAO = new CustomerDAO1();
        customerDAO.updateCustomer(customer);

        response.sendRedirect("viewAllAccounts.jsp");
    }

}
