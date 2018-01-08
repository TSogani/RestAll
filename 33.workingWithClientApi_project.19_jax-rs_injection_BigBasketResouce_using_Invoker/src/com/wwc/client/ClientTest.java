package com.wwc.client;

import com.wwc.invoker.BigBasketInvoker;

public class ClientTest {
	public static void main(String[] args) {
		BigBasketInvoker bigBasketInvoker = null;
		bigBasketInvoker = BigBasketInvoker.getInvokerInstance();
		
		bigBasketInvoker.open();
		String body = bigBasketInvoker.doSearch("itc", 2, "surfexel", 3, "gloceries");
		System.out.println(body);
		bigBasketInvoker.close();
	}
}
