package injection;

//-Instead of the app looking up resources in JNDI, the container injects them.
//-Injection is made at deployment time.
//-If there is a chance that data wont be used, the bean can avoid the cost of resource injection performing a JNDI lookup.
// JNDI is an alternative to injection through JNDI, the code pulls data only if they are needed,
// instead of accepting pushed data that may not be needed at all.

//-The containers can inject various types of resources into session beans using different annotations (or deployment descriptors).
// • @EJB: Injects a reference of the local, remote, or no-interface view of an EJB into the annotated variable. 
// • @PersistenceContext and @PersistenceUnit: Expresses a dependency on an EntityManager and on an EntityManagerFactory.
// • @WebServiceRef: Injects a reference to a web service. 
// • @Resource: Injects several resources such as JDBC data sources, session context, user transactions, environment entries, 
//   JMS connection factories and destinations, the timer service, and so on. 
// • @Inject: Injects nearly everything with @Inject and @Produces.

public class Info {}
