package com.wwu.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/order")
public class OrderResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/track")    					//it's called binding uri or subrsource uri
	public String trackOrder(@QueryParam("orderId") String orderId){       //it's called subresource method becoz it is binded to @Path annotation at method level
		if(orderId.equals("order123")){
			return "Your order "+orderId+" in transit state"; 
		}
		return orderId+ " is wrong";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/details")                              //it's called binding uri and subresource uri
	public String showOrder(@QueryParam("orderId") String orderId){           //it's called subresource method becoz it is binded to @Path annotation at method level
		if(orderId.equals("order123")){
			return "your order "+orderId +" contains apple and mango";
		}
		return orderId+"is wrong";
	}
}
