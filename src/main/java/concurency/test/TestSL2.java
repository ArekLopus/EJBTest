package concurency.test;

import java.util.Arrays;
import java.util.stream.Stream;
//-Reuses beans 
//Cache Idle Timeout: 60s, Cache Idle Timeout: 600s ~ the same results (throws these exceptions)
//100 SL beans ran with Maximum Pool Size: 10, Max Cache Size: 10 on Server, 	Limit Concurrent EJB Instances: checked.
//for(int i = 0; i<100; i++) {
//	mes.execute(() -> {
//		cbe.testMethod();
//	});

//-After beans instances it started to throw:
//-javax.ejb.EJBException: com.sun.ejb.containers.util.pool.PoolException: Pool Instance not obtained within given time interval.
//-A system exception occurred during an invocation on EJB ConcurrentBeanEJB, method:
// public void concurency.ConcurrentBeanEJB.testMethod()]]

//-And after that again started to work - beans2 
public class TestSL2 {

	public TestSL2() {

		String[] beans = { "concurency.ConcurrentBeanEJB@376045]]", "concurency.ConcurrentBeanEJB@e0629a]]",
				"concurency.ConcurrentBeanEJB@16604c7]]", "concurency.ConcurrentBeanEJB@1e475bb]]",
				"concurency.ConcurrentBeanEJB@ada86b]]", "concurency.ConcurrentBeanEJB@1c22cd9]]",
				"concurency.ConcurrentBeanEJB@2e773e]]", "concurency.ConcurrentBeanEJB@4ba872]]",
				"concurency.ConcurrentBeanEJB@1fd1996]]", "concurency.ConcurrentBeanEJB@1d9f724]]",
				"concurency.ConcurrentBeanEJB@1c4501c]]", "concurency.ConcurrentBeanEJB@bb953c]]",
				"concurency.ConcurrentBeanEJB@f82823]]", "concurency.ConcurrentBeanEJB@1350fd3]]",
				"concurency.ConcurrentBeanEJB@e0629a]]", "concurency.ConcurrentBeanEJB@16604c7]]",
				"concurency.ConcurrentBeanEJB@1d60271]]", "concurency.ConcurrentBeanEJB@ada86b]]",
				"concurency.ConcurrentBeanEJB@1c22cd9]]", "concurency.ConcurrentBeanEJB@1fd1996]]",
				"concurency.ConcurrentBeanEJB@2e773e]]", "concurency.ConcurrentBeanEJB@c4790e]]",
				"concurency.ConcurrentBeanEJB@438421]]", "concurency.ConcurrentBeanEJB@bb953c]]",
				"concurency.ConcurrentBeanEJB@1d9f724]]", "concurency.ConcurrentBeanEJB@f82823]]",
				"concurency.ConcurrentBeanEJB@e0629a]]", "concurency.ConcurrentBeanEJB@16cbcc9]]",
				"concurency.ConcurrentBeanEJB@16604c7]]", "concurency.ConcurrentBeanEJB@6a70ab]]",
				"concurency.ConcurrentBeanEJB@ada86b]]", "concurency.ConcurrentBeanEJB@724317]]",
				"concurency.ConcurrentBeanEJB@2e773e]]", "concurency.ConcurrentBeanEJB@1c22cd9]]",
				"concurency.ConcurrentBeanEJB@438421]]", "concurency.ConcurrentBeanEJB@bb953c]]",
				"concurency.ConcurrentBeanEJB@e0cf55]]", "concurency.ConcurrentBeanEJB@1d9f724]]",
				"concurency.ConcurrentBeanEJB@c4790e]]" };
		String[] beans2 = { "concurency.ConcurrentBeanEJB@f82823]]", "concurency.ConcurrentBeanEJB@f29501]]",
				"concurency.ConcurrentBeanEJB@16cbcc9]]", "concurency.ConcurrentBeanEJB@16604c7]]",
				"concurency.ConcurrentBeanEJB@6a70ab]]", "concurency.ConcurrentBeanEJB@1264baa]]",
				"concurency.ConcurrentBeanEJB@724317]]", "concurency.ConcurrentBeanEJB@2e773e]]",
				"concurency.ConcurrentBeanEJB@1c22cd9]]", "concurency.ConcurrentBeanEJB@101e6c0]]",
				"concurency.ConcurrentBeanEJB@bb953c]]", "concurency.ConcurrentBeanEJB@e0cf55]]",
				"concurency.ConcurrentBeanEJB@1d9f724]]", "concurency.ConcurrentBeanEJB@c4790e]]",
				"concurency.ConcurrentBeanEJB@c5e611]]", "concurency.ConcurrentBeanEJB@e5e0ba]]",
				"concurency.ConcurrentBeanEJB@6dc2b4]]", "concurency.ConcurrentBeanEJB@11ce844]]",
				"concurency.ConcurrentBeanEJB@141e843]]", "concurency.ConcurrentBeanEJB@f8d0c]]",
				"concurency.ConcurrentBeanEJB@1c0c61a]]", "concurency.ConcurrentBeanEJB@2d6da9]]",
				"concurency.ConcurrentBeanEJB@c89b6c]]", "concurency.ConcurrentBeanEJB@1cb0c18]]", };
		
		String[] beans3 = Stream.concat(Arrays.stream(beans), Arrays.stream(beans2)).toArray(String[]::new);
		
		System.out.println("Beans size: " + beans.length);
		System.out.println("Beans distinct size: " + Arrays.asList(beans).stream().distinct().count());

		System.out.println("Beans size: " + beans2.length);
		System.out.println("Beans distinct size: " + Arrays.asList(beans2).stream().distinct().count());
		
		System.out.println("Total Beans size: " + beans3.length);
		System.out.println("Total distinct size: " + Arrays.asList(beans3).stream().distinct().count());
	}

	public static void main(String[] args) {
		new TestSL2();
	}
}
