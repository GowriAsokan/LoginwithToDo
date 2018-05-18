package jdosample;

import java.io.IOException;
import java.util.List;
import java.io.PrintWriter;
import javax.jdo.PersistenceManager;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import javax.jdo.Query;

public class RetrieveListServlet extends HttpServlet {
	
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		 
		     HttpSession session = req.getSession();
		    String custEmail = session.getAttribute("sessionEmail").toString();
		    System.out.println("Email :" + custEmail);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			PrintWriter out = resp.getWriter();
			String output = "";
			
			Query q = pm.newQuery(ListPojo.class);
			q.setFilter("CustomerEmail =='" + custEmail + "'");
			
			List<ListPojo> result = (List<ListPojo>) q.execute();
			
			System.out.println("no of list :" + result.size());
			
			Gson gson = new Gson();
			output = gson.toJson(result);

			
			out.write(output);
		    
	 }

}
