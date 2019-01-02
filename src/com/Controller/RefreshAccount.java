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
import com.Dao.AccountDao;

/**
 * Servlet implementation class RefreshAccount
 */
@WebServlet("/RefreshAccount")
public class RefreshAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefreshAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
		
		String acctId=request.getParameter("acctId");
		
		AccountDao dao=new AccountDao();
		AccountStatus acct=dao.updateStatus(Integer.parseInt(acctId));
		PrintWriter writer=response.getWriter();
		
		writer.write(
				"<tr>"+
		"<td>"+acct.getCustid()+"</td>"+
		"<td>"+acct.getAccid()+"</td>"+
		"<td>"+acct.getAcctype()+"</td>"+
		"<td>"+acct.getStatus()+"</td>"+
		"<td>"+acct.getMessage()+"</td>"+
		"<td>"+acct.getLastUpdate()+"</td>"
		+"<td><button type=\"button\" onclick=\"$.get('RefreshCustomer?custId="+acct.getAccid()+"', function(data) {"
		  +"$('.div"+acct.getAccid()+"').html(data);"
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
