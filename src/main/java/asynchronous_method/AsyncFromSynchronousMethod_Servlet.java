package asynchronous_method;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-When the second method is invoked from the first method, the container doesn’t get a chance to proxy the call
// to the second method – it is called directly on the same instance and hence executes synchronously. 

//-We need to use SessionContext and use it to call the method as ctx.getBusinessObject(AsynchronousTest.class).asyncMethodVoid()
//-Now the first method is getting executed synchronously while the second method getting executed asynchronously.

@WebServlet("/asynchronousFromNonAsync")
public class AsyncFromSynchronousMethod_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	AsyncFromSynchronousMethodBean at;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<h3>Asynchronous From Non Async</h3>");
		
		at.asyncFromNonAsyncDoesNOTWork();
		
		at.asyncFromNonAsyncProperWay();
        
	}
	
}
