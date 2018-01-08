package com.wwu.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/car/{make}")
public class CarResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/search/{make}-{model}")
	public String search(@PathParam("make") String make,@PathParam("model") String model){
		return "make : "+make+" model : "+model;  //in this case make will be access at method level
	}
	
}
