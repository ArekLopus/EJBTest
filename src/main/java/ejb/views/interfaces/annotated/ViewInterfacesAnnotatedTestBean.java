package ejb.views.interfaces.annotated;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ejb.views.InterfaceLocalWithAnnotation;
import ejb.views.InterfaceRemoteWithAnnotation;

@Stateless
@LocalBean
public class ViewInterfacesAnnotatedTestBean implements InterfaceRemoteWithAnnotation, InterfaceLocalWithAnnotation {

	@Override
	public String testMethodLocal() {
		return "ViewInterfacesAnnotatedTestBean's testMethodLocal()";
	}

	@Override
	public String testMethodRemote() {
		return "ViewInterfacesAnnotatedTestBean's testMethodRemote()";
	}
	
	public String testMethodNoView() {
		return "ViewInterfacesAnnotatedTestBean's testMethodNoView()";
	}
}