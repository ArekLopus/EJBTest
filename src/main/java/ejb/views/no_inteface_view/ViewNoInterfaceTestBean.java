package ejb.views.no_inteface_view;

import javax.ejb.Stateless;

//-The no-interface view is a variation of the local view that exposes all public business methods of the bean class
// locally without the use of a separate business interface.

@Stateless
public class ViewNoInterfaceTestBean {

	public String testMethod() {
		return "ViewNoInterfaceTestBean's testMethod()";
	}

}
