package com.wwac.test;

import java.util.concurrent.ExecutionException;

import com.wwac.invoker.BSEStockExchangeInvoker;

public class BSEStockExchangeClinetTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		BSEStockExchangeInvoker invoker = BSEStockExchangeInvoker.getBSEStockExchangeInvokerInstance();
		invoker.open();
		String price = invoker.getStockPrice("silver","mumbai");
		System.out.println(price);
		invoker.close();
	}
}
