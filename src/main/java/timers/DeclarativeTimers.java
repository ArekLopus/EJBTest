package timers;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;
import javax.transaction.TransactionSynchronizationRegistry;

//Declarative Timer Creation
//-Timers can be created automatically by the container at deployment time based on metadata.
//-An EB can have multiple automatic timeout methods, unlike a programmatic timer,
// which allows only one method @ed with the @Timeout in the EB class.
//-The container creates a timer for each method annotated with @Schedule or @Schedules (or the XML eq in the ejb-jar.xml DD).
//-By default, each @Schedule corresponds to a single persistent timer, but you can also define nonpersistent timers.

//-The @Schedule has elements that correspond to the calendar expressions detailed in Creating Calendar-Based Timer Expressions 
// and the persistent, info, and timezone elements.
//-The optional persistent element takes a Boolean value and is used to specify whether the automatic timer
// should survive a server restart or crash. By default, all automatic timers are persistent.
//-The optional timezone el is used to specify that the automatic timer is associated with a particular time zone.
// If set, this element will evaluate all timer expressions in relation to the specified time zone, regardless of the time zone
// in which the EJB container is running. By default, all automatic timers set are in relation to the default time zone of the server.
//-The optional info el is used to set an informational description of the timer.
// A timer's info can be retrieved using Timer.getInfo.

//-The @Schedules is used to specify multiple calendar-based timer expressions for a given timeout method.
//	@Schedules ({
//	    @Schedule(dayOfMonth="Last"),
//	    @Schedule(dayOfWeek="Fri", hour="23")
//	})
//	public void doPeriodicCleanup() { ... }

//-statisticsItemsSold() creates a timer that will call the method every first day of the month at 5:30 a.m.
//-generateReport() creates two timers (using @Schedules): one every day at 2 a.m. and another one every Wednesday at 2 p.m.
//-refreshCache() creates a nonpersistent timer that will refresh the cache every ten minutes.
//	@Schedule(dayOfMonth = "1", hour = "5", minute = "30") 	public void statisticsItemsSold() {}
//	@Schedules({ @Schedule(hour = "2"),  @Schedule(hour = "14", dayOfWeek = "Wed")  })     generateReport()
//	@Schedule(minute = "*/10", hour = "*", persistent = false) 	public void refreshCache() {


//@Stateless	//Turned off to not to start at redeploy
@SuppressWarnings("unused")
public class DeclarativeTimers {
	
	int counter = 1;
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	@Schedule(second = "*/3", minute = "*", hour = "*", persistent=false, info="Declarative Timer")
	private void timerMethod(Timer timer) {
		System.out.println("Timer... -> " + (counter++) + ", info: " + timer.getInfo());
	}
	
	
	@AroundTimeout
    private Object timeoutInterceptorMethod(InvocationContext ctx) throws Exception {
    	System.out.println("AroundTimeout called");
    	
    	if(counter == 4) {
    		counter = 1;
    		
    		Timer timer = (Timer) ctx.getTimer();
        	if(timer != null) {
        		System.out.println("Timer is cancelled after 3rd round.");
    			timer.cancel();
        		return null;
        	}
		}
    	return ctx.proceed();
    }
    
	
    public void justInvokeThis() {
		System.out.println("Started DeclarativeTimers");
	}
}