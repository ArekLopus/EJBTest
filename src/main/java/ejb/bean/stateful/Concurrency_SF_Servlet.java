package ejb.bean.stateful;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-By default, clients are allowed to make concurrent calls to a SF session object and the container is required to serialize
// such concurrent requests. Note that the container never permits multi-threaded access to the actual SF session bean instance.

//@ConcurrencyManagement works only for @Singleton
//@Lock(LockType.READ) on method:	Container ->   @Lock is only permitted for singleton session beans]]

//	testMethod() 				- serialized

@WebServlet(value="/concurrencySF")
public class Concurrency_SF_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Concurrency_SF sc;
	
	@Resource
	ManagedExecutorService mes;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Concurrency - Stateful Session Bean</h3>");
		
		for(int i = 0; i < 3; i++) {
			mes.execute(() -> {
				
				sc.testMethod();
				
			});
		}
	}
	
}
