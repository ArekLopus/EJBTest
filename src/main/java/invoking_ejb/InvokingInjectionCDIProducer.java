package invoking_ejb;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import ejb.views.InterfaceRemote;

@ApplicationScoped
public class InvokingInjectionCDIProducer {
	
	@Produces
	@EJB
	InterfaceRemote ir;
	
}
