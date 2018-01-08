package com.aw.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/stock")
public class BSEStockExchangeResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/price/{stockName}")
	public void getStockPrice(@PathParam("stockName")String stockName, @Suspended final AsyncResponse aysncResponse){
		new Thread(){
			public void run(){
				aysncResponse.resume(Response.ok().entity(383.00f).build());
			}
		}.start();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/findagentName/{city}")
	public void getAgentName(@PathParam("city")String city,@Suspended final AsyncResponse aysncResponse){
		new Thread(){
			public void run(){
				aysncResponse.resume(Response.ok().entity("mahendra").build());
			}
		}.start();
	}
}
