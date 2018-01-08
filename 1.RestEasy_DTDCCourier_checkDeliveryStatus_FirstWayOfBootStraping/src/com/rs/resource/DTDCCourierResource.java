package com.rs.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/dtdccourier")
public class DTDCCourierResource {
	
	@GET
	@Produces(value="text/plain")
	public String getCourierStatus(@QueryParam("agentNo")String agentNo,@QueryParam("trackingNo")String trackingNo){
		
		return "your courier tracking no "+trackingNo+" is in transint status";
	}
}
