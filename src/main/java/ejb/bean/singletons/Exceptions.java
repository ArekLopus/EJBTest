package ejb.bean.singletons;

import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Singleton;

@Singleton
public class Exceptions {
	
	
	public void testMethod(PrintWriter out) {
		boolean check = ThreadLocalRandom.current().nextBoolean();
		try {
			if(check) {
				throw new IllegalStateException("There was an error!, " + this);
			} else {
				out.println("<br/>No Exception: " + this);
			}
		} catch (Exception e) {
			out.println("<br/>Exception: " + e.getMessage());
		}
	}
	
}
