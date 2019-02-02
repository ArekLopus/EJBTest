package ejb.bean.stateless;

//		Stateless Beans
//-Stateless mean that a task has to be completed in a single method call.
//-Stateless session beans can support a large number of clients, minimizing any needed resources.
//-Having stateless applications is one way to improve scalability (as the container doesn’t have to store and manage state).

//-Stateless services are independent, are self-contained, and do not require information or state from one request to another.
//-SL SBs follow the stateless server arch. and are the most efficient component model
// because they can be pooled and shared by several cl.
//-When a client invokes a method on a SL bean, the container picks an instance from the pool and assigns it to the client.
// When the client request finishes, the instance returns to the pool to be reused.
// This way you need only a small number of beans to handle several clients,
//-Because they live in a container, can use any container-managed service.

//-Stateless session beans often contain several closely related business methods.
// You will find methods to create, update, find, as well as other related business logic.

//-The @Stateless  marks the ItemEJB POJO as a SL SB, thus turning a simple Java class into a container-aware component.
// •  name parameter specifies the name of the bean and by default is the name of the class (ItemEJB in the example).
//    This parameter can be used to look up an EJB with JNDI, for example.
// •  description parameter is a String that can be used to describe the EJB.
// •  mappedName attribute is the global JNDI name assigned by the container. Note that this JNDI name is vendor specific and is therefore not portable. mappedName has no relationship with the portable global JNDI name, described earlier.


//		Business Methods
//-The primary purpose of a session bean is to run business tasks for the client. The client invokes business methods on the obj
// reference it gets from DI or JNDI lookup. From the client's perspective, the business methods appear to run locally.

//-The signature of a business method must conform to these rules.
// • The method name must not begin with ejb..., to avoid conflicts with callback methods defined by the EJB architecture.
// • The access control modifier must be public.
// • If the bean allows remote access through a remote business interface, the arguments and return types must be legal RMI API types.
// • If the bean is a JAX-WS WS endpoint, the arguments, return types for the methods @ed @WebMethod must be leg. JAX-WS  types.
// • If the bean is a JAX-RS resource, the arguments and return types for the resource methods must be legal types for JAX-RS.
// • The modifier must not be static or final.
//-The throws clause can include exceptions that you define for your app.
//-To indicate a system-level problem, such as the inability to connect to a DB, a business method should throw a EJBException. 
// The container will not wrap application exceptions, such as BookException.
// Because EJBException is a subclass of RuntimeException, you dont need to include it in the throws clause of the business method.


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

//Stateless  and  Singleton
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

//-SL and singleton beans share the same life cycle, but there are some differences in the way they are created and destroyed. 
//-When you deploy a SL SB, the container creates several instances and adds them into a pool. When a client calls a method
// on a SL SB, the container selects one instance from the pool, delegates the method invocation to that instance,
// and returns it to the pool. When the container no longer needs the instance
// (usually when the container wants to reduce the number of instances in the pool), it destroys it.
//-For singleton SBs, creation depends on whether they are instantiated eagerly (@Startup) or not, or whether they
// depend (@DependsOn) on another singleton that had been eagerly created.
//  If the answer is yes, the container will create an instance at deployment time.
//  If not, the container will create an instance when a client invokes a business method.
// Because singletons last for the duration of the application, the instance is destroyed when the container shuts down.

//-Lifecycle
// 1. @AroundConstruct 
// 2. constructor 
// 3. Dependency Injection 
// 4. @PostConstruct 
// 5. Business Methods invocations, @AroundInvoke 
// 6. @PreDestroy


//Callbacks
//-The change from one state to another can be intercepted by the container to invoke methods @ed by one of the annotations:
//@AroundConstruct	- (JEE 7) invokes logic prior to the constructor of the target class is invoked.
//@PostConstruct	- Marks a method to be invoked immediately after you create a bean instance and the container does DI.
//					  This annotation is often used to perform any initialization. 
//@PreDestroy		- Marks a method to be invoked immediately before the container destroys the bean instance. 
//					  @PreDestroy is often used to release resources that had been previously initialized. With SF beans, this happens after timeout or when a method annotated with @Remove has been completed. 


public class AnInfo {}
