package com.Controller;

import com.Bean.Employee;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.Dao.LoginDao;
import org.apache.log4j.Logger;

@WebServlet(urlPatterns="/LoginServlet")
public class LoginServlet extends HttpServlet {

	static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
    }
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
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
	
	Object param=request.getSession().getAttribute("type");
    if(param!=null){
    	String type=(String)param;
    	if(type.equals("cae"))
    		request.getRequestDispatcher("caehome.jsp").forward(request, response);
    	else if(type.equals("cashier_teller"))
    		request.getRequestDispatcher("cashier_tellerhome.jsp").forward(request,response);
    	else
    		response.sendRedirect("index.jsp");
    }
    else
    {
    	response.sendRedirect("index.jsp");
    }
	
}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	HttpSession session=request.getSession();
	response.setContentType("text/html");  
    String name=request.getParameter("uname");  
    String password=request.getParameter("pwd");  
    Employee emp=new Employee(name, password);
    LoginDao login=new LoginDao();
    int status=login.authenticateUser(emp);  
    request.setAttribute("status", status);
    //HttpSession session=request.getSession();
    session.setAttribute("sessionId", session.getId());
    if(status==0 || status==-1){
    	request.getRequestDispatcher("index.jsp").forward(request,response);
    }
    else{
    	login.updateTimestamp(emp);
    	if(status==1){
    		LOGGER.info("Cae logged in with userid="+name);
    		session.setAttribute("id", name);
    		session.setAttribute("type", "cae");
    		//request.getRequestDispatcher("caehome.jsp").forward(request,response);
    		response.sendRedirect("LoginServlet?type=cae");
    	}
    	else if(status==2){
    		LOGGER.info("Cashier logged in with userid="+name);
    		session.setAttribute("id", name);
    		session.setAttribute("type", "cashier_teller");
    		//request.getRequestDispatcher("cashier_tellerhome.jsp").forward(request,response);
    		response.sendRedirect("LoginServlet?type=cashier_teller");
    	}
    }
}
}