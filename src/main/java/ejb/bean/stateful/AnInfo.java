package ejb.bean.stateful;


//		Stateful Beans
//-SF SBs preserve conversational state. 
//-They are useful for tasks that have to be done in several steps, each of which relies on the state maintained in a previous step.
//-When a client invokes a SF SB in the server, the EJB container needs to provide the same instance for each subsequent method invocation.
//-SF SBs cannot be reused by other clients. No extra code needed, as the EJB container automatically manages one-to-one correlation.

//-The 1-to-1 correlation comes at a price because if you have one million clients, you will get one million SF beans in memory.
//-If one client doesn’t invoke its bean’s instance for a long enough time, the container has to clear it before the JVM runs out
// of memory, preserve the instance state to a permanent storage, and then bring back the instance with its state when it’s needed. 
//-This technique is called passivation and activation.
//-Passivation is the process of removing an instance from memory and saving it to a persistent location (a file on a disk, a DB, etc.).
// It helps you to free memory and release resources (a DB or JMS connections, etc.). 
//-Activation is the inverse process of restoring the state and applying it to an instance.

//- Passivation and activation are done automatically by the container; you shouldn’t worry about it, as it’s a container service.
//-What you should worry about is freeing any resource (e.g., DB connection, JMS factories conn, etc.) before the bean is passivated.
//-Since EJB 3.2, you can also disable passivation.
//	@Stateful 
//	@StatefulTimeout(value = 20, unit = TimeUnit.SECONDS) 
//	public class ShoppingCartEJB {
//	    … 
//	    @Remove 
//	    public void checkout() { //some business logic   cartItems.clear();  }
//	}
//-The only needed annotation is @Stateful, which has the same API as @Stateless,.
// •  name parameter specifies the name of the bean and by default is the name of the class (ItemEJB in the example).
//    This parameter can be used to look up an EJB with JNDI, for example.
// •  description parameter is a String that can be used to describe the EJB.
// •  mappedName attribute is the global JNDI name assigned by the container. Note that this JNDI name is vendor specific and
//    is therefore not portable. mappedName has no relationship with the portable global JNDI name, described earlier.
// •  passivationCapable - Specifies whether this stateful session bean is passivation capable
//-Optional @StatefulTimeout assigns a timeout value, which is the duration the bean is permitted to remain idle
// (not receiving any client invocations) before being removed by the container. 
//-The time unit is a java.util.concurrent.TimeUnit, so it can go from DAYS, HOURS ... to NANOSECONDS (the default is MINUTES)

//-@Remove decorates a method. The bean instance is permanently removed from memory after you invoke the checkout() sbove. 

//-Alternatively, you can avoid these annotations and rely on the container automatically removing an instance when the client’s
// session ends or expires. However, making sure the stateful bean is removed at the appropriate moment might reduce memory consumption.
// This could be critical in highly concurrent applications.


//		Session Beans Life Cycle
//-The container is responsible for managing the life cycle of the bean. 
//-Life cycle means that a session bean goes through a predefined set of state transitions. Depending on the type of your bean (SL, SF , singleton), the life cycle will consist of different states.
//-Each time the container changes the lifecycle state, it can invoke methods that are annotated with callback annotations.
//-All SBs have 2 obvious phases in their life cycle: creation and destruction.
//-SF SBs also go through the passivation and activation phases
//-Similar to the callback methods used in entities that you saw in previous chapters, EJBs allow the container, during certain phases of its life, to automatically invoke annotated methods (@PostConstruct, @PreDestroy, etc.).
// These methods may initialize state information on the bean, look up resources using JNDI, or release database connections. 

//	Stateful
//-The container generates an instance and assigns it only to one client. Then, each request from that client is passed to the same instance..
//-If one client doesn’t invoke its bean’s instance for a long enough time, the container has to clear it before the JVM runs out
// of memory, preserve the instance state to a permanent storage, and then bring back the instance with its state when it’s needed.

//-The container employs the technique of passivation and activation.
//-Passivation is when the container serializes the bean instance to a permanent storage medium (file on a disk, DB, etc.).
//-Activation, which is the opposite, is done when the bean instance is needed again by the client. The container deserializes
// the bean from permanent storage and activates it back into memory. This means the bean’s attributes have to be serializable
// (it must either be a Java primitive or implement the java.io.Serializable interface). 

//-Stateful bean life cycle:
//1. The life cycle of a stateful bean starts when a client requests a reference to the bean (using DI or JNDI lookup).
//   The container creates a new session bean instance and stores it in memory. 
//2. If the newly created instance uses DI through annotations (@Inject, @Resource, @EJB, @PersistenceContext, etc.) or DDs,
//   the container injects all the needed resources. 
//3. If the instance has a method annotated with @PostContruct, the container invokes it. 
//4. The bean executes the requested call and stays in memory, waiting for subsequent client requests. 
//5. If the client remains idle for a period of time, the container invokes the method @ed with @PrePassivate, if any,
//   and passivates the bean instance into a permanent storage. 
//6. If the client invokes a passivated bean, the container activates it back to memory and invokes the method @ed @PostActivate, if any. 
//7. If the client does not invoke a passivated bean instance for the session timeout period, the container destroys it. 
//8. Alternatively to step 7, if the client calls a method annotated by @Remove, the container then invokes the method annotated with
//   @PreDestroy, if any, and ends the life of the bean instance.

//-Optional @StatefulTimeout assigns a timeout value, which is the duration the bean is permitted to remain idle
// (not receiving any client invocations) before being removed by the container. 
//-The time unit is a java.util.concurrent.TimeUnit, so it can go from DAYS, HOURS ... to NANOSECONDS (the default is MINUTES)

//-@Remove decorates a method. The bean instance is permanently removed from memory after you invoke the remove method. 


//		Transaction SessionSynchronization
//-A stateful SB instance is not required to commit a started transaction before a business method or interceptor method returns.
//-Only a stateful session bean with CONTAINER-managed transaction demarcation can receive session synchronization notifications.
//-The SessionSynchronization interface allows a stateful SB instance to be notified by its container of transaction boundaries.
//-Other bean types must not implement the SessionSynchronization interface or use the session synchronization annotations.



public class AnInfo {}
