package hello.advanced.trace.threadLocal;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.threadLocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class FieldServiceTest {
	private FieldService fieldService = new FieldService();

	@Test
	void field() {
		log.info("start");

		Runnable userA = () -> fieldService.logic("userA");
		Runnable userB = () -> fieldService.logic("userB");

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
