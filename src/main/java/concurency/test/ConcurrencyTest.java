package concurency.test;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ejb.bean.singletons.Concurrency_Sin_BMC;
import ejb.bean.singletons.Concurrency_Sin_CMC;
import ejb.bean.stateful.Concurrency_SF;
import ejb.bean.stateless.Concurrency_SL;

//JMeter and AB test for WF	127.0.0.1
//ab -c 1 -n 20000 http://localhost:8080/EJBTest/res/concurrency/ejb-sin-cmc
//ab -c 1 -n 20000 http://localhost:8080/EJBTest/res/concurrency/ejb-sin-bmc
//ab -c 1 -n 20000 http://localhost:8080/EJBTest/res/concurrency/ejb-sf
//ab -c 1 -n 20000 http://localhost:8080/EJBTest/res/concurrency/ejb-sl

//ab -c 1 -n 20000 http://localhost:8080/EJBTest/res/concurrency/cdi-no-concurrency
//ab -c 1 -n 20000 http://localhost:8080/EJBTest/res/concurrency/cdi-synchronized
//ab -c 1 -n 20000 http://localhost:8080/EJBTest/res/concurrency/cdi-lock

@Path("concurrency")
@Produces(MediaType.TEXT_HTML)
public class ConcurrencyTest {
	
	@Inject
	Concurrency_Sin_CMC cbe;
	
	@Inject
	Concurrency_Sin_BMC bbe;
	
	@Inject
	Concurrency_SF csf;
	
	@Inject
	Concurrency_SL csl;
	
	@Inject
	TestBeanConcurrentReq bc;
	
	
	@Path("ejb-sin-cmc")
	@GET
	public String sinCmcTest() {
		return cbe.testMethodTests();
	}
	
	@Path("ejb-sin-bmc")
	@GET
	public String sinBmcRead() {
		return bbe.testMethodTests();
	}
	
	@Path("ejb-sf")
	@GET
	public String sfTest() {
		return csf.testMethodTests();
	}
	
	@Path("ejb-sl")
	@GET
	public String slTest() {
		return csl.testMethodTests();
	}
	
	
	@Path("cdi-no-concurrency")
	@GET
	public String cdiTest() {
		return csl.testMethodTests();
	}
	@Path("cdi-synchronized")
	@GET
	public String cdiSyncTest() {
		return csl.testMethodTests();
	}
	@Path("cdi-lock")
	@GET
	public String cdiLockTest() {
		return csl.testMethodTests();
	}
	
	@Path("cdi-app-no-concurrency")
	@GET
	public String cdiAppTest() {
		return csl.testMethodTests();
	}
	@Path("cdi-app-synchronized")
	@GET
	public String cdiAppSyncTest() {
		return csl.testMethodTests();
	}
	@Path("cdi-app-lock")
	@GET
	public String cdiAppLockTest() {
		return csl.testMethodTests();
	}
}
