package ejb.bean.singletons;

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

@WebServlet("/ejbSingletonAsyncMethod")
public class SingletonAsyncMethodTest_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	SingletonAsyncMethodTestBean sf;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Singleton EJB - Asynchronous Methods</h3>");
		
		
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
