package ejb.views;

import javax.ejb.Local;

@Local
public interface InterfaceLocalWithAnnotation {
	public String testMethodLocal();
}
