package com.wwu.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/postal/{area}")
public class PostalResource {
		
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public int locateZip(@PathParam("area") String area){
		
		if(area.equals("pratapNagar")){
			return 302033;
		}else if(area.equals("sikandra")){
			return 303326;
		}
		return 0;
	}
}
