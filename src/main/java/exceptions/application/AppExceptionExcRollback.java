package exceptions.application;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class AppExceptionExcRollback extends Exception {
	private static final long serialVersionUID = 1L;
	
	public AppExceptionExcRollback() {
		super();
	}
	
	public AppExceptionExcRollback(String msg) {
		super(msg);
	}
    
}