package interceptors.around_invoke;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-Use the @AroundInvoke annotation to designate interceptor methods for managed object methods.
//-Only one around-invoke interceptor method per class is allowed.
//-Around-invoke interceptor methods have the following form:
//	@AroundInvoke
//	visibility Object method-name(InvocationContext) throws Exception { ... }
//-Around-invoke interceptor methods can have public, private, protected, or package-level access, and must not be declared static or final.
//-An around-invoke interceptor can call any component or resource that is callable by the target method on which it interposes,
// can have the same security and transaction context as the target method, and can run in the same JVM call stack as the target method.
//-Around-invoke interceptors can throw runtime exceptions and any exception allowed by the throws clause of the target method.
// They may catch and suppress exceptions, and then recover by calling the InvocationContext.proceed method.

//-Must be @Dependant if declared in a separate @Interceptor class
//-org.glassfish.deployment.common.DeploymentException: CDI definition failure:WELD-001476: 
// Interceptor [class cdi.interceptors.around_invoke.AroundInvokeInterceptor intercepts ] must be @Dependent

//	AroundInvokeTest's @PostConstruct - @AroundInvoke called - aroundInvokeTest() called - AroundInvokeTest's @PreDestroy

@WebServlet("/aroundInvoke")
public class AroundInvoke_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	AroundInvokeEJB ai;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>@AroundInvoke Interceptor Test</h3>");
		
		ai.aroundInvokeTest("FirstString", "SecondString");
		
	}
	
}
