package ejb.bean.stateless;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/stateless")
public class StatelessTest_Resource {
	
	@Inject
	StatelessTest sl;
	
	@Resource
	ManagedExecutorService mes;
	
	@GET
	@Path("inst")
	public String test() {
		return "Instance: " + sl.testMethod();
	}

	@GET
	@Path("trans")
	public String testTrans() {
		return "<br/>" + sl.testTransaction();
	}

	@GET
	@Path("async")
	public String testAsync() {
		Future<String> testAsync = sl.testAsync();
		while(!testAsync.isDone()) {
			try {
				return "<br/>" + testAsync.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return "Exception: " + e.getMessage();
			}
		}
		return "Exception " ;
	}

}
