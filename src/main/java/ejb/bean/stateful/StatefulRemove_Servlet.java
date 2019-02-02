package ejb.bean.stateful;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-A @Stateful EJB is not destroyed unless it is:
// • Removed, by invoking a @Remove method
// • Expired, by exceeding its @StatefulTimeout
// • Contextual, and it falls out of scope, e.g. @SessionScoped, @ConversationScoped

//http://localhost:8080/EJBTest/statefulRemove?type=remove
@WebServlet(value="/statefulRemove")
public class StatefulRemove_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//@Inject
	StatefulRemove sf;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Stateful EJB @Remove</h3>");
		
		String param = request.getParameter("type");
		if(param != null && param.equals("remove")) {	//StatefulTest's @Remove method called, StatefulTest's @PreDestroy
			sf.remove();
		}
		
		if(sf == null) {
			try {
				sf = InitialContext.doLookup("java:global/EJBTest/StatefulRemove");
				out.println("<br/>StatefulRemove was NULL!");
			} catch (NamingException e) {
				e.printStackTrace();
			}	
		}
		
		
		out.println("<br/>" + sf.testMethod());
		
		out.println("<br/><br/>Done");
	}
	
}
