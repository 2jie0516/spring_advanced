package hello.advanced.vo;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {
	private final OrderRepositoryV0 orderRepositoryV0;

	public void orderItem(String itemId) {
		orderRepositoryV0.save(itemId);
	}
}
