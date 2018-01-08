package com.re.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/dtdccourier")
public class DtdcCourierResource {

	@GET
	@Produces(value="text/plain")
	public String checkCourierState(@QueryParam("agentNo")String agentNo,@QueryParam("trackingNo") int trackingNo){
		return "your courier trackingNo-"+trackingNo+" in transint state";
	}
}
