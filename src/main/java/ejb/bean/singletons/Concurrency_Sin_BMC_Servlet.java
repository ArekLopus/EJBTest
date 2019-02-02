package ejb.bean.singletons;

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

//	testMethod() 				- NOT serialized (@ConcurrencyManagement works)
//	testMethodWithLockRead() 	- NOT serialized
//! testMethodWithLockWrite() 	- NOT serialized - @Lock DOES NOT WORK! need to protect it manually!!!
//	testMethodSynchronized()	- serialized!

@WebServlet(value="/concurrencySingletonBMC")
public class Concurrency_Sin_BMC_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Concurrency_Sin_BMC sb;
	
	@Resource
	ManagedExecutorService mes;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Concurrency - Singleton BMC</h3>");
		
		for(int i = 0; i < 3; i++) {
			mes.execute(() -> {
				
//				sb.testMethod();
//				sb.testMethodWithLockRead();
//				sb.testMethodWithLockWrite();
				sb.testMethodSynchronized();
				
			});
		}
	}
}
