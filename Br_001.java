import java.util.List;

public class Br_001 {
	/**
	 * BR-001 —D—ÇŒÚ‹q”»’èƒ‹[ƒ‹
	 * 
	 * @param orderList ”­’Ï’•¶ƒŠƒXƒg
	 * @return —D—ÇŒÚ‹q‚©”Û‚©
	 * 
	 */
	public boolean excellentCustomer(List<Order> orderList) {
		int amountThreshold = 1000;
		int countThreshold = 3;
        long highOrderCount = orderList.stream().filter(order->order.getMoney()>=amountThreshold).count();
        return highOrderCount >= countThreshold;
	}
}
