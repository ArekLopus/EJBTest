package ejb.bean.stateless;

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

//-Container Uses a new bean per request 

//@ConcurrencyManagement works only for @Singleton
//@Lock(LockType.READ) on method:	Container ->   @Lock is only permitted for singleton session beans]]

//-@Stateless uses a different bean for each call (so it is 'serialized').
//	testMethod() 				- serialized
//	testMethodWithLockRead() 	- serialized
//	testMethodWithLockWrite() 	- serialized

@WebServlet(value="/concurrencySL")
public class Concurrency_SL_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Concurrency_SL sc;
	
	@Resource
	ManagedExecutorService mes;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Concurrency - Stateless Session Bean</h3>");
		
		for(int i = 0; i < 3; i++) {
			mes.execute(() -> {
				
				sc.testMethod();
				
			});
		}
	}
	
}
