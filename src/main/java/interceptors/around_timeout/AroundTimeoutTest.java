package interceptors.around_timeout;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;

//-For both. programatic and declarative timers (Payara/Glassfish)
//-ONLY if Timer is passed AND USED (fe, timer.getInfo()) !!!
//@AroundTimeout doesnt work to cancel Timer for programmatic timers.
//	@Timeout										@Schedule(second = "*/5", minute = "*", hour = "*", persistent=false, info="Declarative Timer")
//	public void timerMethod(Timer timer) {}			private void timerMethod(Timer timer) {}
//-Exception:
//	javax.ejb.NoSuchObjectLocalException: timer no longer exists
//	Caused by: javax.ejb.FinderException: Non-persistent timer 18@@1544808096160@@server@@domain1 does not exist


//@Stateless				//Commented out to turn it off
@SuppressWarnings("unused")
public class AroundTimeoutTest {
	
	int counter = 1;
	
	@Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    public void timerMethod() { 
		System.out.println("Scheduler... -> " + counter++);
	}

    @AroundTimeout
    public Object timeoutInterceptorMethod(InvocationContext ctx) throws Exception {
    	System.out.println("AroundTimeout called");
    	
    	if(counter == 3) {
    		Object timer = ctx.getTimer();
        	if(timer != null) {
        		System.out.println("timer not null");
        		Timer tr = (Timer) timer;
        		System.out.println("Timer is cancelled after 3rd round.");
    			tr.cancel();	
        	}
		}
    	
    	return ctx.proceed();
    }
	
}
