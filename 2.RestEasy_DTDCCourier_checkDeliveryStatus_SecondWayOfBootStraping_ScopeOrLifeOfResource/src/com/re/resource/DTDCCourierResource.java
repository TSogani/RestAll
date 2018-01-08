package com.re.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/dtdccourier")
public class DTDCCourierResource {
	
	@GET
	@Produces(value="text/plain")
	public String getDeliveryStatus(@QueryParam("agentNo") int agentNo,@QueryParam("trackingNo") String trackingNo){
		return "your order trackingNo "+trackingNo+" is in transient state,hashcode is : "+this.hashCode();
	}
}
