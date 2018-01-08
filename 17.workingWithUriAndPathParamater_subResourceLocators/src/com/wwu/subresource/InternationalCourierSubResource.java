package com.wwu.subresource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class InternationalCourierSubResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{awbNo}")
	public String getInternationalCourierStatus(@PathParam("awbNo") String awbNo){
		return "your parsel awbNo : "+awbNo+" is in shipping state from internalCourier";
	}
}
