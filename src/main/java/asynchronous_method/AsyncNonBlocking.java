package asynchronous_method;

import java.util.concurrent.CompletableFuture;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class AsyncNonBlocking {
	
	@Asynchronous
    public void asyncMethodCF(CompletableFuture<String> cf) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        cf.complete("@Asynchronous method CompletableFuture<String>, thread: " + Thread.currentThread().getName());
    }
	
}
