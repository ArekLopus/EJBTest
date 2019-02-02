package ejb.views.local_bean;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import ejb.views.InterfaceLocalBean;
import ejb.views.InterfaceRemoteBean;

@Stateless
@Remote(InterfaceRemoteBean.class)
@Local(InterfaceLocalBean.class)
@LocalBean
public class ViewLocalTestBean implements InterfaceRemoteBean, InterfaceLocalBean {

	@Override
	public String testMethodLocal() {
		return "ViewLocalTestBean's testMethodLocal()";
	}

	@Override
	public String testMethodRemote() {
		return "ViewLocalTestBean's testMethodRemote()";
	}
	
	public String testMethodNoView() {
		return "ViewLocalTestBean's testMethodNoView()";
	}
}