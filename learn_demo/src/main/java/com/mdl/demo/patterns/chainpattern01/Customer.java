package com.mdl.demo.patterns.chainpattern01;

import com.mdl.demo.patterns.chainpattern01.handler.PriceHandler;
import com.mdl.demo.patterns.chainpattern01.handler.PriceHandlerFactory;

/**
 * 客户，请求折扣
 */
public class Customer {
	
	private PriceHandler priceHandler;
	
	public void setPriceHandler(PriceHandler priceHandler) {
		this.priceHandler = priceHandler;
	}

	public void requestDiscount(float discount){
		priceHandler.processDiscount(discount);
	}
	
	
	public static void main(String[] args){
		Customer customer = new Customer();
		customer.setPriceHandler(PriceHandlerFactory.createPriceHandler());
		
//		Random rand = new Random();
//		for(int i=1;i<=100;i++){
//			System.out.print(i+":");
//			customer.requestDiscount(rand.nextFloat());
//		}

		customer.requestDiscount(0.6f);
		
		
	}
	

}
