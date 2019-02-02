package session_context;

//-Usually, SBs don’t access the container or use the container services directly (transaction, security, DI, etc.).
// These services are meant to be handled transparently by the container on the bean’s behalf (this is called inversion of control).
//-Sometimes it is necessary for the bean to explicitly use container services in code (fe, marking a trans to be rolled back). 
// And this can be done through the javax.ejb.SessionContext interface.
// The SessionContext allows programmatic access to the runtime context provided for a session bean instance.
//-SessionContext extends the EJBContext interface (EJBContext is mostly for old type of EJBs - before 3.0).

//Some Methods of the SessionContext Interface 
//	Method					Description 
//	getCallerPrincipal		Returns the java.security.Principal associated with the invocation. 
//	getRollbackOnly			Tests whether the current transaction has been marked for rollback.
//	getTimerService			Returns the javax.ejb.TimerService interface. Only stateless beans and singletons can use this method.
//	getUserTransaction		Returns the javax.transaction.UserTransaction interface to demarcate transactions. 
//							Only session beans with bean-managed transaction (BMT) can use this method. 
//	isCallerInRole			Tests whether the caller has a given security role. 
//	lookup					Enables the session bean to look up its environment entries in the JNDI naming context. 
//	setRollbackOnly			Allows the bean to mark the current transaction for rollback. 
//	wasCancelCalled			Checks whether a client invoked the cancel() method on the client Future object
//							corresponding to the currently executing asynchronous business method.

//-A SB can have access to its environment context by injecting a reference of SessionContext with an @Resource annotation.
//Here the createBook method checks that only admins can create a book. It also rolls back if the inventory level of books is too high.
//	@Resource 
//	private SessionContext context;
//	if (!context.isCallerInRole("admin")) throw new SecurityException("Only admins can create books"); 
//	if (inventoryLevel(book) == TOO_MANY_BOOKS) context.setRollbackOnly();
//or
//	private SessionContext context;
//	@Resource setSessionContext(SessionContext sessionContext) {   this.sessionContext = sessionContext;   }

public class Info {}
