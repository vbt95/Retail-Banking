package com.Controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.Dao.CustomerDao;
import com.Service.CustomerService;
import com.Bean.Customer;
import com.Bean.CustomerStatus;

/**
 * Servlet implementation class AddNewCustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {

	static final Logger LOGGER = Logger.getLogger(CustomerServlet.class);
	private static final long serialVersionUID = 1L;
    public CustomerServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		String sessionId=(String)session.getAttribute("sessionId");
		if(sessionId==null){
			response.sendRedirect("index.jsp");
			return;
		}
		else{
			if(session.getId()!=sessionId){
				response.sendRedirect("index.jsp");
				return;	
			}
		
		String id=(String)session.getAttribute("id");
		if(id==null){
			response.sendRedirect("index.jsp");
			return;
		}
		}
		
		String action = request.getParameter("action");
		if(action.equals("viewallcustomerstatus")){
			CustomerService service=new CustomerService();
			ArrayList<CustomerStatus> list=service.viewAllStatus();
			request.setAttribute("list", list);	
			request.getRequestDispatcher("viewAllCustomerStatus.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session=request.getSession();
		String sessionId=(String)session.getAttribute("sessionId");
		if(sessionId==null){
			response.sendRedirect("index.jsp");
			return;
		}
		else{
			if(session.getId()!=sessionId){
				response.sendRedirect("index.jsp");
				return;	
			}
		
		String id=(String)session.getAttribute("id");
		if(id==null){
			response.sendRedirect("index.jsp");
			return;
		}
		}
		
		String action = request.getParameter("action");
		if(action.equals("add"))
		{ 
			int SSN=Integer.parseInt(request.getParameter("SSN"));
			String CustomerName=request.getParameter("CustomerName");
			int age=Integer.parseInt(request.getParameter("age"));
			String AddressLine1=request.getParameter("address1");
			String AddressLine2 =request.getParameter("address2");
			String	City=request.getParameter("city");
			String  State=request.getParameter("state");
			Customer cust=new  Customer(SSN,CustomerName,age,AddressLine1,AddressLine2,City,State);
			int result=0;
			result = new CustomerService().addCustomer(cust);
			if(result>=1)
			{
				LOGGER.info("Successfully created customer !!");
				request.setAttribute("message", "Successfully created customer !!");
				request.getRequestDispatcher("success.jsp").forward(request,response);
			}
			else
			{
				LOGGER.error("Customer creation failed !!");
				request.setAttribute("message", "Oops!!Customer creation failed !!");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
		}
		else if(action.equals("search")){
			
			try {
				String value=request.getParameter("value");
				if(value.equalsIgnoreCase("customerID")){
					int id=Integer.parseInt(request.getParameter("searchval"));					
					CustomerStatus cust = new CustomerDao().searchCustomer(id);					
					if(cust!=null)
					{
						request.setAttribute("id", cust.getCustomerID());
						request.setAttribute("ssn", cust.getSSN());
						request.setAttribute("name", cust.getCustomerName());
						request.setAttribute("age", cust.getAge());
						request.setAttribute("add1", cust.getAddressLine1());
						request.setAttribute("add2", cust.getAddressLine2());
						request.setAttribute("city", cust.getCity());
						request.setAttribute("state", cust.getState());
						request.setAttribute("status", cust.getStatus());
						request.setAttribute("message", cust.getMessage());
						request.setAttribute("last_updated", cust.getLastUpdate());
						request.getRequestDispatcher("UpdateCustomer.jsp").forward(request, response);
					}
					else
					{
						LOGGER.error("No customer with given Customer ID exists !!");
						request.setAttribute("message", "Oops!!No customer with given Customer ID exists !!");
						request.getRequestDispatcher("error.jsp").forward(request,response);
					}
				}
				else{
					
					int id=Integer.parseInt(request.getParameter("searchval"));					
					CustomerStatus cust = new CustomerDao().searchCustomerSSN(id);			
					if(cust!=null)
					{
						request.setAttribute("id", cust.getCustomerID());
						request.setAttribute("ssn", cust.getSSN());
						request.setAttribute("name", cust.getCustomerName());
						request.setAttribute("age", cust.getAge());
						request.setAttribute("add1", cust.getAddressLine1());
						request.setAttribute("add2", cust.getAddressLine2());
						request.setAttribute("city", cust.getCity());
						request.setAttribute("state", cust.getState());
						request.setAttribute("status", cust.getStatus());
						request.setAttribute("message", cust.getMessage());
						request.setAttribute("last_updated", cust.getLastUpdate());
						request.getRequestDispatcher("UpdateCustomer.jsp").forward(request, response);
					}
					else
					{
						LOGGER.error("No customer with given SSN ID exists !!");
						request.setAttribute("message", "Oops!!No customer with given SSN ID exists !!");
						request.getRequestDispatcher("error.jsp").forward(request,response);
					}
				}
				
			} 
			catch (Exception e) {
			// TODO Auto-generated catch block
				LOGGER.error(e.getStackTrace());
				e.printStackTrace();
			}
			
		}
		else if(action.equals("update"))
		{ 
			try {
				int customerID = Integer.parseInt(request.getParameter("customerID"));			
				int SSN=Integer.parseInt(request.getParameter("SSN"));			
				String CustomerName=request.getParameter("CustomerName");
				int age=Integer.parseInt(request.getParameter("age"));
				String AddressLine1=request.getParameter("address1");
				String AddressLine2 =request.getParameter("address2");
				String	City=request.getParameter("city");
				String  State=request.getParameter("state");
				String status = request.getParameter("status");
				String message = request.getParameter("message");
				CustomerStatus cust=new  CustomerStatus(customerID,SSN,CustomerName,age,AddressLine1,AddressLine2,City,State,status,message);		
				int result=0;
				result = new CustomerDao().UpdateCustomer(cust);
				if(result>=1)
				{
					LOGGER.info("Successfully updated customer !!");
					request.setAttribute("message", "Successfully updated customer !!");
					request.getRequestDispatcher("success.jsp").forward(request,response);
				}
				else
				{
					LOGGER.error("Customer updation failed !!");
					request.setAttribute("message", "Oops!!Customer updation failed !!");
					request.getRequestDispatcher("error.jsp").forward(request,response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getStackTrace());
				e.printStackTrace();
			}
		}
		
		else if(action.equals("search_cus")){

			try {
					String value=request.getParameter("value");
					if(value.equalsIgnoreCase("customerID")){

						int id=Integer.parseInt(request.getParameter("searchval"));					
						CustomerStatus cust = new CustomerDao().searchCustomer(id);			
						if(cust!=null)
						{
							request.setAttribute("id", cust.getCustomerID());
							request.setAttribute("ssn", cust.getSSN());
							request.setAttribute("name", cust.getCustomerName());
							request.setAttribute("age", cust.getAge());
							request.setAttribute("add1", cust.getAddressLine1());
							request.setAttribute("add2", cust.getAddressLine2());
							request.setAttribute("city", cust.getCity());
							request.setAttribute("state", cust.getState());
							request.setAttribute("status", cust.getStatus());
							request.setAttribute("message", cust.getMessage());
							request.setAttribute("last_updated", cust.getLastUpdate());
							request.getRequestDispatcher("DisplayCustomer.jsp").forward(request, response);
						}
						else
						{
							LOGGER.error("No customer with given Customer ID exists !!");
							request.setAttribute("message", "Oops!!No customer with given Customer ID exists !!");
							request.getRequestDispatcher("error.jsp").forward(request,response);
						}
					}
					else{
						
						int id=Integer.parseInt(request.getParameter("searchval"));					
						CustomerStatus cust = null;
						cust = new CustomerDao().searchCustomerSSN(id);					
						if(cust!=null)
						{
							request.setAttribute("id", cust.getCustomerID());
							request.setAttribute("ssn", cust.getSSN());
							request.setAttribute("name", cust.getCustomerName());
							request.setAttribute("age", cust.getAge());
							request.setAttribute("add1", cust.getAddressLine1());
							request.setAttribute("add2", cust.getAddressLine2());
							request.setAttribute("city", cust.getCity());
							request.setAttribute("state", cust.getState());
							request.setAttribute("status", cust.getStatus());
							request.setAttribute("message", cust.getMessage());
							request.setAttribute("last_updated", cust.getLastUpdate());
							request.getRequestDispatcher("DisplayCustomer.jsp").forward(request, response);
						}
						else
						{
							LOGGER.error("No customer with given SSN ID exists !!");
							request.setAttribute("message", "Oops!!No customer with given SSN ID exists !!");
							request.getRequestDispatcher("error.jsp").forward(request,response);
						}
					}
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getStackTrace());
				e.printStackTrace();
			}
			
		}
		else if(action.equals("delete"))
		{
	
			int CustId=Integer.parseInt(request.getParameter("CustId"));
			CustomerService service = new CustomerService();
			int rowsDeleted = service.deleteCustomer(CustId);
			if (rowsDeleted > 0) {
				
				LOGGER.info("Successfully deleted customer !!");
				request.setAttribute("message", "Successfully deleted customer !!");
				request.getRequestDispatcher("success.jsp").forward(request,response);
			}
			else {
				LOGGER.error("Customer deletion failed !! Invalid customer ID!!");
				request.setAttribute("message", "Oops!! Invalid customer ID !!");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
		}
	
	}

}
