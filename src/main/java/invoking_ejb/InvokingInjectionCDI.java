package invoking_ejb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.views.InterfaceLocal;
import ejb.views.InterfaceRemote;
import ejb.views.interfaces.annotated.ViewInterfacesAnnotatedTestBean;

//-For remote EJBs, as you just saw, you might need to use a JNDI string to look it up.
//The @Inject cannot have a String parameter, so in this case, you need to produce the remote EJB to be able to inject it.
//-if no @Produces, Exception:
//	org.glassfish.deployment.common.DeploymentException: CDI deployment failure:WELD-001408:
//	Unsatisfied dependencies for type InterfaceRemote with qualifiers @Default

@WebServlet("/invokingCDI")
public class InvokingInjectionCDI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Produced by InvokingInjectionCDIProducer
	@Inject
	InterfaceRemote ir;
	
	@Inject
	InterfaceLocal il;
	
	@Inject
	ViewInterfacesAnnotatedTestBean lv;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>EJB Invoking with @Inject Injection</h3>");
		
		out.println("InterfaceRemote: " + ir.testMethodRemote());
		out.println("<br/>InterfaceLocal: " + il.testMethodLocal());
		out.println("<br/>Local View: " + lv.testMethodNoView());
		
	}
	
}
