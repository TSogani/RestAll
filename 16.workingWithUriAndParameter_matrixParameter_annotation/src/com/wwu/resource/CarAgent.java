package com.wwu.resource;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/car-agent")
public class CarAgent {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/search/{make}/{model}")
	public String search(@PathParam("make") String make, @PathParam("model") String model,
			@QueryParam("color") String color, @QueryParam("price") String price, @MatrixParam("year")String year) {
		return "make:"+make+" model:"+model+" color:"+color+" price:"+price+" year:"+year;
	}
}
