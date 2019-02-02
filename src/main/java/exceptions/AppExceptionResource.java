package exceptions;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import exceptions.application.AppExceptionException;

@Path("appexc")
public class AppExceptionResource {
	
	@Inject
	AppExceptionTestEJB et;
	
	
	@GET
	public String testException() throws AppExceptionException {
		return et.appExcTest();
	}
	
	@Path("appruntime")
	@GET
	public String testAppRuntime(){
		return et.appExcTestRuntime();
	}
	
	@Path("runtime")
	@GET
	public String testRuntime(){
		return et.excRuntime();
	}
	
}
