package hello.advanced.trace.threadLocal;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.threadLocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ThreadLocalServiceTest {
	private ThreadLocalService service = new ThreadLocalService();

	@Test
	void field() {
		log.info("start");

		Runnable userA = () -> service.logic("userA");
		Runnable userB = () -> service.logic("userB");

		Thread threadA = new Thread(userA);
		threadA.setName("thread-A");
		Thread threadB = new Thread(userB);
		threadB.setName("thread-B");

		threadA.start();
		// sleep(2000);
		threadB.start();

		sleep(3000);
		log.info("main exit");
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
