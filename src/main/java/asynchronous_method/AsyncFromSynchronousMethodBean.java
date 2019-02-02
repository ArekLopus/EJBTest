package asynchronous_method;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
public class AsyncFromSynchronousMethodBean {
	
	@Resource
	private SessionContext ctx;
	
	@Asynchronous
	public void asyncMethodVoid() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("@Asynchronous method void, thread: " + Thread.currentThread().getName());
	}
	
	
	public void asyncFromNonAsyncDoesNOTWork() {
		System.out.println("asyncFromNonAsyncDoesNOTWork(), thread: " + Thread.currentThread().getName());
		asyncMethodVoid();
	}
	
	public void asyncFromNonAsyncProperWay() {
		System.out.println("asyncFromNonAsyncProper(), thread: " + Thread.currentThread().getName());
		//Invoked using the SessionContext INSTEAD OF DIRECTLY
        ctx.getBusinessObject(AsyncFromSynchronousMethodBean.class).asyncMethodVoid();
	}
	
}
