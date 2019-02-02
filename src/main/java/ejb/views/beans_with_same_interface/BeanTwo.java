package ejb.views.beans_with_same_interface;

import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.Stateless;

//-We are using here a custom name ( @Stateless(name="bean2") )		-> beanName="bean2"

@Stateless(name="bean2")
@EJBs({
	@EJB(name = "b2r", beanInterface = InterfaceRemoteMulti.class, beanName="bean2"),
	@EJB(name = "b2l", beanInterface = InterfaceLocalMulti.class, beanName="bean2")
})

public class BeanTwo implements InterfaceRemoteMulti , InterfaceLocalMulti {
	
	@Override
	public String testMethodLocal() {
		return "BeanTwo's testMethodLocal()";
	}

	@Override
	public String testMethodRemote() {
		return "BeanTwo's testMethodRemote()";
	}
	
	public String testMethodNoView() {
		return "BeanTwo's testMethodNoView()";
	}
	
}

