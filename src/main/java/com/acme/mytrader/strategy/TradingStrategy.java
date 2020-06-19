package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy implements PriceSource, PriceListener, ExecutionService{
	private String stock;
	private Double listPrice;
	@Override
	public void buy(String security, double price, int volume) {
		
		if (security.equalsIgnoreCase(stock)) {
			if (price == listPrice) {

				System.out.println("We purchased the following stock " + security);
			}else{
				System.out.println("N/A");
			}
		}else{
			System.out.println("not matched");
		}
	}

	@Override
	public void priceUpdate(String security, double price) {
		
	}

	@Override
	public void addPriceListener(PriceListener listener) {
		PriceSource source  = new PriceSource() {
			
			@Override
			public void addPriceListener(PriceListener listener) {

				ExecutionService service = new ExecutionService() {

					@Override
					public void buy(String security, double price, int volume) {
						
						if (security.equalsIgnoreCase(stock)) {
							if (price == listPrice) {

								System.out.println("We purchased the following stock " + security);
							}else{
								System.out.println("N/A");
							}
						}else{
							System.out.println("Nop");
						}
					}
				};
				service.buy("BOSS", 30.0, 10);
			}
		};
	}
	
	
	public static void main(String[] args) {
		
		TradingStrategy st = new TradingStrategy();
		st.priceUpdate("A", 10);
		
		st.buy("A", 10, 10);
	}
	
	
	
}
