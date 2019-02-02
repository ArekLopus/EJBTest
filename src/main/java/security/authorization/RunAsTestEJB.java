package security.authorization;

import java.io.PrintWriter;

import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;

@RolesAllowed({"admin", "user"})
@RunAs("user")
@Stateless
public class RunAsTestEJB {
	
	@Inject
	SecurityContext sc;
	
	@Inject
	AuthorizationTestEJB at;
	
	public void testMethodUser(PrintWriter out) {
		if(sc.getCallerPrincipal() == null) {
			out.println("<br/><br/>RunAsTestEJB, Principal NULL");
			System.out.println("RunAsTestEJB, Principal NULL");
		} else {
			out.println("<br/><br/>RunAsTestEJB, Principal: " + sc.getCallerPrincipal().getName());
			System.out.println("RunAsTestEJB, Principal: " + sc.getCallerPrincipal().getName());
			
			String info = "<br/>User: " + sc.getCallerPrincipal().getName()
				+ "<br/>is caller in role 'admin' -> "+sc.isCallerInRole("admin")
				+ "<br/>is caller in role 'user' -> "+sc.isCallerInRole("user");
			out.println(info);
			System.out.println(info);
		}
		
		at.testMethodUser(out);
	}
	

}
