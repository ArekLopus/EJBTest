package ejb.bean.stateful;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Asynchronous methods doesnt work on Payara!!!
//	java.lang.NullPointerException

@WebServlet(value="/ejbStatefulAsyncMethod")
public class StatefulAsyncMethodTest_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	StatefulAsyncMethodTestBean sf;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Stateful EJB - Asynchronous Methods</h3>");
		
		//@Asynchronous methods doesnt work on Payara
		sf.testAsyncVoid();
		
		Future<String> testAsync = sf.testAsyncFuture();
		while(!testAsync.isDone()) {
			try {
				out.println("<br/><br/>" + testAsync.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		out.println("<br/><br/>Done");
	}
	
}
