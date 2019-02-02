package timers;

import java.io.PrintWriter;
import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;
import javax.transaction.TransactionSynchronizationRegistry;

@Stateless
public class TransactionsAndTimers {
	
	private int counter = 1;
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	@Resource
	TimerService ts;

	public void intervalTimer() { 
	    ts.createIntervalTimer(1000, 5000, new TimerConfig("Interval Timer", false));
 	}
	
	@Timeout   	
	//public void timeout() {
	public void timeout(Timer timer) {	// If Timer passed AND USED @AroundTimeout doesnt work properly (cant cancel)
	    //System.out.println("TimerBean: timeout occurred, info: " + timer.getInfo() + ", trans: " + tsr.getTransactionKey());
		System.out.println("TimerBean: timeout occurred, trans: " + tsr.getTransactionKey());
		counter++;
	}
	
	
	@AroundTimeout
    private Object timeoutInterceptorMethod(InvocationContext ctx) throws Exception {
    	System.out.println("AroundTimeout called");
    	
    	if(counter == 3) {
    		Object timer = ctx.getTimer();
        	//if(timer != null) {
    		if(timer instanceof Timer) {
        		System.out.println("timer not null");
        		Timer tr = (Timer) timer;
        		System.out.println("Timer is cancelled after 3rd round.");
        		counter = 1;
    			tr.cancel();	
        	}
		}
    	
    	return ctx.proceed();
    }
	
	
	public void killEmAll(PrintWriter out) {
    	Collection<Timer> timers = ts.getTimers();
		out.println("Timers found: " + timers.size());
		
		timers.forEach(t -> t.cancel());
		out.println("<br/>All Timers Stopped.");
		System.out.println("--- All Timers Stopped ---");
	}
    
}
