package concurency.test;

import java.util.Arrays;
//-Reuses beans
//-Server default EJB settings + Limit Concurrent EJB Instances: checked.
//for(int i = 0; i<100; i++) {
//	mes.execute(() -> {
//		cbe.testMethod();
//	});
public class TestSL3 {

	public TestSL3() {

		String[] beans = {
				"concurency.ConcurrentBeanEJB@794e6e]]", "concurency.ConcurrentBeanEJB@c835d1]]",
				"concurency.ConcurrentBeanEJB@b7aa6c]]", "concurency.ConcurrentBeanEJB@b4ec4d]]",
				"concurency.ConcurrentBeanEJB@35c5bf]]", "concurency.ConcurrentBeanEJB@13c7c9c]]",
				"concurency.ConcurrentBeanEJB@16986a2]]", "concurency.ConcurrentBeanEJB@14fc148]]",
				"concurency.ConcurrentBeanEJB@1c6ac9c]]", "concurency.ConcurrentBeanEJB@1313f75]]",
				"concurency.ConcurrentBeanEJB@1b20eb8]]", "concurency.ConcurrentBeanEJB@32d53a]]",
				"concurency.ConcurrentBeanEJB@fdfcf4]]", "concurency.ConcurrentBeanEJB@f2cb06]]",
				"concurency.ConcurrentBeanEJB@1b1ae97]]", "concurency.ConcurrentBeanEJB@327285]]",
				"concurency.ConcurrentBeanEJB@da08b4]]", "concurency.ConcurrentBeanEJB@df1ec7]]",
				"concurency.ConcurrentBeanEJB@fcb85d]]", "concurency.ConcurrentBeanEJB@2b161]]",
				"concurency.ConcurrentBeanEJB@1b616d7]]", "concurency.ConcurrentBeanEJB@135df6f]]",
				"concurency.ConcurrentBeanEJB@7539f0]]", "concurency.ConcurrentBeanEJB@1281e4a]]",
				"concurency.ConcurrentBeanEJB@449617]]", "concurency.ConcurrentBeanEJB@17d2a41]]",
				"concurency.ConcurrentBeanEJB@e4bc3b]]", "concurency.ConcurrentBeanEJB@16e8191]]",
				"concurency.ConcurrentBeanEJB@bcfb00]]", "concurency.ConcurrentBeanEJB@c21dc1]]",
				"concurency.ConcurrentBeanEJB@76e399]]", "concurency.ConcurrentBeanEJB@d00627]]",
				"concurency.ConcurrentBeanEJB@c835d1]]", "concurency.ConcurrentBeanEJB@794e6e]]",
				"concurency.ConcurrentBeanEJB@16986a2]]", "concurency.ConcurrentBeanEJB@13c7c9c]]",
				"concurency.ConcurrentBeanEJB@b4ec4d]]", "vconcurency.ConcurrentBeanEJB@b7aa6c]]",
				"vconcurency.ConcurrentBeanEJB@35c5bf]]", "concurency.ConcurrentBeanEJB@14fc148]]",
				"vconcurency.ConcurrentBeanEJB@1c6ac9c]]", "concurency.ConcurrentBeanEJB@1313f75]]",
				"concurency.ConcurrentBeanEJB@1b20eb8]]", "concurency.ConcurrentBeanEJB@32d53a]]",
				"concurency.ConcurrentBeanEJB@fdfcf4]]", "concurency.ConcurrentBeanEJB@f2cb06]]",
				"concurency.ConcurrentBeanEJB@1b1ae97]]", "concurency.ConcurrentBeanEJB@327285]]",
				"concurency.ConcurrentBeanEJB@da08b4]]", "concurency.ConcurrentBeanEJB@df1ec7]]",
				"concurency.ConcurrentBeanEJB@fcb85d]]", "concurency.ConcurrentBeanEJB@2b161]]",
				"concurency.ConcurrentBeanEJB@1b616d7]]", "concurency.ConcurrentBeanEJB@d00627]]",
				"concurency.ConcurrentBeanEJB@bcfb00]]", "concurency.ConcurrentBeanEJB@e4bc3b]]",
				"concurency.ConcurrentBeanEJB@16e8191]]", "concurency.ConcurrentBeanEJB@17d2a41]]",
				"concurency.ConcurrentBeanEJB@c21dc1]]", "concurency.ConcurrentBeanEJB@1281e4a]]",
				"concurency.ConcurrentBeanEJB@7539f0]]", "concurency.ConcurrentBeanEJB@135df6f]]",
				"concurency.ConcurrentBeanEJB@76e399]]", "concurency.ConcurrentBeanEJB@449617]]",
				"concurency.ConcurrentBeanEJB@c835d1]]", "concurency.ConcurrentBeanEJB@16986a2]]",
				"concurency.ConcurrentBeanEJB@794e6e]]", "concurency.ConcurrentBeanEJB@b4ec4d]]",
				"concurency.ConcurrentBeanEJB@35c5bf]]", "concurency.ConcurrentBeanEJB@b7aa6c]]",
				"concurency.ConcurrentBeanEJB@13c7c9c]]", "concurency.ConcurrentBeanEJB@14fc148]]",
				"concurency.ConcurrentBeanEJB@1c6ac9c]]", "concurency.ConcurrentBeanEJB@1313f75]]",
				"concurency.ConcurrentBeanEJB@1b20eb8]]", "concurency.ConcurrentBeanEJB@32d53a]]",
				"concurency.ConcurrentBeanEJB@fdfcf4]]", "concurency.ConcurrentBeanEJB@f2cb06]]",
				"concurency.ConcurrentBeanEJB@1b1ae97]]", "concurency.ConcurrentBeanEJB@327285]]",
				"concurency.ConcurrentBeanEJB@da08b4]]", "concurency.ConcurrentBeanEJB@fcb85d]]",
				"concurency.ConcurrentBeanEJB@df1ec7]]", "concurency.ConcurrentBeanEJB@2b161]]",
				"concurency.ConcurrentBeanEJB@1b616d7]]", "concurency.ConcurrentBeanEJB@bcfb00]]",
				"concurency.ConcurrentBeanEJB@17d2a41]]", "concurency.ConcurrentBeanEJB@c21dc1]]",
				"concurency.ConcurrentBeanEJB@16e8191]]", "concurency.ConcurrentBeanEJB@76e399]]",
				"concurency.ConcurrentBeanEJB@135df6f]]", "concurency.ConcurrentBeanEJB@e4bc3b]]",
				"concurency.ConcurrentBeanEJB@449617]]", "concurency.ConcurrentBeanEJB@1281e4a]]",
				"concurency.ConcurrentBeanEJB@d00627]]", "concurency.ConcurrentBeanEJB@7539f0]]",
				"concurency.ConcurrentBeanEJB@c835d1]]", "concurency.ConcurrentBeanEJB@16986a2]]",
				"concurency.ConcurrentBeanEJB@794e6e]]", "concurency.ConcurrentBeanEJB@b4ec4d]]", 
			};

		System.out.println("Beans size: " + beans.length);
		System.out.println("Beans distinct size: " + Arrays.asList(beans).stream().distinct().count());

	}

	public static void main(String[] args) {
		new TestSL3();
	}
}
