package hello.advanced.v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.helloTrace.HelloTraceV1;
import hello.advanced.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
	private final OrderServiceV2 orderServiceV2;
	private final HelloTraceV2 trace;

	@GetMapping("/v2/request")
	public String request(String itemId) {
		TraceStatus status = null;
		try {
			status = trace.begin("OrderController.request()");
			orderServiceV2.orderItem(status.getTraceId(), itemId);
			trace.end(status);
			return "ok";
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}
}
