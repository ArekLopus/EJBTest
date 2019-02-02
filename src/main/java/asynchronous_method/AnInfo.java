package asynchronous_method;


//		Asynchronous Methods
//-A session bean can expose methods with asynchronous client invocation semantics.
//-For asynchronous invocations, control returns to the client before the container dispatches the invocation to a bean instance. 
//-An asynchronous method is a business method exposed through one or more of the remote or local business, or no-interface  views.
//-Asynchronous method invocation semantics only apply to the no-interface, local business, and remote business client views.
// Support for asynchronous business methods exposed through the local component, remote component, and web service client views
// is not required by this specification, and applications which expose such views with asynchronous methods will not be portable.
//-Asynchronous methods can return a Future<V> object that allows the client to retrieve a result value,
// check for exceptions, or attempt to cancel an in-progress invocation.

//-The @Asynchronous is used to designate which business methods are asynchronous.
//-The @Asynchronous can be applied to a particular business method of a bean class (or superclass), or to the bean class (or superclass).
//-If the @Asynchronous is applied at the class level, all business methods declared on that specific class are asynchronous.
//-Asynchronous methods can also be designated via the deployment descriptor.

//	Method Requirements
//-The valid return type of an asynchronous method is either void or java.util.concurrent.Future<V>, where V is the result value type.
//-An asynchronous method with return type void must not declare any application exceptions. 
//-An asynchronous method with return type Future<V> is permitted to declare application exceptions.

//	Return Values
//-The Bean Provider makes the result value of an asynchronous invocation available to the client by returning a Future<V>
// object for which both get() methods return the result value. 
//-A concrete Future<V> implemention called javax.ejb.AsyncResult<V> is provided by the container as a convenience.
//-The AsyncResult<V> class has a constructor that takes the result value as a parameter.
//	    return new AsyncResult<Integer>(result);
//-Note that the Future<V> object returned from the bean class method (including any instance of AsyncResult<V>) is only used
// as a way to pass the result value to the container. This object is not given directly to the caller, since by definition
// the caller already has a container-generated Future<V>object that was returned from the original invocation.

//	Method cancellation
//-A client can request that an asynchronous invocation be cancelled by calling the Future<V>.cancel(boolean mayInterruptIfRunning).
//-The Bean Provider can check whether the client has requested cancellation by calling the SessionContext.wasCancelCalled()
// within the context of the asynchronous method. See Section 3.4.8 for the description of the client Future contract.

//	Transactions
//-The client’s transaction context does not propagate with an asynchronous method invocation.
//-From the Bean Provider’s point of view, there is never a transaction context flowing in from the client. This means, fe,
// that the semantics of the REQUIRED transaction attribute on an asynchronous method are exactly the same as REQUIRES_NEW.

//	Security
//-The caller security principal propagates with an asynchronous method invocation. Caller security principal propagation behaves
// exactly the same for asynchronous method invocations as it does for synchronous session bean invocations.

//	Client Exception Behavior
//-Client exception behavior depends on whether the asynchronous method has return type void or Future<V>.
//-If void, then once control has returned from the client’s method call no exceptions occurring during the processing of the invocation
// will be delivered to the client. For this reason, asynchronous methods with return type void must not declare app exceptions.
//-If Future<V>, an exception thrown from the processing of the asynchronous method invocation is accessible to the client
// via the getCause() method of a java.util.concurrent.ExecutionException thrown from either Future.get() method.

//	Mixing sync and async
//-When the second method is invoked from the first method, the container doesn’t get a chance to proxy the call to the second method
// it is called directly on the same instance and hence executes synchronously. 
//-We need to use SessionContext and use it to call the method as ctx.getBusinessObject(AsynchronousTest.class).asyncMethodVoid()
//-Now the first method is getting executed synchronously while the second method getting executed asynchronously.

//	Limits
//-Note that if there are multiple concurrent asynchronous invocations on a bean, there may be a limit to how many
// the container will allow concurrently. Consider the code sample below
//		for(int i=0;i<20;i++){
//		    asyncService.asyncMethod();
//		}
//-On glassfish, after about 16 threads, there was a pause and only after some of those 16 threads finished executing did the 
// remaining 4 threads start. From the caller’s point of view the method calls are still asynchronous, but instead of the async
// call getting done after 3 seconds (assuming the async task takes 3 secs), some of the async tasks may get done only
//6 secs after the async method is invoked. 
//-This is expected, since the thread pool is finite – so makes sense to experiment with your AS
// to understand and configure the behavior.


public class AnInfo {}
