package transactions;

//-All EJBs can use BMT:
// @TransactionManagement - Specifies whether a session bean or message driven bean has CMT or BMT.

//-Declarative Transaction Demarcation - CMT
//-Programmatic Transaction Demarcation - BMT

//	@TransactionAttribute
//-Transaction attributes - REQUIRED, REQUIRES_NEW, SUPPORTS, MANDATORY, NOT_SUPPORTED, NEVER
//-@TransactionAttribute (javax.ejb) - use it to apply one of these six demarcation attributes to your session bean,
// or use the deployment descriptor (setting the <trans-attribute> element in the ejb-jar.xml). 
//-These metadata can be applied either to individual methods or to the entire bean.
//-If applied at the bean level, all business methods will inherit the bean’s transaction attribute value. 

//-TransactionAttributeType enum is used with the @TransactionAttribute to specify whether the methods of
// a session bean or message driven bean are called with a valid transaction context.
//-For a MDB's message listener methods (or interface), only the REQUIRED and NOT_SUPPORTED values may be used.
//-For an enterprise bean's timeout callback methods, only the REQUIRED, REQUIRES_NEW, NOT_SUPPORTED values may be used.
//-For a session bean's asynchronous business methods, only the REQUIRED, REQUIRES_NEW, NOT_SUPPORTED may be used.
//-For a singleton SB's PostConstruct and PreDestroy callbacks, only the REQUIRED, REQUIRES_NEW, and NOT_SUPPORTED values may be used.
//-If an enterprise bean implements the SessionSynchronization interface or uses any of the session synchronization annotations,
// only values may be used for the transaction attributes of the bean's methods: REQUIRED, REQUIRES_NEW, MANDATORY.


//-The EJB model was always designed to manage transactions, by default each method is automatically wrapped in a transaction. 
//-This default behavior is known as a container-managed transaction (CMT), because transactions are managed by the EJB container
// (a.k.a. declarative transaction demarcation).
//-You can also choose to manage transactions yourself using bean-managed transactions (BMTs), also called
// programmatic transaction demarcation. Transaction demarcation determines where transactions begin and end.

//		Container Managed Transactions
//-With CMT, you leave the container to do the transaction demarcation just by specifying a @TransactionAttribute
// and using the session context or exceptions to mark a transaction for rollback.
//-The EJB container provides transaction management services to session beans and MDBs.
//-In an enterprise bean with a container-managed transaction, the EJB container sets the boundaries of the transactions.
//-With configuration by exception, all the transaction management defaults are applied (REQUIRED is the default)

//-The client call is intercepted by the container, which checks immediately before invoking the method whether a transaction
// context is associated with the call. By default, if no transaction context is available, the container begins
// a new transaction before entering the method and then invokes the method.
//-Once the method exits, the container automatically commits the transaction or rolls it back
// (if a particular type of exception is thrown

//■ Note: Client transaction context does not propagate with asynchronous method invocation (@Asynchronous) 
//-MDBs support only the REQUIRED and NOT_SUPPORTED attributes as explained in Chapter 13.

//-This behavior can be changed using metadata (annotation or XML deployment descriptor).
//-Depending on a transaction attribute (REQUIRED, REQUIRES_NEW, SUPPORTS, MANDATORY, NOT_SUPPORTED, NEVER), you can affect the way
// the container demarcates transactions: on a client invocation of a transactional method, the container uses
// the client’s transaction, runs the method in a new transaction, runs the method with no transaction, or throws an exception. 

//	Marking a CMT for Rollback
//-You might want to prevent the transaction from being committed if some error or business condition is encountered.
//-It is important to stress that a CMT bean is not allowed to roll back the transaction explicitly.
// Instead, you need to use the EJB context  to inform the container to roll back. ( @Resource SessionContext.setRollbackOnly() )
//-Calling this method doesn’t roll back the transaction immediately; instead, a flag is set for the container to do the
// actual rollback when it is time to end the transaction. Only session beans with CMT demarcation can use this method 
//-BMT session beans roll back transactions directly ( Usertransaction.rollback() ).
//-SessionContext.getRollbackOnly(), returns a boolean, to determine whether the current transaction has been marked for rollback.
//-Another way to programmatically inform the container to roll back is by throwing specific types of exceptions.

//	Exceptions and Transactions
//-Throwing an exception in a business method will not always mark the transaction for rollback. It depends on the type of
// exception or the metadata defining the exception. In fact, the EJB 3.2 specification outlines two types of exceptions.
// • Application exceptions: Exceptions related to business logic handled by the EJB. Throwing an app exception does not auto
//   result in marking the transaction for rollback. As detailed table, the container doesn’t roll back when checked exceptions
//   (which extend java.lang.Exception) are thrown, but it does for unchecked exceptions (which extend RuntimeException).
// • System exceptions: Exceptions caused by system-level faults: JNDI errors, JVM errors, failure to acquire a DB conn, and so on.
//   A system exception must be a subclass of a RuntimeException or java.rmi.RemoteException (and therefore a subclass of
//   javax.ejb.EJBException). Throwing a system exception results in marking the transaction for rollback.

//-An application exception is one that extends from a checked or unchecked exception and is annotated with @ApplicationException
// (or the XML equivalent in the deployment descriptor). 
//-This annotation has a rollback element that can be set to true to explicitly roll back the transaction.


//		Bean-Managed Transactions
//-With CMT, you leave the container to do the transaction demarcation just by specifying
// a transaction attribute and using the session context or exceptions to mark a transaction for rollback. 
//-In some cases, the declarative CMT may not provide the demarcation granularity that you require (e.g., a method cannot generate
// more than one transaction). To address this issue, EJBs offer a programmatic way to manage transaction demarcations with BMT.
//-BMT allows you to explicitly manage transaction boundaries (begin, commit, rollback) using JTA.

//-@TransactionManagement - a bean simply has to turn off the default CMT demarcation and switch to BMT mode (or the XML equivalent in the ejb-jar.xml file) as follows:
//	@Stateless
//	@TransactionManagement(TransactionManagementType.BEAN)
//	public class ItemEJB {...}
//-With BMT demarcation, the application requests the transaction, and the EJB container creates the physical transaction
// and takes care of a few low-level details. Also, it does not propagate transactions from one BMT to another.
//-The main interface used to carry out BMT is javax.transaction.UserTransaction.
//-It allows the bean to demarcate a transaction, get its status, set a timeout, and so on.
//-The UserTransaction is instantiated by the EJB container and made available through dependency injection,
// JNDI lookup, or the SessionContext ( with the SessionContext.getUserTransaction() ). Table describes the API.

//	Method 					Description
//	begin 					Begins a new transaction and associates it with the current thread
//	commit 					Commits the transaction attached to the current thread
//	rollback 				Rolls back the transaction attached to the current thread
//	setRollback				Only Marks the current transaction for rollback
//	getStatus 				Obtains the status of the current transaction
//	setTransactionTimeout 	Modifies the timeout for the current transactions


//-One of the drawbacks of BMT is the fact that it can never join an existing transaction!
 


public class Info {}
