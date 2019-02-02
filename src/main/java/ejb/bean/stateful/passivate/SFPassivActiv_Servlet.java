package ejb.bean.stateful.passivate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-To lower number of pooled beans to see passivation -> sun-ejb-jar.xml
// or the same settings server-config/EJB Container in Console Admin	Max Cache Size:5, Cache Idle Timeout: 60

//<?xml version="1.0" encoding="UTF-8"?>
//<!DOCTYPE sun-ejb-jar PUBLIC "-//Sun Microsystems, Inc.//DTD Application Server 9.0 EJB 3.0//EN" 
//  "http://www.sun.com/software/appserver/dtds/sun-ejb-jar_3_0-0.dtd">
//<sun-ejb-jar>
//<enterprise-beans>
// <ejb>
//   <ejb-name>SFPassivActiv</ejb-name>
//   <bean-cache>
//     <max-cache-size>5</max-cache-size>
//     <cache-idle-timeout-in-seconds>10</cache-idle-timeout-in-seconds>
//   </bean-cache>
// </ejb>
//</enterprise-beans>
//</sun-ejb-jar>

//http://localhost:8080/CDITest/passivate
//http://localhost:8080/CDITest/passivate?bean=12
@WebServlet("/passivate")
public class SFPassivActiv_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int counter = 50;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Statefull Session Bean Passivate Activate</h3>");
		
		String beanNr = request.getParameter("bean");
    	if(beanNr != null) {
    		SFPassivActiv tsf = (SFPassivActiv) request.getSession().getAttribute("bean" + beanNr);
    		if(tsf == null) {
    			out.println("Deactivate Beans First!");
    			return;
    		}
        	tsf.simpleCall();
        	return;
    	}
		
    	try{
    		InitialContext ic = new InitialContext();
    		for(int i = 0; i < counter; i++){
    			SFPassivActiv tsf = (SFPassivActiv) ic.lookup("java:global/EJBTest/SFPassivActiv");
    			request.getSession().setAttribute("bean" + i, tsf);
    		}
    		
    		Thread.sleep(1000);
    		
	    } catch(Exception e){
	    	throw new ServletException(e);
	    }
    	
    	SFPassivActiv tsf = (SFPassivActiv) request.getSession().getAttribute("bean" + 11);
    	tsf.checkTrans();
	}
		
}
