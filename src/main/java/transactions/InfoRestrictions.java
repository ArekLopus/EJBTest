package transactions;

//	Restrictions for Transactions

//	Stateful SB
//-By default a SF SB’s PostConstruct, PreDestroy, PrePassivate, PostActivate methods are executed in an unspecified trans ctx. 
//-A PostConstruct, PreDestroy, PrePassivate and PostActivate method of a SF SB with container-managed transaction demarcation
// is permitted to have transaction attribute REQUIRES_NEW or NOT_SUPPORTED (RequiresNew or NotSupported if the DD is used).

// • A SF SB instance can participate in at most a single transaction at a time.
// • If a SF SB instance is participating in a transaction, it is an error for a client to invoke a method on the session object
// such that the transaction attribute specified in the bean’s metadata annotations and/or the DD would cause the container to
// execute the method in a different transaction context or in an unspecified transaction context. In such a case, the EJBException
// will be thrown to a client of the bean’s business interface [28]. If the EJB 2.1 client view is used, the container throws the
// RemoteException to the client if the client is a remote client, or the javax.ejb.EJBException if the client is a local client.
// • If a SF SB instance is participating in a transaction, it is an error for a client to invoke the remove method on the
// session object’s home or component interface object. The container must detect such an attempt and throw the RemoveException
// to the client. The container should not mark the client’s transaction for rollback, thus allowing the client to recover.
// Note that this restriction only applies to the remove method on the session object’s home or component interface,
// not to the invocation of Remove methods.

//	Async method (@Asynchronous) 
//-The client’s transaction context does not propagate with an asynchronous method invocation. 
//-From the Bean Provider’s point of view, there is never a transaction context flowing in from the client. This means, fe,
// that the semantics of the REQUIRED transaction attribute on an asynchronous method are exactly the same as REQUIRES_NEW.

//■ Note: Client transaction context does not propagate with asynchronous method invocation (@Asynchronous) 
//-MDBs support only the REQUIRED and NOT_SUPPORTED attributes as explained in Chapter 13.

//-TransactionAttributeType enum is used with the @TransactionAttribute to specify whether the methods of
// a session bean or message driven bean are called with a valid transaction context.
//-For a MDB's message listener methods (or interface), only the REQUIRED and NOT_SUPPORTED values may be used.
//-For an enterprise bean's timeout callback methods, only the REQUIRED, REQUIRES_NEW, NOT_SUPPORTED values may be used.
//-For a session bean's asynchronous business methods, only the REQUIRED, REQUIRES_NEW, NOT_SUPPORTED may be used.
//-For a singleton SB's PostConstruct and PreDestroy callbacks, only the REQUIRED, REQUIRES_NEW, and NOT_SUPPORTED values may be used.
//-If an enterprise bean implements the SessionSynchronization interface or uses any of the session synchronization annotations,
// only values may be used for the transaction attributes of the bean's methods: REQUIRED, REQUIRES_NEW, MANDATORY.

//	Conversational State
//-A session object’s conversational state is not transactional.
//-It is not automatically rolled back to its initial state if the transaction in which the object has participated rolls back.
//-If a rollback could result in an inconsistency between a session object’s conversational state and the state of the underlying
// DB, the bean dev (or the app dev tools used by the dev) must use the afterCompletion notification to manually reset its state.

//	Transaction Context of Session Bean Methods
//-The following SB methods are invoked in the scope of a transaction determined by
// the transaction attribute specified in the bean’s metadata annotations or deployment descriptor.
// • An implementation of a method defined in a session bean’s business interface or component interface or no-interface view.
// • A web service method.
// • A timeout callback method
// • A singleton session bean’s PostConstruct or PreDestroy lifecycle callback interceptor method.

//??-A stateful session bean's PostConstruct, PreDestroy, PrePassivate or PostActivate lifecycle callback interceptor method is
// invoked in the scope of a transaction determined by the transaction attribute specified in the lifecycle callback method's
// metadata annotations or deployment descriptor.
//-A stateful session bean’s afterBegin and beforeCompletion methods are always called with the same transaction context
// as the business methods executed between the afterBegin and beforeCompletion methods.
//-A session bean’s constructor, setSessionContext, other dependency injection methods, other lifecycle callback interceptor
// methods, and afterCompletion methods are called with an unspecified transaction context.
// Refer to section 8.6.7 for how the container executes methods with an unspecified transaction context.
//-If database operations are performed within a stateful session bean’s PostConstruct, PreDestroy, PrePassivate or PostActivate
// lifecycle callback interceptor methods these operations will not be part of the client’s transaction.
// If such a transaction is rolled back, the instance is discarded.
// See section 4.6.3 for rules on dealing with exceptions in stateful session beans.



public class InfoRestrictions {}
