package exceptions.application;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class AppExceptionRuntime extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AppExceptionRuntime() {
		super();
	}
	
	public AppExceptionRuntime(String msg) {
		super(msg);
	}
    
}