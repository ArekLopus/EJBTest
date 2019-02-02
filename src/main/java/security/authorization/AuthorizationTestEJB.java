package security.authorization;

import java.io.PrintWriter;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;

@RolesAllowed({ "admin", "user", "foo" })
@Stateless
public class AuthorizationTestEJB {
	
	@Inject
	SecurityContext sc;
	
	@RolesAllowed("user")
	public void testMethodUser() {
		System.out.println("AuthorizationTestEJB's testMethodUser() called");
		if(sc.getCallerPrincipal() == null) {
			System.out.println("Principal NULL");
		} else {
			System.out.println("Principal: " + sc.getCallerPrincipal().getName());
			
			String info = "User: " + sc.getCallerPrincipal().getName()
				+ "\nis caller in role 'admin' -> "+sc.isCallerInRole("admin")
				+ "\nis caller in role 'user' -> "+sc.isCallerInRole("user");
			System.out.println(info);
		}
	}
	@RolesAllowed("user")
	public void testMethodUser(PrintWriter out) {
		System.out.println("AuthorizationTestEJB's testMethodUser() called");
		if(sc.getCallerPrincipal() == null) {
			out.println("<br/><br/>AuthorizationTestEJB, Principal NULL");
		} else {
			out.println("<br/><br/>AuthorizationTestEJB, Principal: " + sc.getCallerPrincipal().getName());
			
			String info = "<br/>User: " + sc.getCallerPrincipal().getName()
				+ "<br/>is caller in role 'admin' -> "+sc.isCallerInRole("admin")
				+ "<br/>is caller in role 'user' -> "+sc.isCallerInRole("user");
			out.println(info);
		}
	}
	
	@RolesAllowed("admin")
	public void testMethodAdmin() {
		System.out.println("AuthorizationTestEJB's testMethod() called");
	}

	@PermitAll
	public void testPermitAll() {
		System.out.println("AuthorizationTestEJB's testPermitAll() called");
	}

	@DenyAll
	public void testDenyAll() {
		System.out.println("AuthorizationTestEJB's testDenyAll() called");
	}
}
