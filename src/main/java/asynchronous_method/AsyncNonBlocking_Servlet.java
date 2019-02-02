package asynchronous_method;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/asynchronousNonBlocking", asyncSupported = true)
public class AsyncNonBlocking_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	AsyncNonBlocking at;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		AsyncContext ac = request.startAsync(request, response);
		
		out.println("<h3>Asynchronous Non Blocking</h3>");
		
		out.println("Started..., thread: " + Thread.currentThread().getName());
        response.flushBuffer();

        CompletableFuture<String> cf = new CompletableFuture<>();
        at.asyncMethodCF(cf);

        cf.whenCompleteAsync((str, thr)->{
            try {
                
            	if(thr != null)
            		throw thr;
                
                out.println("<br/>" + str);
                out.println("<br/>Done, thread: " + Thread.currentThread().getName());
                response.flushBuffer();
                ac.complete();
                
            } catch (Throwable t2) {
            	
            }
        });
        
	}
	
}
