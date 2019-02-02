package ejb.views;

import javax.ejb.Remote;

@Remote
public interface InterfaceRemoteWithAnnotation {
	public String testMethodRemote();
}
