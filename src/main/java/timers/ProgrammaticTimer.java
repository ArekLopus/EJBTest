package timers;

import java.io.PrintWriter;
import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;

//-@Timeout - timers can also be created programmatically and must provide one callback method annotated with the @Timeout.
//-Calling a method creates a timer, when it goes off runs @Timeout method. 

//-TimerService
// createCalendarTimer, createIntervalTimer, createSingleActionTimer, createTimer, 

//-ScheduleExpression ONLY for calendar timer.

//Timer singleActionTimer = ts.createSingleActionTimer(1, new TimerConfig("Single Action Timer", true));
//	singleActionTimer.getSchedule();	//IllegalStateException: Only allowed for calendar-based timers

//calendarTimer.getHandle();	//IllegalStateException: Only allowed for persistent timers

@Stateless
public class ProgrammaticTimer {
	
	private int counter = 1;
	
	@Resource
	TimerService ts;

	public void singleActionTimer() { 
	    ts.createSingleActionTimer(1, new TimerConfig("Single Action Timer", true));
 	}
	
	public void calendarActionTimer() { 
	    
		TimerConfig tc = new TimerConfig();
		tc.setInfo("Calendar Action Timer");
		tc.setPersistent(false);
		//tc.setPersistent(true);
		
		ScheduleExpression schedule = new ScheduleExpression().second("*/3").minute("*").hour("*"); 
	    
		Timer calendarTimer = ts.createCalendarTimer(schedule, tc);
		
		System.out.println("Timers: ");
		ts.getTimers().forEach(System.out::println);
		System.out.println("getSchedule() -> " + calendarTimer.getSchedule());
 	}
	
	@Timeout   	
	public void timeout(Timer timer) {
	    System.out.println("TimerBean: timeout occurred, info: " + timer.getInfo() + ", counter: " + counter);
	    counter++;
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
	
	
	
	
	
	public void killEmAll(PrintWriter out) {
		
		Collection<Timer> timers = ts.getAllTimers();
		System.out.println("Timers found: " + timers.size());
		
		if(out != null) {
			out.println("Timers found: " + timers.size());
			out.println("<br/>All Timers Stopped.");
		}
		timers.forEach(t -> t.cancel());
		System.out.println("All Timers Stopped.");
	}
	
}
