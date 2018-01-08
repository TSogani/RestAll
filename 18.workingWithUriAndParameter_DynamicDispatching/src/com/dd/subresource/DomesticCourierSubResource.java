package com.dd.subresource;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//not annotate path annotation here bcoz it it not root resource.
public class DomesticCourierSubResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{awbNo}")
	public String getDomesticCourierStatus(@PathParam("awbNo") String awbNo){
		return "your parsel awbNo :"+awbNo+"in transit state from domenstic"+"hashcode:"+this.hashCode();
	}
}
