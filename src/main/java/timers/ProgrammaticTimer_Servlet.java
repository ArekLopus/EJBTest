package timers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-When a programmatic timer expires (goes off), the container calls the method  @Timeout in the bean's implementation class.
//-The @Timeout method contains the business logic that handles the timed event.
//-Methods annotated @Timeout in the EB class must return void and optionally take a javax.ejb.Timer object as the only parameter.
//-They may not throw application exceptions:

//-To create a timer, the bean invokes one of the create methods of the TimerService interface. 
// Use either DI (@Resource TimerService) or the SessionContext (SessionContext.getTimerService()), or through JNDI lookup.
//-These methods allow single-action, interval, or calendar-based timers to be created.

//-For single-action or interval timers, the expiration of the timer can be expressed as either a duration or an absolute time.
//-The duration is expressed as a the number of milliseconds before a timeout event is triggered.
//-To specify an absolute time, create a Date object and pass it to the TimerService.createSingleActionTimer() or createTimer().
//	Timer timer = timerService.createSingleActionTimer(60000, new TimerConfig());
//	Timer timer = timerService.createSingleActionTimer(new Date(), new TimerConfig());

//-For calendar-based timers, the expiration of the timer is expressed as a javax.ejb.ScheduleExpression object,
// passed as a parameter to the TimerService.createCalendarTimer().
//-The ScheduleExpression class has methods that correspond to the attributes described in Creating Calendar-Based Timer Exprs.
//	new ScheduleExpression().dayOfMonth("Mon").month("Jan"); 
//	new ScheduleExpression().second("10,30,50").minute("*/5").hour("10-14"); 
//	new ScheduleExpression().dayOfWeek("1,5").timezone("Europe/Lisbon"); 

//-Once you create the timer (call the method that creates the timer), the container will invoke the @Timeout method at set time.

//-Timers are persistent by default.
//-If the server is shut down/crashes, persistent timers are saved and will become active again when the server is restarted. 
//-If a persistent timer expires while the server is down, the container will call the @Timeout method when the server is restarted.

//-Nonpersistent programmatic timers are created by calling TimerConfig.setPersistent(false)
// and passing the TimerConfig object to one of the timer-creation methods.
//-The Date and long parameters of the createTimer methods represent time with the resolution of milliseconds. 
//-The timer service is not intended for real-time apps, a callback to the @Timeout method might not occur with millisecond precision.
//-The timer service is for business applications, which typically measure time in hours, days, or longer durations.

@WebServlet("/timersProgrammatic")
public class ProgrammaticTimer_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	ProgrammaticTimer pt;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Programmatic Timers</h3>");
		
		pt.singleActionTimer();
		
		pt.calendarActionTimer();
		
	}
	
}
