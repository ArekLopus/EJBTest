package environment_entries;

import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
public class EnvironmentEntriesTest {
	
	@Resource(name = "currencyEntry") 
    private String currency;
	
    //@Resource(name = "ratioEntry")  
    private Float ratio; 
	
    @PostConstruct
    public void init() {
		try {
			ratio = InitialContext.doLookup("java:comp/env/ratioEntry");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
    
	public void testMethod(PrintWriter out) {
		out.println("Currency: " + currency);
		out.println("<br/>Ratio: " + ratio);
	}

}
