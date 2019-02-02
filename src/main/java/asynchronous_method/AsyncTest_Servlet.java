package asynchronous_method;

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

@WebServlet("/asynchronous")
public class AsyncTest_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	AsyncTestBean at;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>@Asynchronous Methods Test</h3>");
		
		at.asyncMethodVoid();
		
		Future<String> asyncMethodFuture = at.asyncMethodFuture();
		while(!asyncMethodFuture.isDone()) {
			try {
				out.println("Done: " + asyncMethodFuture.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}			
		}
		
	}
	
}
