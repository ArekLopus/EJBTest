package concurency.test;

import java.util.Arrays;
//-Uses a new bean per request 
//100 SL beans ran with Maximum Pool Size: 1, Max Cache Size: 1 on Server.
//for(int i = 0; i<100; i++) {
//	mes.execute(() -> {
//		cbe.testMethod();
//	});
public class TestSL1 {

	public TestSL1() {

		String[] beans = { 
				"concurency.ConcurrentBeanEJB@1423a15]]", "concurency.ConcurrentBeanEJB@1f77482]]",
				"concurency.ConcurrentBeanEJB@5f24f7]]", "concurency.ConcurrentBeanEJB@477c5c]]",
				"concurency.ConcurrentBeanEJB@65fc62]]", "concurency.ConcurrentBeanEJB@ab284b]]",
				"concurency.ConcurrentBeanEJB@686705]]", "concurency.ConcurrentBeanEJB@12f1033]]",
				"concurency.ConcurrentBeanEJB@1394426]]", "concurency.ConcurrentBeanEJB@ca1ee6]]",
				"concurency.ConcurrentBeanEJB@3576a8]]", "concurency.ConcurrentBeanEJB@1bc7ce7]]",
				"concurency.ConcurrentBeanEJB@50d1c]]", "concurency.ConcurrentBeanEJB@ed3634]]",
				"concurency.ConcurrentBeanEJB@1b58195]]", "concurency.ConcurrentBeanEJB@cb3aad]]",
				"concurency.ConcurrentBeanEJB@e3e909]]", "concurency.ConcurrentBeanEJB@158b8b]]",
				"concurency.ConcurrentBeanEJB@1f119c0]]", "concurency.ConcurrentBeanEJB@cc0ec1]]",
				"concurency.ConcurrentBeanEJB@1d6336b]]", "concurency.ConcurrentBeanEJB@3fdc9a]]",
				"concurency.ConcurrentBeanEJB@9ef4c1]]", "concurency.ConcurrentBeanEJB@12fc567]]",
				"concurency.ConcurrentBeanEJB@1038cd2]]", "concurency.ConcurrentBeanEJB@1d3981d]]",
				"concurency.ConcurrentBeanEJB@19eaf16]]", "concurency.ConcurrentBeanEJB@124337b]]",
				"concurency.ConcurrentBeanEJB@18d5bcb]]", "concurency.ConcurrentBeanEJB@108937f]]",
				"concurency.ConcurrentBeanEJB@1e71b68]]", "concurency.ConcurrentBeanEJB@b8dcde]]",
				"concurency.ConcurrentBeanEJB@e2aa43]]", "concurency.ConcurrentBeanEJB@11c3453]]",
				"concurency.ConcurrentBeanEJB@73e36f]]", "concurency.ConcurrentBeanEJB@97c88b]]",
				"concurency.ConcurrentBeanEJB@1c4922f]]", "concurency.ConcurrentBeanEJB@13f7182]]",
				"concurency.ConcurrentBeanEJB@152fa36]]", "concurency.ConcurrentBeanEJB@4166f0]]",
				"concurency.ConcurrentBeanEJB@eed690]]", "concurency.ConcurrentBeanEJB@174ca88]]",
				"concurency.ConcurrentBeanEJB@1796342]]", "concurency.ConcurrentBeanEJB@18a3f0b]]",
				"concurency.ConcurrentBeanEJB@1620051]]", "concurency.ConcurrentBeanEJB@19b0c5f]]",
				"concurency.ConcurrentBeanEJB@180a9a]]", "concurency.ConcurrentBeanEJB@4ef3c8]]",
				"concurency.ConcurrentBeanEJB@1ecaf7f]]", "concurency.ConcurrentBeanEJB@ff7300]]",
				"concurency.ConcurrentBeanEJB@1ed7d0a]]", "concurency.ConcurrentBeanEJB@1b5b4c5]]",
				"concurency.ConcurrentBeanEJB@171627a]]", "concurency.ConcurrentBeanEJB@e93245]]",
				"concurency.ConcurrentBeanEJB@1ee071]]", "concurency.ConcurrentBeanEJB@101f78b]]",
				"concurency.ConcurrentBeanEJB@1e214ee]]", "concurency.ConcurrentBeanEJB@c95e16]]",
				"concurency.ConcurrentBeanEJB@8e5e57]]", "concurency.ConcurrentBeanEJB@d362de]]",
				"concurency.ConcurrentBeanEJB@199aa03]]", "concurency.ConcurrentBeanEJB@1683ccd]]",
				"concurency.ConcurrentBeanEJB@15a5483]]", "concurency.ConcurrentBeanEJB@1e12c4c]]",
				"concurency.ConcurrentBeanEJB@1181a8f]]", "concurency.ConcurrentBeanEJB@165d0bc]]",
				"concurency.ConcurrentBeanEJB@395c4c]]", "concurency.ConcurrentBeanEJB@1e222ad]]",
				"concurency.ConcurrentBeanEJB@4a2934]]", "concurency.ConcurrentBeanEJB@cedb55]]",
				"concurency.ConcurrentBeanEJB@642981]]", "concurency.ConcurrentBeanEJB@1fd5771]]",
				"concurency.ConcurrentBeanEJB@585af6]]", "concurency.ConcurrentBeanEJB@1aab443]]",
				"concurency.ConcurrentBeanEJB@1516aea]]", "concurency.ConcurrentBeanEJB@19ba988]]",
				"concurency.ConcurrentBeanEJB@1d37294]]", "concurency.ConcurrentBeanEJB@1f607db]]",
				"concurency.ConcurrentBeanEJB@19b06b]]", "concurency.ConcurrentBeanEJB@d14076]]",
				"concurency.ConcurrentBeanEJB@ab1c48]]", "concurency.ConcurrentBeanEJB@156b572]]",
				"concurency.ConcurrentBeanEJB@171e115]]", "concurency.ConcurrentBeanEJB@52132d]]",
				"concurency.ConcurrentBeanEJB@9841da]]", "concurency.ConcurrentBeanEJB@f6d420]]",
				"concurency.ConcurrentBeanEJB@1992f4]]", "concurency.ConcurrentBeanEJB@2f5e37]]",
				"concurency.ConcurrentBeanEJB@dc0f23]]", "concurency.ConcurrentBeanEJB@18336a1]]",
				"concurency.ConcurrentBeanEJB@433c69]]", "concurency.ConcurrentBeanEJB@9a8a26]]",
				"concurency.ConcurrentBeanEJB@917ab1]]", "concurency.ConcurrentBeanEJB@1c6f60a]]",
				"concurency.ConcurrentBeanEJB@e4b65f]]", "concurency.ConcurrentBeanEJB@17e0658]]",
				"concurency.ConcurrentBeanEJB@1841c3c]]", "concurency.ConcurrentBeanEJB@22d03d]]",
				"concurency.ConcurrentBeanEJB@ef4758]]", "concurency.ConcurrentBeanEJB@15bfeaf]]", };

		System.out.println("Beans size: " + beans.length);
		System.out.println("Beans distinct size: " + Arrays.asList(beans).stream().distinct().count());

	}

	public static void main(String[] args) {
		new TestSL1();
	}
}
