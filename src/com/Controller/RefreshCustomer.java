package com.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Bean.AccountStatus;
import com.Bean.CustomerStatus;
import com.Dao.AccountDao;
import com.Dao.CustomerDao;

/**
 * Servlet implementation class RefreshCustomer
 */
@WebServlet("/RefreshCustomer")
public class RefreshCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefreshCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//System.out.println("in get of refresh customer");
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
		
		String custId=request.getParameter("custId");
		

		System.out.println(custId);
		
		CustomerDao dao=new CustomerDao();
		CustomerStatus cust=dao.updateStatus(Integer.parseInt(custId));
		PrintWriter writer=response.getWriter();
		
		writer.write(
				"<tr>"+
		"<td>"+cust.getCustomerID()+"</td>"+
		"<td>"+cust.getSSN()+"</td>"+
		"<td>"+cust.getStatus()+"</td>"+
		"<td>"+cust.getMessage()+"</td>"+
		"<td>"+cust.getLastUpdate()+"</td>"
		+"<td><button type=\"button\" onclick=\"$.get('RefreshCustomer?custId="+cust.getCustomerID()+"', function(data) {"
		  +"$('.div"+cust.getCustomerID()+"').html(data);"
	+"});\">Refresh</button>"
		+"</tr>");
		
		writer.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
