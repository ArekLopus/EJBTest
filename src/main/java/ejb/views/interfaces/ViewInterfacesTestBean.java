package ejb.views.interfaces;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import ejb.views.InterfaceLocal;
import ejb.views.InterfaceRemote;

@Stateless
@Local(InterfaceLocal.class)
@Remote(InterfaceRemote.class)
@LocalBean
public class ViewInterfacesTestBean implements InterfaceRemote, InterfaceLocal {

	@Override
	public String testMethodLocal() {
		return "ViewInterfacesTestBean's testMethodLocal()";
	}

	@Override
	public String testMethodRemote() {
		return "ViewInterfacesTestBean's testMethodRemote()";
	}
	
	public String testMethodNoView() {
		return "ViewInterfacesTestBean's testMethodNoView()";
	}
}