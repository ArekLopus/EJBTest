package embedded_server;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.Test;

import ejb.bean.stateless.StatelessTest;

//-Embeddable EJB containers only work with the EJB Lite subset API (no MDBs, remote calls, etc.), meaning it has
// the same capabilities as an EJB Lite container (not a full EJB container).
// • Local session beans
// • Transactions
// • Security
// • Interceptors
// • Deployment descriptor

//-Uses test scope 
//<dependency>
//    <groupId>fish.payara.extras</groupId>
//    <artifactId>payara-embedded-all</artifactId>
//    <version>5.184</version>
//    <scope>test</scope>
//</dependency>

public class EmbeddedServerTestJunit {
    
	@Test
	public void test() throws NamingException {

        // Sets the container classpath
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));

        // Creates an Embedded Container and get the JNDI context
        try (EJBContainer ec = EJBContainer.createEJBContainer(properties)) {

            Context ctx = ec.getContext();

            // Looks up the EJB with the no-interface view
            StatelessTest test = (StatelessTest) ctx.lookup("java:global/classes/StatelessTest");
            
            System.out.println(test.testMethod());
            
            try {
            	while(true) {
            		Thread.sleep(2000);
            	}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
}