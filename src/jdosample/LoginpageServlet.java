
package jdosample;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.jdo.Query;

@SuppressWarnings("serial")
  public class LoginpageServlet extends HttpServlet {
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String loginemail = req.getParameter("emailaddress");
		String loginpassword = req. getParameter("loginpassword");
	
	 HttpSession session = req.getSession();
	 
	PersistenceManager pm= PMF.get().getPersistenceManager();
	
	Query q = pm.newQuery(SigninPojo.class);
		q.setFilter("email=='"+loginemail+"'"); 
		q.setFilter("password=='"+loginpassword+"'");
			try{   
				List<SigninPojo> results= (List<SigninPojo>) q.execute();
					
				if(!results.isEmpty())
						{
					 	session.setAttribute("sessionEmail", loginemail);
							RequestDispatcher dispatcher = req.getRequestDispatcher("todolist.jsp");
							dispatcher.forward( req, resp );
							System.out.println("successfully loggedIn");
							
						}
				else
				{
					String loginmsg="<p style= \"color: red;\"> You have not registered yet!</p>";
					req.setAttribute("errormsg", loginmsg);
					//session.setAttribute("error", "login_invalid");
						RequestDispatcher dispatcher = req.getRequestDispatcher("loginpage.jsp");
						dispatcher.forward(req, resp);
				}
			}
		
			finally
			{
				pm.close();
			}
  		}
	}
