package concurency;

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

import concurency.test.TestBeanConcurrentApp;
import ejb.bean.singletons.Concurrency_Sin_CMC;
import ejb.bean.singletons.Concurrency_Sin_BMC;
import ejb.bean.stateful.Concurrency_SF;
import ejb.bean.stateless.Concurrency_SL;

//-CDI don't have concurrency management.

//-@Singleton CMC - serialized
//-@Singleton CMC with @Lock(LockType.READ) on method - NOT serialized () (turns off @Lock(LockType.WRITE))
//-@Singleton CMC with @Lock(LockType.WRITE) on method - serialized

//-@Singleton BMC - NOT serialized (@ConcurrencyManagement works)
//-@Singleton BMC with @Lock(LockType.READ) on method - NOT serialized
//!!-@Singleton BMC with @Lock(LockType.WRITE) on method - NOT serialized - @Lock DOES NOT WORK! need to protect it manually!!!

//-@Stateless uses a different bean for each call (so it is 'serialized').

//-@Stateful - serialized (1 object used for all calls - concurency.ConcurrentBeanEJB@1256f5f)

//http://localhost:8080/CDITest/concurrency?type=ejb-sin-test
//http://localhost:8080/CDITest/concurrency?type=ejb-sin-read
//http://localhost:8080/CDITest/concurrency?type=ejb-sin-write

//http://localhost:8080/CDITest/concurrency?type=ejb-sin-bmc-test
//http://localhost:8080/CDITest/concurrency?type=ejb-sin-bmc-read
//http://localhost:8080/CDITest/concurrency?type=ejb-sin-bmc-write
@WebServlet(value="/concurrency")
public class ConcurrencyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Concurrency_Sin_CMC cbes;
	
	@Inject
	Concurrency_Sin_BMC cbesb;
	
	@Inject
	Concurrency_SL cbesl;
	
	@Inject
	Concurrency_SF cbesf;
	
	@Inject
	TestBeanConcurrentApp cb;
	
	@Resource
	ManagedExecutorService mes;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Concurrency</h3>");
		
		String param = request.getParameter("type");
		if(param == null) {
			out.println("<br/><br/>Pick a type, ejb singleton: ?type=ejb-sin-test ?type=ejb-sin-read ?type=ejb-sin-write");
			out.println("<br/>Pick a type, ejb singleton bmc: ?type=ejb-sin-bmc-test ?type=ejb-sin-bmc-read ?type=ejb-sin-bmc-write");
			out.println("<br/>Pick a type, ejb stateless: ?type=ejb-sl-test");
			out.println("<br/>Pick a type, ejb stateful: ?type=ejb-sf-test ?type=ejb-sf-read ?type=ejb-sf-write");
			return;
		}
		
		switch (param) {
			
			case "ejb-sin-test": testConcurrency(cbes::testMethod); break;
			case "ejb-sin-read": testConcurrency(cbes::testMethodWithLockRead); break;
			case "ejb-sin-write": testConcurrency(cbes::testMethodWithLockWrite); break;
			
			case "ejb-sin-bmc-test": testConcurrency(cbesb::testMethod); break;
			case "ejb-sin-bmc-read": testConcurrency(cbesb::testMethodWithLockRead); break;
			case "ejb-sin-bmc-write": testConcurrency(cbesb::testMethodWithLockWrite); break;
			
			case "ejb-sl-test": testConcurrency(cbesl::testMethod); break;
		
			case "ejb-sf-test": testConcurrency(cbesf::testMethod); break;
			
			case "cdi-test": testConcurrency(cb::testMethod); break;
			case "cdi-sync": testConcurrency(cb::testMethodSynchronized); break;
			case "cdi-lock": testConcurrency(cb::testMethodLocked); break;
			
			default: break;
		}
		
	}
	
	private void testConcurrency(Runnable r) {	//Runnable only to use type: () -> {} 
		System.out.println("------------------------------------");
		
		for(int i = 0; i < 3; i++) {
			mes.execute(() -> {
				r.run();
			});
		}
		
	}
	
}
