package jdosample;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;
@SuppressWarnings("serial")

	public class SignupServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String signinemail = req.getParameter("email");
		String password    = req.getParameter("password");
		String username    = req.getParameter("username");
	
		 HttpSession session = req.getSession();
		 
	PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(SigninPojo.class);
		q.setFilter("email=='"+signinemail+"'"); 
		q.setFilter("password=='"+password+"'");
  try{   
		List<SigninPojo> results= (List<SigninPojo>) q.execute();
		if(!results.isEmpty())
		{
			String msg= "<p style=\"color: red;\">You have already registered</p>";
			req.setAttribute("name", msg);
        	RequestDispatcher dispatcher = req.getRequestDispatcher("signup page.jsp");
        	dispatcher.forward( req, resp );
			System.out.println("You have registered already!");
		
		}
	else{
		SigninPojo l = new SigninPojo();
		l.setEmail(signinemail);
		l.setPassword(password);
		l.setUsername(username);
		pm.makePersistent(l);
		
		session.setAttribute("sessionEmail", signinemail);
		RequestDispatcher dispatch = req.getRequestDispatcher("todolist.jsp");
		dispatch.forward(req, resp);
		System.out.println("New user!");
		}	
	}finally{
		pm.close();
		}
	}
}
