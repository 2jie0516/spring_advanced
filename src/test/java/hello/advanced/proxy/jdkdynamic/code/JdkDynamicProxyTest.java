package hello.advanced.proxy.jdkdynamic.code;

import java.lang.reflect.Proxy;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdkDynamicProxyTest {
	@Test
	void Atest() {
		AInterface target = new AImpl();
		TimeInvocationHandler handler = new TimeInvocationHandler(target);
		AInterface proxy = (AInterface)Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[] {AInterface.class}, handler);

		proxy.call();

		log.info("targetClass={}", target.getClass());
		log.info("proxyClass={}", proxy.getClass());
	}

	@Test
	void Btest() {
		BInterface target = new BImpl();
		TimeInvocationHandler handler = new TimeInvocationHandler(target);
		BInterface proxy = (BInterface)
			Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]
				{BInterface.class}, handler);
		proxy.call();
		log.info("targetClass={}", target.getClass());
		log.info("proxyClass={}", proxy.getClass());
	}
}
