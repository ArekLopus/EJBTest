package session_context;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sessionContext")
public class SessionContextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	SessionContextTest sct;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>SessionContext</h3>");
		
		sct.testMethod(out);
		
	}
	
}
