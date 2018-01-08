package com.wwu.resource;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/icici-netbanking")
public class IConnctResourceWithSubUri {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{accountNo}/balance")         //it's subresource uri
	public float getBalance(@PathParam("accountNo") String accountNo){
		return 8989.00f;	
	}
}
