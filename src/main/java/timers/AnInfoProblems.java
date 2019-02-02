package timers;

//https://stackoverflow.com/questions/13092567/automatic-ejb-timer-on-glassfish-server-not-triggering/13102822#13102822

//-ScheduleExpression ONLY for calendar timer.

//Timer singleActionTimer = ts.createSingleActionTimer(1, new TimerConfig("Single Action Timer", true));
//	singleActionTimer.getSchedule();	//IllegalStateException: Only allowed for calendar-based timers

//calendarTimer.getHandle();	//IllegalStateException: Only allowed for persistent timers


//	Timers and @AroundTimeout
//-After canceling timer, return null NOT ctx.proceed() otherwise Exception:
//	javax.ejb.FinderException: Non-persistent timer 19@@1547808358789@@server@@domain1 does not exist
//@AroundTimeout
//private Object timeoutInterceptorMethod(InvocationContext ctx) throws Exception {
//	System.out.println("AroundTimeout called");
//	
//	if(counter == 4) {
//		counter = 1;
//		
//		Timer timer = (Timer) ctx.getTimer();
//   	if(timer != null) {
//    		System.out.println("Timer is cancelled after 3rd round.");
//			timer.cancel();
//    		return null;						//NOT ctx.proceed()!!!
//    	}
//	}
//	return ctx.proceed();
//}


public class AnInfoProblems {}
