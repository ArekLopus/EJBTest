package exceptions.application;

import javax.ejb.ApplicationException;

@ApplicationException
public class AppExceptionException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public AppExceptionException() {
		super();
	}
	
	public AppExceptionException(String msg) {
		super(msg);
	}
    
}