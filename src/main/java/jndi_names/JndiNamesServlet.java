package jndi_names;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.views.InterfaceLocal;
import ejb.views.InterfaceRemote;
import ejb.views.interfaces.ViewInterfacesTestBean;

//java:<scope>[/<app-name>]/<module-name>/<bean-name>[!<fully-qualified-interface-name>]

@WebServlet("/ejbJndiNames")
public class JndiNamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ViewInterfacesTestBean lb;
	InterfaceLocal il;
	InterfaceRemote ir;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>EJB JNDI names</h3>");
		
		out.println("<br/>Since EJB 3.1, JNDI names have been specified so the code could be portable.");
		out.println("<br/>Each time a SB with its interfaces is deployed to the container, each bean / interface is auto bound to a portable JNDI name.");
		out.println("<br/><br/>&emsp;&emsp;java: scope [ / app-name ] / module-name / bean-name [!fully-qualified-interface-name]");
		
			
		
		out.println("<br/><br/>java:global/EJBTest/ViewInterfacesTestBean!ejb.views.interfaces.ViewInterfacesTestBean");
		out.println("<br/>java:global/EJBTest/ViewInterfacesTestBean!ejb.views.InterfaceLocal");
		out.println("<br/>java:global/EJBTest/ViewInterfacesTestBean!ejb.views.InterfaceRemote");
		
		
		try {
			
			lb = InitialContext.doLookup("java:global/EJBTest/ViewInterfacesTestBean!ejb.views.interfaces.ViewInterfacesTestBean");
			il = InitialContext.doLookup("java:global/EJBTest/ViewInterfacesTestBean!ejb.views.InterfaceLocal");
			ir = InitialContext.doLookup("java:global/EJBTest/ViewInterfacesTestBean!ejb.views.InterfaceRemote");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		out.println("<br/><br/><br/>ViewInterfacesTestBean: " + lb);
		out.println("<br/>" + lb.testMethodLocal());
		out.println("<br/>" + lb.testMethodRemote());
		out.println("<br/>" + lb.testMethodNoView());
		
		out.println("<br/><br/>InterfaceLocal: " + il);
		out.println("<br/>" + il.testMethodLocal());
		
		out.println("<br/><br/>InterfaceRemote: " + ir);
		out.println("<br/>" + ir.testMethodRemote());
		
	}
	
}
