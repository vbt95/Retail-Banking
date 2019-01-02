package com.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Bean.Account;
import com.Bean.Transaction;
import com.Service.TransactionService;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(TransactionServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String action=request.getParameter("action");
		PrintWriter out = response.getWriter();
		if(action.equals("deposit")){
			TransactionService service=new TransactionService();
			Account account=(Account)request.getSession().getAttribute("account");
			double amount=Double.parseDouble(request.getParameter("amount"));
			ArrayList status=service.deposit(account,amount);
			if((Integer)status.get(0)==1){
				LOGGER.info("Deposited amount successfully !!");
				request.setAttribute("status", status);
				request.getRequestDispatcher("transactionsuccess.jsp").forward(request,response);
			}
			else{
				LOGGER.error("Transaction failed !!");
				request.setAttribute("message", "Oops!!Transaction failed !!");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
        	
		}
		else if(action.equals("withdraw")){
			TransactionService service=new TransactionService();
			Account account=(Account)request.getSession().getAttribute("account");
			double amount=Double.parseDouble(request.getParameter("amount"));
			ArrayList status=service.withdraw(account,amount);
			if((Integer)status.get(0)==1){
				LOGGER.info("Withdarawed amount successfully !!");
				request.setAttribute("status", status);
				request.getRequestDispatcher("transactionsuccess.jsp").forward(request,response);;
			}
			else if((Integer)status.get(0)==0){
				LOGGER.error("Internal Error !! Transaction failed!!");
				request.setAttribute("message", "Oops!! Internal Error !! Transaction failed !!");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
			else if((Integer)status.get(0)==-1){
				LOGGER.error("Not Enough Balance !! Transaction failed!!");
				request.setAttribute("message", "Oops!! Not Enough Balance !! Transaction failed !!");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
		}
		else if(action.equals("transfer")){
			TransactionService service=new TransactionService();
			int amount=Integer.parseInt(request.getParameter("amount"));
			int source=Integer.parseInt(request.getParameter("source"));
			int target=Integer.parseInt(request.getParameter("target"));
			if(source==target){
				LOGGER.error("Transfer to same account not possible !!");
				request.setAttribute("message", "Are you kidding me !!Source and Target are same!!");
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
			else{
				ArrayList ret=service.transfer(amount,source,target);
				if(ret.get(0).equals("Amount transfer completed successfully"))
				{
					LOGGER.info("Transfered amount successfully !!");
					request.setAttribute("details", ret);
					request.getRequestDispatcher("transferCompleted.jsp").forward(request, response);
				}
				else{
					out.write(ret.get(0).toString());
					LOGGER.error(ret.get(0).toString());
					request.setAttribute("message", ret.get(0).toString());
					request.getRequestDispatcher("error.jsp").forward(request,response);
				}
					
			}
			
		}
		else if(action.equals("getStatement")){
			TransactionService service=new TransactionService();
			String type=request.getParameter("type");
			int accountId=Integer.parseInt(request.getParameter("accountId"));
			if(type.equals("mini")){
				int days=Integer.parseInt(request.getParameter("days"));
				ArrayList<Transaction> list= service.getStatement(accountId, days);
				request.setAttribute("list", list);
				request.getRequestDispatcher("viewStatement.jsp").forward(request, response);
			}
			else if(type.equals("date")){
				String start=request.getParameter("start");
				try{
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date startDate= formatter.parse(start); 
					String end=request.getParameter("end");
					Date endDate= formatter.parse(end);
			
							//System.out.println(start);
						//System.out.println(startDate);
					//System.out.println(endDate);
			
					//System.out.println(sqlStartDate);
					//System.out.println(sqlEndDate);
					ArrayList<Transaction> list= service.getStatement(accountId, startDate,endDate);
					request.setAttribute("list", list);
					LOGGER.info("Statement viewed successfully !!");
					request.getRequestDispatcher("viewStatement.jsp").forward(request, response);
				}
				catch(Exception e){
					LOGGER.error(e.getStackTrace());
					e.printStackTrace();
				}
			}
		}
	}

}
