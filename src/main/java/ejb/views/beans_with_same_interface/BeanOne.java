package ejb.views.beans_with_same_interface;

import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.Stateless;

//-We are using here the default bean name		-> beanName="BeanOne"

@Stateless
@EJBs({
	@EJB(name = "b1r", beanInterface = InterfaceRemoteMulti.class, beanName="BeanOne"),
	@EJB(name = "b1l", beanInterface = InterfaceLocalMulti.class, beanName="BeanOne")
})
public class BeanOne implements InterfaceRemoteMulti , InterfaceLocalMulti {

	@Override
	public String testMethodLocal() {
		return "BeanOne's testMethodLocal()";
	}

	@Override
	public String testMethodRemote() {
		return "BeanOne's testMethodRemote()";
	}
	
	public String testMethodNoView() {
		return "BeanOne's testMethodNoView()";
	}
	
}