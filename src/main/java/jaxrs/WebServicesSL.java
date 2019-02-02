package jaxrs;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("ejbs")
@Stateless
public class WebServicesSL {
	
	@Inject
	SecurityContext sc;
	
	@Context
	HttpServletRequest request;
	
	@Context
	HttpServletResponse response;
	
	@GET
	public String testEjb() {
		System.out.println("WebServicesSL.testEjb() called, object: " + this);
		return "WebServicesSL.testEjb() called, object: " + this;
	}
	
	
	@Path("login")
	@GET
	public String testLogin() {
		
		//UsernamePasswordCredential credentials = new UsernamePasswordCredential("aa", "aa");
		UsernamePasswordCredential credentials = new UsernamePasswordCredential("bb", "bb");
		sc.authenticate(request, response, AuthenticationParameters.withParams().credential(credentials));
		
		if(sc.getCallerPrincipal() == null) {
			return "Principal NULL";
		}
		String info = "Session: "+ request.getSession(false) 
				+ "<br/>User: " + sc.getCallerPrincipal().getName()
				+ "<br/>is caller in role 'admin' -> "+sc.isCallerInRole("admin")
				+ "<br/>is caller in role 'user' -> "+sc.isCallerInRole("user");
		
		return info;
	}
	
	@Path("logout")
	@GET
	public String testLogout() {
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		
		if(sc.getCallerPrincipal() == null) {
			return "Principal NULL";
		}
		String info = "Session: "+ request.getSession(false) 
				+ "<br/>User: " + sc.getCallerPrincipal().getName()
				+ "<br/>is caller in role 'admin' -> "+sc.isCallerInRole("admin")
				+ "<br/>is caller in role 'user' -> "+sc.isCallerInRole("user");
		
		return info;
	}
}
