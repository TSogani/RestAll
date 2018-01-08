package com.wwc.invoker;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

public class OrderResourceInvoker extends Invoker{
	private static OrderResourceInvoker orderResourceInvoker;
	private static final String ORDER_RESOURCE_ROOT_URI="http://localhost:8001/14.workingWithUriAndParameter_queryParam_annotation/rest/order";
	
	private OrderResourceInvoker(){
		//no-operation
	}
	
	public static synchronized OrderResourceInvoker getOrderResourceInvokerInstance(){
		if(orderResourceInvoker==null){
			orderResourceInvoker = new OrderResourceInvoker();
		}
		return orderResourceInvoker;
	}
	
	public String newOrder(String orderId){
		Response response = null;
		String body = null;
		
		Invocation invocation = client.target(ORDER_RESOURCE_ROOT_URI).path("track").queryParam("orderId", orderId).request().buildGet();//when we use buildGet() method then it will not go to server every time.It will check the data at client side and return the data.
		response = invocation.invoke();
		
		if(response.getStatus()==200){
			body = response.readEntity(String.class);
		}
		return body;
	}
}
