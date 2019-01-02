package com.Controller;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.Bean.Account;
import com.Service.AccountService;
import com.Bean.AccountStatus;
import com.Dao.AccountDao;


/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(AccountServlet.class);
    /**
     * Default constructor. 
     */
    public AccountServlet() {
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
		
		String action = request.getParameter("action");
		if(action.equals("viewallaccountstatus")){
			AccountService service=new AccountService();
			ArrayList<AccountStatus> list=service.viewAllStatus();
			
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("viewAllAccountStatus.jsp").forward(request, response);
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
		AccountService service = new AccountService();
		if(action.equals("add")){
			int Customer_Id = Integer.parseInt(request.getParameter("custid"));
			String Account_Type = request.getParameter("acctype");
			double Deposit_Amount = Double.parseDouble(request.getParameter("depamt"));
			Account a = new Account(Customer_Id, Account_Type, Deposit_Amount);
			int result = service.insertAccount(a);
			if(result>=1){
				
				LOGGER.info("Account creation initiated successfully !!");
				request.setAttribute("message", "Account creation initiated successfully !!");
				request.getRequestDispatcher("success.jsp").forward(request,response);
			}
			else{
				LOGGER.error("Error Occured in account creation !!");
				request.setAttribute("message", " Oops, Error Occured in account creation !!");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}

		}
		else if(action.equals("delete")){

			int id = Integer.parseInt(request.getParameter("accid"));
			Account a = service.retrieveAccount(id);
			if(a!=null){
				
				request.setAttribute("acct", a);
				RequestDispatcher rd=null;
				rd=request.getRequestDispatcher("deletedata.jsp");
				rd.forward(request, response);
			}
			else{
				LOGGER.error("No details of account !!");
				request.setAttribute("message", " Oops, No details of account !!");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
		}
		
		else if(action.equals("view"))
		{
			  String IdType=request.getParameter("type");
		      int id = Integer.parseInt(request.getParameter("id"));
		      AccountDao accdao=new AccountDao();
		      ArrayList<Integer> idlist=new ArrayList<Integer>();
		      idlist=accdao.viewAccountId(id,IdType); 
		      request.setAttribute("idlist", idlist);
		      request.getRequestDispatcher("displayAccountInfo.jsp").forward(request, response);
		}
		else if(action.equals("acid"))
		{
				Account b;
				AccountDao accdao=new AccountDao();
				int unique_id = Integer.parseInt(request.getParameter("accountid"));
				b=accdao.viewAccount(unique_id);
				int custid=b.getCustid();
				int accid=b.getAccid();
				String atype=b.getAcctype();
				double bal=b.getBalance(); 
				request.setAttribute("custid", custid);
				request.setAttribute("accid",accid);
				request.setAttribute("atype",atype);
				request.setAttribute("bal", bal);
				request.getSession().setAttribute("account", b);
				request.getRequestDispatcher("viewAccountInfo.jsp").forward(request, response); 

		}
		
		else if(action.equals("accid"))
		{
			Account b=new Account();
			AccountDao accdao=new AccountDao();
		    int aid = Integer.parseInt(request.getParameter("aid"));
		    b=accdao.viewAccount(aid);
		    if(b!=null){
		    	  int custid=b.getCustid();
	              int accid=b.getAccid();
	              String atype=b.getAcctype();
	              double bal=b.getBalance(); 
	              request.setAttribute("custid", custid);
	              request.setAttribute("accid",accid);
	              request.setAttribute("atype",atype);
	              request.setAttribute("bal", bal);
	              request.getSession().setAttribute("account", b);
	              request.getRequestDispatcher("viewAccountInfo.jsp").forward(request, response);
		    }
		    else
		    {
		    	LOGGER.error("Entered account id is not available !!");
				request.setAttribute("message", " Oops, Entered account id is not available !!");
				request.getRequestDispatcher("error.jsp").forward(request,response);
		    }

		}
		else if(action.equals("viewaccounttable"))
		{
			  String IdType=request.getParameter("type");
		      int id = Integer.parseInt(request.getParameter("id"));
		      AccountDao accdao=new AccountDao();
		      ArrayList<Account> idlist;
		      idlist=accdao.viewAccountIdTable(id,IdType);
		      request.setAttribute("idlist", idlist);
		      request.getRequestDispatcher("deleteaccount.jsp").forward(request, response);
		}
		
		
	}

}
