package ejb.bean.singletons;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//	Handling Errors in a Singleton Session Bean
//-If a singleton session bean encounters an error when initialized by the EJB container, that singleton instance will be destroyed.

//-Unlike other EBs, once a singleton instance is initialized, it is not destroyed if the singleton's business or lifecycle
// methods cause system exceptions. This ensures that the same singleton instance is used throughout the app lifecycle.

@WebServlet(value="/exceptionsSingleton")
public class Exceptions_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Exceptions ex;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Singleton - Exceptions</h3>");
		
		out.println("If a singleton session bean encounters an error when initialized by the EJB container, that singleton instance will be destroyed.");
		out.println("<br/>Unlike other EBs, once a singleton instance is initialized, it is not destroyed if the singleton's business or lifecycle methods cause system exceptions.");
		out.println("<br/>This ensures that the same singleton instance is used throughout the app lifecycle.");
		out.println("<br/>");
		out.println("<br/>Even if exception is thrown, the same isntance is used:");
		ex.testMethod(out);
		
	}
}
