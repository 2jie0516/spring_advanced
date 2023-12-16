package hello.advanced.v2;

import org.springframework.stereotype.Service;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.helloTrace.HelloTraceV1;
import hello.advanced.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
	private final OrderRepositoryV2 orderRepositoryV2;
	private final HelloTraceV2 trace;

	public void orderItem(TraceId traceId, String itemId) {
		TraceStatus status = null;
		try {
			status = trace.beginSync(traceId, "OrderController.request()");
			orderRepositoryV2.save(itemId);
			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}
}
