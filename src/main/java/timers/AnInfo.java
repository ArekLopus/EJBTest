package timers;

//https://stackoverflow.com/questions/13092567/automatic-ejb-timer-on-glassfish-server-not-triggering/13102822#13102822

//Glassfish / Payara
//-Timer Config
//		http://localhost:4848/ejb/configuration/ejbContainerTimerService.jsf?configName=server-config 
//-JDBC/JDBC Connection Pools/--TimerPool
//		http://localhost:4848/jdbc/jdbcConnectionPoolEdit.jsf?name=__TimerPool
//-Timer Application
//		http://localhost:8080/ejb-timer-service-app/timer


//-The EJB timer service is a container service that allows EJBs to be registered for callback invocation.
//-EJB notifications may be scheduled to occur at
// a calendar-based schedule, at a specific time, after a specific elapsed duration, or at specific recurring intervals. 
//-The container keeps a record of all the timers, and invokes the appropriate bean instance method when a timer has expired.
//-SL beans, singletons, and MDBs can be registered by the timer service, but Statefull beans can’t use the scheduling api.

//-Timers are persistent by default.
//-Timers are intended for long-lived business processes and are by default persistent.
// They survive server shutdowns, and once the server starts again, the timers are executed as if no shutdown had happened.
// Optionally, you can specify timers to be nonpersistent.

//-The timer service is for business applications, which typically measure time in hours, days, or longer durations.
//-The Date and long parameters of the createTimer methods represent time with the resolution of milliseconds. 
//-The timer service is not intended for real-time apps, a callback to the @Timeout method might not occur with millisecond precision.

// • @Schedule - if the bean has methods annotated with this @, timers are created automatically by the container at deployment time.
// • @Timeout - timers can also be created programmatically and must provide ONE callback method annotated with the @Timeout.

//@Timeout and @Schedule timeout method
//-The timeout callback method to which the @Schedule is applied must have the signatures, where <METHOD>designates the method name: 
//	 void <METHOD>()
//	 void <METHOD>(Timer timer)
//-A timeout callback method can have public, private, protected, orpackage level access.
//-A timeout callback method must not be declared asfinal or static.
//-Timeout callback methods must not throw application exceptions.


//		Programmatic Timers
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

//-Nonpersistent programmatic timers are created by calling TimerConfig.setPersistent(false)
// and passing the TimerConfig object to one of the timer-creation methods.
//-The Date and long parameters of the createTimer methods represent time with the resolution of milliseconds. 
//-The timer service is not intended for real-time apps, a callback to the @Timeout method might not occur with millisecond precision.
//-The timer service is for business applications, which typically measure time in hours, days, or longer durations.


//		Declarative Timer Creation
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

//-statisticsItemsSold() creates a timer that will call the method every first day of the month at 5:30 a.m.
//-generateReport() creates two timers (using @Schedules): one every day at 2 a.m. and another one every Wednesday at 2 p.m.
//-refreshCache() creates a nonpersistent timer that will refresh the cache every ten minutes.
//	@Schedule(dayOfMonth = "1", hour = "5", minute = "30") 	public void statisticsItemsSold() {}
//	@Schedules({ @Schedule(hour = "2"),  @Schedule(hour = "14", dayOfWeek = "Wed")  })     generateReport()
//	@Schedule(minute = "*/10", hour = "*", persistent = false) 	public void refreshCache() {


//	Canceling and Saving Timers
//-Timers can be cancelled by the following events.
// • When a single-event timer expires, the EJB container calls the associated timeout method and then cancels the timer.
// • When the bean invokes the cancel() of the Timer interface, the container cancels the timer.
//   If a method is invoked on a cancelled timer, the container throws the javax.ejb.NoSuchObjectLocalException.

//-To save a Timer object for future reference, invoke its getHandle() and store the TimerHandle obj (A TimerHandle obj is serializable.). 
//-To reinstantiate the Timer object, retrieve the handle from the DB and invoke getTimer() on the handle. 
//-A TimerHandle object cannot be passed as an argument of a method defined in a remote or WS interface.
// In other words, remote clients and web service clients cannot access a bean's TimerHandle object.
//-getHandle() -> Only for persistent timers!

//	Getting Timer Information
//-In addition to defining the cancel and getHandle methods, the Timer intf defines methods for obtaining info about timers:
//	public long getTimeRemaining();
//	public java.util.Date getNextTimeout();
//	public java.io.Serializable getInfo();
//-The getInfo() returns the object that was the last parameter of the createTimer invocation.
//-To retrieve all of a bean's active timers, call the getTimers() of the TimerService interface.
// The getTimers method returns a collection of Timer objects.

//	Transactions and Timers
//-An EB usually creates a timer within a transaction. If this transaction is rolled back, the timer creation also is rolled back.
//-Similarly, if a bean cancels a timer within a transaction that gets rolled back, the timer cancellation is rolled back.
// In this case, the timer's duration is reset as if the cancellation had never occurred.
//-In beans that use container-managed transactions, the @Timeout method usually has the Required or RequiresNew transaction attr
// to preserve transaction integrity. With these attributes, the EJB container begins the new transaction before calling the
// @Timeout method. If the transaction is rolled back, the container will call the @Timeout method at least one more time.


public class AnInfo {}
