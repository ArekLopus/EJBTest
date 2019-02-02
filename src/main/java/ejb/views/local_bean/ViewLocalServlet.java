package ejb.views.local_bean;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.views.InterfaceLocalBean;
import ejb.views.InterfaceRemoteBean;

@WebServlet("/ejbLocalView")
public class ViewLocalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	ViewLocalTestBean lb;
	
	@EJB
	InterfaceLocalBean il;
	
	@EJB
	InterfaceRemoteBean ir;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>EJB With View Local ( @LocalBean )</h3>");
		out.println("If the bean exposes at least one interface (local or remote) it automatically loses the no-interface view.");
		out.println("<br/>It then needs to explicitly specify that it exposes a no-interface view by using the @LocalBean annotation on the bean class.");
		
		out.println("<br/><br/><br/>ViewLocalTestBean: " + lb);
		
		out.println("<br/>" + lb.testMethodLocal());
		out.println("<br/>" + lb.testMethodRemote());
		out.println("<br/>" + lb.testMethodNoView());
		
		out.println("<br/><br/>InterfaceLocal: " + il);
		out.println("<br/>" + il.testMethodLocal());
		
		out.println("<br/><br/>InterfaceLocal: " + ir);
		out.println("<br/>" + ir.testMethodRemote());
		
		//out.println("<br/><br/>InterfaceRemote cant be injected: Exception");
		
		//@Inject InterfaceRemote ir;
		//Exception during lifecycle processing
		//  org.glassfish.deployment.common.DeploymentException: CDI deployment failure:WELD-001408:
		//  Unsatisfied dependencies for type InterfaceRemote with qualifiers @Default
		//  at injection point [BackedAnnotatedField] @Inject ejb.views.Servlet.ir
		
	}
	
}
