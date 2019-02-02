package abc;

//-Exceptions rollback and JMS tested in EJBTestJMS project to keep this project possible to run on all servers (No JPA and JMS here)

//localhost does not work for WF server by default for some apps, use 127.0.0.1
//Fe, ab -c 1 -n 20000 http://127.0.0.1:8080/EJBTest/res/concurrency/ejb-sin-cmc


//-Services Given by the Container
// • Remote client communication: Without writing any complex code, an EJB client (another EJB, a user interface, a batch process, etc.) can invoke methods remotely via standard protocols. 
// • Dependency injection: The container can inject several resources into an EJB (JMS destinations and factories, datasources, other EJBs, environment variables, etc.) as well as any POJO thanks to CDI. 
// • State management: For stateful session beans, the container manages their state transparently. You can maintain state for a particular client, as if you were developing a desktop application. 
// • Pooling: For stateless beans and MDBs, the container creates a pool of instances that can be shared by multiple clients. Once invoked, an EJB returns to the pool to be reused instead of being destroyed. 
// • Component life cycle: The container is responsible for managing the life cycle of each component.
// • Messaging: The container allows MDBs to listen to destinations and consume messages without too much JMS plumbing. 
// • Transaction management: With declarative transaction management, an EJB can use annotations to inform the container about the transaction policy it should use. The container takes care of the commit or the rollback. 
// • Security: Class or method-level access control can be specified on EJBs to enforce user and role authorization. 
// • Concurrency support: Except for singletons, where some concurrency declaration is needed, all the other types of EJB are thread-safe by nature. You can develop high-performance applications without worrying about thread issues. 
// • Interceptors: Cross-cutting concerns can be put into interceptors, which will be invoked automatically by the container. 
// • Asynchronous method invocation: Since EJB 3.1, it’s now possible to have asynchronous calls without involving messaging.

//-When should we use a SB instead of a managed bean? Whenever we need the advanced enterprise services offered by EJB, such as:
// • method-level transaction management and security,
// • asynchronous methods,
// • concurrency management,
// • instance-level passivation for stateful session beans,
// • instance-pooling for stateless session beans,
// • remote or web service invocation,
// • timers,
//When we don’t need any of these things, an ordinary managed bean will serve just fine.

public class Info {}
