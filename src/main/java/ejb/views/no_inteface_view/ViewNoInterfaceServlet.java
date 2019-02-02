package ejb.views.no_inteface_view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ejbNoInterfaceView")
public class ViewNoInterfaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	ViewNoInterfaceTestBean tb;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>EJB With No Interface View</h3>");
		out.println("No Interface view uses all public business methods of the bean class locally without the use of a separate business interface.");
		
		out.println("<br/><br/>ViewNoInterfaceTestBean: " + tb);
		
		out.println("<br/>" + tb.testMethod());
		
	}
	
}
