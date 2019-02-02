package ejb.bean.stateful;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/statefulEJB")
public class StatefulTest_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	StatefulTestBean sf;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Stateful EJB</h3>");
		
		out.println("<br/>" + sf.testMethod());
		out.println("<br/>" + sf.testTransaction());
		
		out.println("<br/><br/>Done");
	}
	
}
