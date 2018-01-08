package com.wwc.test;

import com.wwc.invoker.OrderResourceInvoker;

public class OrderResourceClientTest {
	public static void main(String[] args) {
		OrderResourceInvoker orderResourceInvoker = OrderResourceInvoker.getOrderResourceInvokerInstance();
		orderResourceInvoker.open();
		String result = orderResourceInvoker.newOrder("order123");
		System.out.println(result);
		orderResourceInvoker.close();
	}
}
