package com.wwc.test;

import com.wwc.invoker.OrderResourceInvoker;

public class OrderResourceClientTest {
	public static void main(String[] args) {
		OrderResourceInvoker invoker = OrderResourceInvoker.getOrderResourceInvokerInstance();
		invoker.open();
		String result = invoker.newOrder("product:apple");
		System.out.println(result);
		invoker.close();
		
	}
}
