

import java.io.IOException;

import bankapp.Customer1;
import bankapp.CustomerDAO1;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AddCustomerServlet
 */
public class AddCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCustomerServlet() {
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
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("emailId");
        String accountType = request.getParameter("accountType");
        String dob = request.getParameter("dob");
        String idProof = request.getParameter("idProof");
        String idNumber = request.getParameter("idNumber");

        Customer1 customer = new Customer1();
        customer.setCustomerFullName(fullName);
        customer.setCustomerAddress(address);
        customer.setCustomerMobileNo(mobileNo);
        customer.setCustomerEmailid(email);
        customer.setCustomerTypeofAcc(accountType);
        customer.setCustomerDOB(dob);
        customer.setId_Proof(idProof);
        customer.setId_Number(idNumber);

        CustomerDAO1 customerDAO = new CustomerDAO1();
        customerDAO.addCustomer(customer);

        response.sendRedirect("CustomerAddSuccess.jsp");
    }

}
