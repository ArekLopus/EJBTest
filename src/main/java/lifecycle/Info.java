package lifecycle;

//-Lifecycle
// 1. @AroundConstruct 
// 2. constructor 
// 3. Dependency Injection 
// 4. @PostConstruct 
// 5. Business Methods invocations, @AroundInvoke 
// 6. @PreDestroy

//		Session Beans Life Cycle
//-The container is responsible for managing the life cycle of the bean. 
//-Life cycle means that a session bean goes through a predefined set of state transitions.
// Depending on the type of your bean (SL, SF , singleton), the life cycle will consist of different states.
//-Each time the container changes the lifecycle state, it can invoke methods that are annotated with callback annotations.
//-All SBs have 2 obvious phases in their life cycle: creation and destruction.
//-SF SBs also go through the passivation and activation phases
//-Similar to the callback methods used in entities, EJBs allow the container, during certain phases of its life,
// to automatically invoke annotated methods (@PostConstruct, @PreDestroy, etc.).
// These methods may initialize state information on the bean, look up resources using JNDI, or release database connections. 


//		Stateless  and  Singleton
//-Stateless and singleton beans have in common the fact that they don’t maintain conversational state with a dedicated client.
//-Both bean types allow access by any client — stateless does this on a per-instance basis,
// while singleton provides concurrent access to a single instance.

//-Both share the same life cycle:
// 1. The life cycle of a SL or singleton SB starts when a client requests a reference to the bean (using DI or JNDI lookup). In the case of a singleton, it can also start when the container is bootstrapped (using the @Startup ). The container creates a new SB instance.
//    - JEE 7 Added @AroundConstruct callback called before the constructor.
// 2. If the newly created instance uses DI through annotations (@Inject, @Resource, @EJB, @PersistenceContext, etc.) or DDs, the container injects all the needed resources. 
// 3. If the instance has a method annotated with @PostContruct, the container invokes it. 
// 4. The bean instance processes the call invoked by the client and stays in ready mode to process more calls.
//    - SL beans stay in ready mode til the container frees some space in the pool.
//    - Singletons stay in ready mode until the container is shut down. 
// 5. The container doesnt need the instance anymore. invokes the method @ed @PreDestroy, if any, and ends the life of the bean inst.


//		Stateful
//-Stateful beans maintain conversational state with their client, and therefore have a slightly different life cycle.
// The container generates an instance and assigns it only to one client. Then, each request from that client is passed to the same
// instance. Following this principle and depending on your app, you might end up with a one-to-one relationship between a client
// and a SF bean (e.g., 1000 simultaneous users might produce 1000 SF beans). If one client doesn’t invoke its bean’s instance for
// a long enough time, the container has to clear it before the JVM runs out of memory, preserve the instance state to a permanent
// storage, and then bring back the instance with its state when it’s needed.
// The container employs the technique of passivation and activation.
//-Passivation is when the container serializes the bean instance to a permanent storage medium (file on a disk, DB, etc.)
// instead of holding it in memory.
//-Activation, which is the opposite, is done when the bean instance is needed again by the client. The container deserializes
// the bean from permanent storage and activates it back into memory. This means the bean’s attributes have to be serializable 
// (it must either be a Java primitive or implement the java.io.Serializable interface).
// 1. The life cycle of a stateful bean starts when a client requests a reference to the bean (using DI or JNDI lookup).
//    The container creates a new session bean instance and stores it in memory. 
// 2. If the newly created instance uses DI through annotations (@Inject, @Resource, @EJB, @PersistenceContext, etc.) or Dds,
//    the container injects all the needed resources. 
// 3. If the instance has a method annotated with @PostContruct, the container invokes it. 
// 4. The bean executes the requested call and stays in memory, waiting for subsequent client requests. 
// 5. If the client remains idle for a period of time, the container invokes the method @ed with @PrePassivate, if any,
//    and passivates the bean instance into a permanent storage. 
// 6. If the client invokes a passivated bean, the container activates it back to memory and invokes the method @ed @PostActivate, if any. 
// 7. If the client does not invoke a passivated bean instance for the session timeout period, the container destroys it. 
// 8. Alternatively to step 7, if the client calls a method annotated by @Remove, the container then invokes the method annotated with
//    @PreDestroy, if any, and ends the life of the bean instance.

//-In some cases, a SF bean contains open resources such as network sockets or DB conns. Because a container cant keep these resources
// open for each bean, you will have to close and reopen the resources before and after passivation using callback methods.
//-Another possibility is to deactivate the default activation / passivation behavior or your SF bean. You have to be very careful
// with that, but if it’s what you really need you can annotate your stateful bean with @Stateful(passivationCapable=false).


public class Info {}
