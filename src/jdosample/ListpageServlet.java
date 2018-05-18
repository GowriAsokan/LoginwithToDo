package jdosample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

import javax.jdo.Query;


public class ListpageServlet extends HttpServlet {
	
	 public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
			
//			String listinput= req.getParameter("listform");
//			HttpSession session = req.getSession();
//			
//		PersistenceManager pm= PMF.get().getPersistenceManager();
//		
//		ListPojo list = new ListPojo();
//		list.setList(listinput);
//		list.setCustomerEmail(session.getAttribute("sessionEmail").toString());
//		
//		try{
//			pm.makePersistent(list);
//			
//			// System.out.println(listinput);
//		}finally{
//			pm.close();
//			
//		}
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		HttpSession session = req.getSession();
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String listinput = req.getParameter("listform");
		String customerEmail = session.getAttribute("sessionEmail").toString();
		System.out.println("inside create customer::" + listinput);
		
		System.out.println("inside  customer == " + customerEmail);
		try {
			Query q = pm.newQuery(ListPojo.class);
			q.setFilter("CustomerEmail =='" + customerEmail + "'");
			List<ListPojo> result = (List<ListPojo>) q.execute();

			if (!(result.isEmpty())) {
				ListPojo list = pm.getObjectById(ListPojo.class, result.get(0).getKey());
				list.setList(listinput);
				list.setCustomerEmail(customerEmail);
				out.print("success");
				pm.makePersistent(list);
			} else {
				System.out.println("TodoListJDO");
				ListPojo list = new ListPojo();
				list.setList(listinput);
				list.setCustomerEmail(customerEmail);
				out.print("success");
				pm.makePersistent(list);
				out.print("success");
				pm.makePersistent(list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} finally {
			pm.close();

		}

            
}
}