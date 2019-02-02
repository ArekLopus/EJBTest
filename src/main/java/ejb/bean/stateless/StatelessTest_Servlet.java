package ejb.bean.stateless;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/statelessEJB")
public class StatelessTest_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	StatelessTest sl;
	
	@Resource
	ManagedExecutorService mes;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Stateless EJB</h3>");
		
		out.println("<br/>" + sl.testMethod());
		out.println("<br/>" + sl.testTransaction());
		
		Future<String> testAsync = sl.testAsync();
		while(!testAsync.isDone()) {
			try {
				out.println("<br/>" + testAsync.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		out.println("<br/><br/>Done");
	}
	
}
