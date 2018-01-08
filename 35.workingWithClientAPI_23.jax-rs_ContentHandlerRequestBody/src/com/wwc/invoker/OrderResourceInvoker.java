package com.wwc.invoker;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OrderResourceInvoker extends Invoker{
	private final static String ORDER_RESOURCE_ROOT_URI="http://localhost:8001/23.Jax-rs_ContentHandler_RequestBody/rest/order";
	private static OrderResourceInvoker orderResourceInvoker;
	
	private OrderResourceInvoker() {
		//no-operation
	}
	
	public static synchronized OrderResourceInvoker getOrderResourceInvokerInstance(){
		if(orderResourceInvoker==null){
			orderResourceInvoker = new OrderResourceInvoker();
		}
		return orderResourceInvoker;
	}
	
	public String newOrder(String order){
		String body = null;
		Response response = null;
		WebTarget target = client.target(ORDER_RESOURCE_ROOT_URI).path("new").path("in");
		
		response = target.request().header("content-type","text/plain").post(Entity.text(order));
								//(or)
		/*response = target.request().post(Entity.entity(order, MediaType.TEXT_PLAIN));*/
		
		if(response.getStatus()==200){
			body = response.readEntity(String.class);
		}
		
		return body;
	}
}
