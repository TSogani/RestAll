package com.cor.resource;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/order")
public class OrderResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/status/{orderId}")
	public Response getOrderStatus(@PathParam("orderId") String orderId){
		Calendar calendar = null;
		Response response = null;
		
		calendar = Calendar.getInstance();
		int min = calendar.get(Calendar.MINUTE);
		calendar.set(Calendar.MINUTE,min+2);
		response = Response.ok().entity("orderId : "+orderId+" In-process").expires(calendar.getTime()).build();
		
		return response;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/price/{productCode}")
	public Response getProductPrice(@PathParam("productCode") String productCode){
		Response response = null;
		Calendar calendar = null;
		CacheControl cacheControl = null;
		cacheControl = new CacheControl();
		
		cacheControl.setMaxAge(2000);
		
		calendar = Calendar.getInstance();
		calendar.get(Calendar.MINUTE);
		
		response = Response.ok().cacheControl(cacheControl).entity(899.00f).build();
		 
		return response;
	}
}
